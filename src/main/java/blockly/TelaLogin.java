package blockly;

import cronapi.*;
import cronapi.rest.security.CronappSecurity;
import java.util.concurrent.Callable;

@CronapiMetaData(type = "blockly")
@CronappSecurity(post = "Public", get = "Public", execute = "Public", delete = "Public", put = "Public")
public class TelaLogin {

	public static final int TIMEOUT = 300;

	/**
	 *
	 * @return Var
	 */
	// TelaLogin
	public static Var CriarUsuario() throws Exception {
		return new Callable<Var>() {

			public Var call() throws Exception {
				if (Var.valueOf(!cronapi.screen.Operations.getValueOfField(Var.valueOf("vars.senha"))
						.equals(cronapi.screen.Operations.getValueOfField(Var.valueOf("vars.confirmasenha"))))
						.getObjectAsBoolean()) {
					cronapi.util.Operations.callClientFunction(Var.valueOf("cronapi.screen.notify"),
							Var.valueOf("success"),
							Var.valueOf("A senha digitada não confere com o campo de confirmação"));
				} else {
					cronapi.database.Operations.insert(Var.valueOf("app.entity.User"),
							Var.valueOf("password",
									cronapi.screen.Operations.getValueOfField(Var.valueOf("vars.senha"))),
							Var.valueOf("name", cronapi.screen.Operations.getValueOfField(Var.valueOf("vars.email"))),
							Var.valueOf("login", cronapi.screen.Operations.getValueOfField(Var.valueOf("vars.email"))),
							Var.valueOf("email", cronapi.screen.Operations.getValueOfField(Var.valueOf("vars.email"))));
					cronapi.util.Operations.callClientFunction(Var.valueOf("cronapi.screen.notify"),
							Var.valueOf("success"),
							Var.valueOf("Usuário cadastrado! Entre no sistema usando o seu email como login!\n"));
				}
				return Var.VAR_NULL;
			}
		}.call();
	}

}
