package br.com.curso.http.fallback

import br.com.curso.dto.output.Veiculo
import br.com.curso.http.VeiculoHttp
import io.micronaut.retry.annotation.Fallback

@Fallback
class VeiculoHttpFallback: VeiculoHttp {

    override fun findById(id: Long): Veiculo {
        println("Chamou o método fallback")
        return Veiculo(
            id = 3,
            modelo = "MONZA",
            marca = "GM",
            placa = "PPP1111"
        )
    }
}