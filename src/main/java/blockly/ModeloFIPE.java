package blockly;

import cronapi.*;
import cronapi.rest.security.CronappSecurity;
import java.util.concurrent.Callable;

@CronapiMetaData(type = "blockly")
@CronappSecurity
public class ModeloFIPE {

	public static final int TIMEOUT = 300;

	/**
	 *
	 * @param marcaO
	 * @return Var
	 */
	// ModeloFIPE
	public static Var buscar(Var marcaO) throws Exception {
		return new Callable<Var>() {

			private Var url = Var.VAR_NULL;
			private Var consulta = Var.VAR_NULL;
			private Var excep = Var.VAR_NULL;

			public Var call() throws Exception {
				url = Var.valueOf(Var.valueOf("http://fipeapi.appspot.com/api/1/carros/veiculos/").toString()
						+ cronapi.json.Operations
								.getJsonOrMapField(cronapi.json.Operations.toJson(marcaO), Var.valueOf("id")).toString()
						+ Var.valueOf(".json").toString());
				System.out.println(url.getObjectAsString());
				try {
					consulta = cronapi.json.Operations.toJson(cronapi.util.Operations.getURLFromOthers(
							Var.valueOf("GET"), Var.valueOf("application/json"), url, Var.VAR_NULL, Var.VAR_NULL));
				} catch (Exception excep_exception) {
					excep = Var.valueOf(excep_exception);
					consulta = cronapi.json.Operations.createObjectJson();
					cronapi.util.Operations.callClientFunction(Var.valueOf("cronapi.screen.notify"),
							Var.valueOf("error"), Var.valueOf(
									"ão foi possível apresentar os modelos da tabela FIPE. Tente novamente mais tarde."));
				} finally {
				}
				System.out.println(consulta.getObjectAsString());
				return consulta;
			}
		}.call();
	}

}
