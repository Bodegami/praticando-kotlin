package br.com.curso.service

import br.com.curso.dto.input.VendaInput
import br.com.curso.dto.output.Parcela
import br.com.curso.dto.output.Venda
import br.com.curso.http.VeiculoHttp
import jakarta.inject.Singleton
import java.math.BigDecimal
import java.time.LocalDate

@Singleton
class VendaService(
    private val veiculoHttp: VeiculoHttp
) {

    fun realizarVenda(vendaInput: VendaInput) {
        val veiculo = veiculoHttp.findById(vendaInput.veiculo)
        val parcelas: List<Parcela> = this.getQuantidadeTotalParcelas(vendaInput)

        val venda = Venda(
            cliente = vendaInput.cliente,
            veiculo = veiculo,
            valor = vendaInput.valor,
            parcelas = parcelas
        )

        println(venda)
    }

    private fun getValorParcela(vendaInput: VendaInput): BigDecimal {
        return vendaInput.valor.divide(vendaInput.quantidadeParcelas.toBigDecimal())
    }

    private fun getQuantidadeTotalParcelas(vendaInput: VendaInput): List<Parcela> {
        var parcelas: List<Parcela> = ArrayList()
        var dataVencimento = LocalDate.now().plusMonths(1)
        val valorParcela = this.getValorParcela(vendaInput)

        for (i in 1..vendaInput.quantidadeParcelas) {
            val parcela = Parcela(valorParcela, dataVencimento.toString())
            parcelas = parcelas.plus(parcela)
            dataVencimento = dataVencimento.plusMonths(1)
        }

        return parcelas
    }

}