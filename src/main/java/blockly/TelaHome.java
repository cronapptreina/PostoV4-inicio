package blockly;

import cronapi.*;
import cronapi.rest.security.CronappSecurity;
import java.util.concurrent.Callable;

@CronapiMetaData(type = "blockly")
@CronappSecurity
public class TelaHome {

	public static final int TIMEOUT = 300;

	/**
	 *
	 * @return Var
	 */
	// TelaHome
	public static Var alterarPerfil() throws Exception {
		return new Callable<Var>() {

			public Var call() throws Exception {
				cronapi.database.Operations.execute(Var.valueOf("app.entity.User"),
						Var.valueOf("update User set name = :name, picture = :picture where login = :login"),
						Var.valueOf("name", cronapi.screen.Operations.getValueOfField(Var.valueOf("vars.name"))),
						Var.valueOf("picture", cronapi.screen.Operations.getValueOfField(Var.valueOf("vars.foto"))),
						Var.valueOf("login", cronapi.util.Operations.getCurrentUserName()));
				cronapi.util.Operations.callClientFunction(Var.valueOf("cronapi.screen.notify"), Var.valueOf("success"),
						Var.valueOf("Perfil alterado!"));
				return Var.VAR_NULL;
			}
		}.call();
	}

}
