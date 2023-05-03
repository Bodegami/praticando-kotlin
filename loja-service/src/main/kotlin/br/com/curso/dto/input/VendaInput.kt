package br.com.curso.dto.input

import java.math.BigDecimal

data class VendaInput(
    val cliente: String,
    val veiculo: Int,
    val valo: BigDecimal,
    val quantidadeParcelas: Int
)
