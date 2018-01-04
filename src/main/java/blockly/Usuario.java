package blockly;

import cronapi.*;
import cronapi.rest.security.CronappSecurity;
import java.util.concurrent.Callable;

@CronapiMetaData(type = "blockly")
@CronappSecurity
public class Usuario {

	public static final int TIMEOUT = 300;

	/**
	 *
	 * @return Var
	 */
	// Usuario
	public static Var buscarLoginUsuarioLogado() throws Exception {
		return new Callable<Var>() {

			private Var consultaUsuario = Var.VAR_NULL;

			public Var call() throws Exception {
				return cronapi.util.Operations.getCurrentUserName();
			}
		}.call();
	}

	/**
	 *
	 * @return Var
	 */
	// Descreva esta função...
	public static Var obterUsuarioLogado() throws Exception {
		return new Callable<Var>() {

			private Var consultaUsuario = Var.VAR_NULL;

			public Var call() throws Exception {
				consultaUsuario = cronapi.database.Operations.query(Var.valueOf("app.entity.User"),
						Var.valueOf("select u.email, u.login, u.name, u.id from User u where u.login = :login"),
						Var.valueOf("login", cronapi.util.Operations.getCurrentUserName()));
				return cronapi.database.Operations.newEntity(Var.valueOf("app.entity.User"),
						Var.valueOf("id",
								cronapi.database.Operations.getField(consultaUsuario, Var.valueOf("this[3]"))),
						Var.valueOf("email",
								cronapi.database.Operations.getField(consultaUsuario, Var.valueOf("this[0]"))),
						Var.valueOf("name",
								cronapi.database.Operations.getField(consultaUsuario, Var.valueOf("this[2]"))),
						Var.valueOf("login",
								cronapi.database.Operations.getField(consultaUsuario, Var.valueOf("this[1]"))));
			}
		}.call();
	}

}
