package blockly;

import cronapi.*;
import cronapi.rest.security.CronappSecurity;
import java.util.concurrent.Callable;

@CronapiMetaData(type = "blockly")
@CronappSecurity
public class Campeoes {

	public static final int TIMEOUT = 300;

	/**
	 *
	 * @return Var
	 */
	// Campeoes
	public static Var calcular_carros() throws Exception {
		return new Callable<Var>() {

			private Var retorno_lista_carros = Var.VAR_NULL;
			private Var consulta = Var.VAR_NULL;
			private Var valor_parcial_item = Var.VAR_NULL;

			public Var call() throws Exception {
				retorno_lista_carros = cronapi.list.Operations.newList();
				consulta = cronapi.database.Operations.query(Var.valueOf("app.entity.Abastecimento"),
						Var.valueOf(
								"select a.carro.marcaFIPE.fipe_name, a.carro.modeloFIPE.fipe_name, SUM(a.valor)/Sum(a.km) from Abastecimento a  group by a.carro.marcaFIPE.fipe_name, a.carro.modeloFIPE.fipe_name  order by SUM(a.valor)/Sum(a.km) asc"),
						Var.VAR_NULL);
				System.out.println(Var.valueOf("montando a lista").getObjectAsString());
				while (cronapi.database.Operations.hasElement(consulta).getObjectAsBoolean()) {
					valor_parcial_item = cronapi.map.Operations.createObjectMapWith(
							Var.valueOf("custo_medio",
									cronapi.database.Operations.getField(consulta, Var.valueOf("this[2]"))),
							Var.valueOf("marca",
									cronapi.database.Operations.getField(consulta, Var.valueOf("this[0]"))),
							Var.valueOf("modelo",
									cronapi.database.Operations.getField(consulta, Var.valueOf("this[1]"))));
					System.out.println(valor_parcial_item.getObjectAsString());
					cronapi.list.Operations.addLast(retorno_lista_carros, valor_parcial_item);
					cronapi.database.Operations.next(consulta);
				} // end while
				System.out.println(retorno_lista_carros.getObjectAsString());
				return retorno_lista_carros;
			}
		}.call();
	}

}
