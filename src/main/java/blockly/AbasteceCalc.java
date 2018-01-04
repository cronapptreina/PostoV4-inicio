package blockly;

import cronapi.*;
import cronapi.rest.security.CronappSecurity;
import java.util.concurrent.Callable;

@CronapiMetaData(type = "blockly")
@CronappSecurity
public class AbasteceCalc {

	public static final int TIMEOUT = 300;

	/**
	 *
	 * @param Entidade
	 * @return Var
	 */
	// AbasteceCalc
	public static Var consumoMedio(Var Entidade) throws Exception {
		return new Callable<Var>() {

			private Var litros = Var.VAR_NULL;

			public Var call() throws Exception {
				litros = cronapi.math.Operations.divisor(
						cronapi.object.Operations.getObjectField(Entidade, Var.valueOf("valor")),
						cronapi.object.Operations.getObjectField(Entidade, Var.valueOf("precoLitro")));
				return cronapi.math.Operations
						.divisor(cronapi.object.Operations.getObjectField(Entidade, Var.valueOf("km")), litros);
			}
		}.call();
	}

	/**
	 *
	 * @param Entidade
	 * @return Var
	 */
	// AbasteceCalc
	public static Var custoKm(Var Entidade) throws Exception {
		return new Callable<Var>() {

			public Var call() throws Exception {
				return cronapi.math.Operations.divisor(
						cronapi.object.Operations.getObjectField(Entidade, Var.valueOf("valor")),
						cronapi.object.Operations.getObjectField(Entidade, Var.valueOf("km")));
			}
		}.call();
	}

	/**
	 *
	 * @param Entidade
	 * @return Var
	 */
	// AbasteceCalc
	public static Var placa(Var Entidade) throws Exception {
		return new Callable<Var>() {

			public Var call() throws Exception {
				return cronapi.math.Operations.divisor(
						cronapi.object.Operations.getObjectField(Entidade, Var.valueOf("carro")),
						cronapi.object.Operations.getObjectField(Entidade, Var.valueOf("km")));
			}
		}.call();
	}

}
