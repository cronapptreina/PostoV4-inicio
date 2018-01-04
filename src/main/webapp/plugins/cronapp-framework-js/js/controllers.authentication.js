(function($app) {
  angular.module('custom.controllers', []);

  app.controller('LoginController', function($controller, $scope, $http, $location, $rootScope, $window, $state, $translate, Notification, ReportService, UploadService) {

    app.registerEventsCronapi($scope, $translate);

    $rootScope.http = $http;
    $rootScope.Notification = Notification;
    $rootScope.UploadService = UploadService;

    $rootScope.getReport = function(reportName, params) {
      ReportService.openReport(reportName, params);
    }

    $scope.message = {};
    $scope.login = function() {
      $scope.message.error = undefined;

      var user = {
        username : $scope.username.value,
        password : $scope.password.value
      };

      $http({
        method : 'POST',
        url : 'auth',
        data : $.param(user),
        headers : {
          'Content-Type' : 'application/x-www-form-urlencoded'
        }
      }).success(handleSuccess).error(handleError);
    }

    function handleSuccess(data, status, headers, config) {
      // Store data response on session storage
      // The session storage will be cleaned when the browser window is closed
      if(typeof (Storage) !== "undefined") {
        // save the user data on localStorage
        sessionStorage.setItem("_u", JSON.stringify(data));
        $rootScope.session = JSON.parse(sessionStorage._u);
      }
      else {
        // Sorry! No Web Storage support.
        // The home page may not work if it depends
        // on the logged user data
      }

      // Redirect to home page
      $state.go("home");
    }

    function handleError(data, status, headers, config) {
      var error = status == 401 ? $translate.instant('Login.view.invalidPassword') : data;
      Notification.error(error);
    }

    try { $controller('AfterLoginController', { $scope: $scope }); } catch(e) {};
  });

  app.controller('HomeController', function($controller, $scope, $http, $rootScope, $state, $translate, Notification, ReportService, UploadService) {

    app.registerEventsCronapi($scope, $translate);

    $rootScope.http = $http;
    $rootScope.Notification = Notification;
    $rootScope.UploadService = UploadService;

    $rootScope.getReport = function(reportName, params) {
      ReportService.openReport(reportName, params);
    }

    $scope.message = {};

    $scope.selecionado = {
      valor : 1
    }

    // refresh token
    $scope.refreshToken = function() {
      $http({
        method : 'GET',
        url : 'auth/refresh'
      }).success(function(data, status, headers, config) {
        // Store data response on session storage
        console.log('revive :', new Date(data.expires));
        sessionStorage.setItem("_u", JSON.stringify(data));
        // Recussive
        setTimeout(function() {
          $scope.refreshToken();
          // refres time
        }, (1800 * 1000));
      }).error(function() {
        // abafar TODO
      });
    };

    $rootScope.session = (sessionStorage._u) ? JSON.parse(sessionStorage._u) : null;

    if($rootScope.session) {
      // When access home page we have to check
      // if the user is authenticated and the userData
      // was saved on the browser's sessionStorage
      $rootScope.myTheme = $rootScope.session.user.theme;
      $scope.$watch('myTheme', function(value) {
        if(value !== undefined && value !== "") {
          $('#themeSytleSheet').attr('href', "plugins/cronapp-framework-js/css/themes/" + value + ".min.css");
        }
      });
      if($rootScope.session.token)
        $scope.refreshToken();
    }
    else {
      $state.go("login");
      sessionStorage.removeItem("_u");
    }

    $rootScope.logout = function logout() {
      $rootScope.session = {};
      if(typeof (Storage) !== "undefined") {
        // save the user data on localStorage
        sessionStorage.removeItem("_u");
      }
      $state.go("login");
    };

    $scope.changePassword = function() {


      if(verifyCredentials()) {
        var user = {
          oldPassword : oldPassword.value,
          newPassword : newPassword.value,
          newPasswordConfirmation : newPasswordConfirmation.value
        };

        $http({
          method : 'POST',
          url : 'changePassword',
          data : $.param(user),
          headers : {
            'Content-Type' : 'application/x-www-form-urlencoded'
          }
        }).success(changeSuccess).error(changeError);
      }


      function changeSuccess(data, status, headers, config) {
        Notification.info($translate.instant('Home.view.passwordChanged'));
        cleanPasswordFields();
      }

      function changeError(data, status, headers, config) {
        var error = status >= 401 ? $translate.instant('Home.view.InvalidPassword') : data;
        Notification.error(error);
      }

      function cleanPasswordFields() {
        oldPassword.value = "";
        newPassword.value = "";
        newPasswordConfirmation.value = "";
        $("#modalPassword").modal("hide");
      }

      function verifyCredentials() {
        if(oldPassword.value === "" || newPassword.value === "" || newPasswordConfirmation.value === "") {
          if(newPasswordConfirmation.value === "") {
            Notification.error($translate.instant('Home.view.ConfirmationPasswordCanNotBeEmpty'));
          }
          if(newPassword.value === "") {
            Notification.error($translate.instant('Home.view.NewPasswordCanNotBeEmpty'));
          }
          if(oldPassword.value === "") {
            Notification.error($translate.instant('Home.view.PreviousPasswordCanNotBeEmpty'));
          }
          return false;
        }
        return true;
      }
    };

    var closeMenuHandler = function() {
      var element = $(this);
      if(element.closest('.sub-menu').length > 0) {
        element.closest(".navbar-nav").collapse('hide');
      }
    };

    $scope.$on('$viewContentLoaded', function() {
      var navMain = $(".navbar-nav");

      // Here your view content is fully loaded !!
      navMain.off("click", "a", closeMenuHandler);
      navMain.on("click", "a", closeMenuHandler);
    });

    $scope.themes = [ "cerulean", "cosmo", "cyborg", "darkly", "flatly", "journal", "lumen", "paper", "readable", "sandstone", "simplex", "slate", "spacelab", "superhero", "united", "yeti" ];

    $scope.changeTheme = function(theme) {
      if(theme !== undefined) {
        $('body').append('<div id="transition" />');
        $('#transition').css({
          'background-color' : '#FFF',
          'zIndex' : 100000,
          'position' : 'fixed',
          'top' : '0px',
          'right' : '0px',
          'bottom' : '0px',
          'left' : '0px',
          'overflow' : 'hidden',
          'display' : 'block'
        });
        $('#transition').fadeIn(800, function() {
          $('#themeSytleSheet').attr('href', "plugins/cronapp-framework-js/css/themes/" + theme + ".min.css");
          $rootScope.myTheme = theme;
          $('#transition').fadeOut(1000, function() {
            $('#transition').remove();
          });
        });

        var user = {
          theme : theme
        };

        $http({
          method : 'POST',
          url : 'changeTheme',
          data : $.param(user),
          headers : {
            'Content-Type' : 'application/x-www-form-urlencoded'
          }
        }).success(changeSuccess).error(changeError);

        function changeSuccess(data, status, headers, config) {
          $rootScope.session.theme = theme;
          $rootScope.session.user.theme = theme;
          sessionStorage.setItem("_u", JSON.stringify($rootScope.session));
        }

        function changeError(data, status, headers, config) {
          var error = data;
          Notification.error(error);
        }
      }
    };
    try { $controller('AfterHomeController', { $scope: $scope }); } catch(e) {};
  });
}(app));

window.safeApply = function(fn) {
  var phase = this.$root.$$phase;
  if(phase == '$apply' || phase == '$digest') {
    if(fn && (typeof (fn) === 'function')) {
      fn();
    }
  }
  else {
    this.$apply(fn);
  }
};