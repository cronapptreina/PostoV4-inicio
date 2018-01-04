package blockly;

import cronapi.*;
import cronapi.rest.security.CronappSecurity;
import java.util.concurrent.Callable;

@CronapiMetaData(type = "blockly")
@CronappSecurity
public class MarcaFIPE {

	public static final int TIMEOUT = 300;

	/**
	 *
	 * @return Var
	 */
	// MarcaFIPE
	public static Var buscar() throws Exception {
		return new Callable<Var>() {

			private Var consulta = Var.VAR_NULL;
			private Var excep = Var.VAR_NULL;

			public Var call() throws Exception {
				try {
					consulta = cronapi.json.Operations.toJson(cronapi.util.Operations.getURLFromOthers(
							Var.valueOf("GET"), Var.valueOf("application/json"),
							Var.valueOf("http://fipeapi.appspot.com/api/1/carros/marcas.json"), Var.VAR_NULL,
							Var.VAR_NULL));
				} catch (Exception excep_exception) {
					excep = Var.valueOf(excep_exception);
					consulta = cronapi.json.Operations.createObjectJson();
					cronapi.util.Operations.callClientFunction(Var.valueOf("cronapi.screen.notify"),
							Var.valueOf("error"),
							Var.valueOf("ão foi possível acessar a tabela FIPE. Tente mais tarde"));
				} finally {
				}
				System.out.println(consulta.getObjectAsString());
				return consulta;
			}
		}.call();
	}

}
