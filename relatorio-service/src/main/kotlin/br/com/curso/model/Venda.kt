package br.com.curso.model

import java.math.BigDecimal

data class Venda(
    var cliente: String,
    var veiculo: Veiculo,
    var valor: BigDecimal,
    var parcelas: List<Parcela>
) {

    constructor(): this("", Veiculo(), BigDecimal.ZERO, listOf(Parcela()))

}
