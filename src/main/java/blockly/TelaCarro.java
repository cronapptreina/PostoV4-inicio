package blockly;

import cronapi.*;
import cronapi.rest.security.CronappSecurity;
import java.util.concurrent.Callable;

@CronapiMetaData(type = "blockly")
@CronappSecurity
public class TelaCarro {

	public static final int TIMEOUT = 300;

	/**
	 *
	 * @param Entidade
	 * @return Var
	 */
	// TelaCarro
	public static Var calculaConsumoMedioCarro(Var Entidade) throws Exception {
		return new Callable<Var>() {

			private Var ConsultaAbastecimento = Var.VAR_NULL;
			private Var somaConsumo = Var.VAR_NULL;
			private Var qtdAbastecimentos = Var.VAR_NULL;
			private Var litros = Var.VAR_NULL;
			private Var consumo = Var.VAR_NULL;

			public Var call() throws Exception {
				ConsultaAbastecimento = cronapi.database.Operations.query(Var.valueOf("app.entity.Abastecimento"),
						Var.valueOf(
								"select a.valor, a.precoLitro, a.km, a.carro.placa from Abastecimento a where a.carro.id = :carroId"),
						Var.valueOf("carroId", cronapi.object.Operations.getObjectField(Entidade, Var.valueOf("id"))));
				if (cronapi.database.Operations.hasElement(ConsultaAbastecimento).getObjectAsBoolean()) {
					somaConsumo = Var.valueOf(0);
					qtdAbastecimentos = Var.valueOf(0);
					while (cronapi.database.Operations.hasElement(ConsultaAbastecimento).getObjectAsBoolean()) {
						litros = cronapi.math.Operations.divisor(
								cronapi.database.Operations.getField(ConsultaAbastecimento, Var.valueOf("this[0]")),
								cronapi.database.Operations.getField(ConsultaAbastecimento, Var.valueOf("this[1]")));
						consumo = cronapi.math.Operations.divisor(
								cronapi.database.Operations.getField(ConsultaAbastecimento, Var.valueOf("this[2]")),
								litros);
						somaConsumo = cronapi.math.Operations.sum(somaConsumo, consumo);
						qtdAbastecimentos = cronapi.math.Operations.sum(qtdAbastecimentos, Var.valueOf(1));
						cronapi.database.Operations.next(ConsultaAbastecimento);
					} // end while
				} else {
					somaConsumo = Var.valueOf(0);
					qtdAbastecimentos = Var.valueOf(1);
				}
				return cronapi.math.Operations.divisor(somaConsumo, qtdAbastecimentos);
			}
		}.call();
	}

}
