package br.com.curso.model

import java.math.BigDecimal
import javax.validation.constraints.Null

data class Venda(
    var cliente: String,
    var veiculo: Veiculo,
    var valor: BigDecimal,
    var parcelas: List<Parcela>
) {

    constructor(): this("", Veiculo(), BigDecimal.ZERO, listOf(Parcela()))

}
