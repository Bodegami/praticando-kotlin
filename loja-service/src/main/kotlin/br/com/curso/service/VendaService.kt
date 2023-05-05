package br.com.curso.service

import br.com.curso.dto.input.VendaInput
import br.com.curso.dto.output.Parcela
import br.com.curso.dto.output.Venda
import br.com.curso.http.VeiculoHttp
import br.com.curso.producer.VendaProducer
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.inject.Singleton
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

@Singleton
class VendaService(
    private val veiculoService: VeiculoService,
    private val vendaProducer: VendaProducer,
    private val objectMapper: ObjectMapper
) {

    fun realizarVenda(vendaInput: VendaInput): Venda {
        val veiculo = veiculoService.getVeiculo(vendaInput.veiculo)
        val parcelas: List<Parcela> = this.getQuantidadeTotalParcelas(vendaInput)

        val venda = Venda(
            cliente = vendaInput.cliente,
            veiculo = veiculo,
            valor = vendaInput.valor,
            parcelas = parcelas
        )

        println(venda)
        confirmarVenda(venda)
        return venda
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

    fun confirmarVenda(venda: Venda) {
        val vendaJSON = objectMapper.writeValueAsString(venda)
        vendaProducer.publicarVenda(UUID.randomUUID().toString(), vendaJSON)
    }

}