package blockly;

import cronapi.*;
import cronapi.rest.security.CronappSecurity;
import java.util.concurrent.Callable;

@CronapiMetaData(type = "blockly")
@CronappSecurity
public class TelaPosto {

	public static final int TIMEOUT = 300;

	/**
	 *
	 * @return Var
	 */
	// TelaPosto
	public static Var buscarCEP() throws Exception {
		return new Callable<Var>() {

			private Var url = Var.VAR_NULL;
			private Var dadosCEP = Var.VAR_NULL;

			public Var call() throws Exception {
				url = Var.valueOf(Var.valueOf("https://viacep.com.br/ws/").toString()
						+ cronapi.screen.Operations.getValueOfField(Var.valueOf("Posto.active.cep")).toString()
						+ Var.valueOf("/json").toString());
				System.out.println(url.getObjectAsString());
				dadosCEP = cronapi.util.Operations.getURLFromOthers(Var.valueOf("GET"), Var.valueOf("application/json"),
						url, Var.VAR_NULL, Var.VAR_NULL);
				dadosCEP = cronapi.json.Operations.toJson(dadosCEP);
				cronapi.util.Operations.callClientFunction(Var.valueOf("cronapi.screen.changeValueOfField"),
						Var.valueOf("Posto.active.logradouro"),
						cronapi.json.Operations.getJsonOrMapField(dadosCEP, Var.valueOf("logradouro")));
				cronapi.util.Operations.callClientFunction(Var.valueOf("cronapi.screen.changeValueOfField"),
						Var.valueOf("Posto.active.bairro"),
						cronapi.json.Operations.getJsonOrMapField(dadosCEP, Var.valueOf("bairro")));
				cronapi.util.Operations.callClientFunction(Var.valueOf("cronapi.screen.changeValueOfField"),
						Var.valueOf("Posto.active.cidade"),
						cronapi.json.Operations.getJsonOrMapField(dadosCEP, Var.valueOf("localidade")));
				cronapi.util.Operations.callClientFunction(Var.valueOf("cronapi.screen.changeValueOfField"),
						Var.valueOf("Posto.active.uf"),
						cronapi.json.Operations.getJsonOrMapField(dadosCEP, Var.valueOf("uf")));
				return Var.VAR_NULL;
			}
		}.call();
	}

}
