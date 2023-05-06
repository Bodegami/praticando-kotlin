package br.com.curso.model

import java.math.BigDecimal

data class Parcela(
    var valor: BigDecimal,
    var dataVencimento: String
) {

    constructor(): this(BigDecimal.ZERO, "")

}
