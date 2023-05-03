package br.com.curso.controller

import br.com.curso.model.Veiculo
import br.com.curso.service.VeiculoService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post


@Controller("/veiculos")
class VeiculoController(
    private val veiculoService: VeiculoService
) {

    @Post
    fun create(@Body veiculo: Veiculo): HttpResponse<Veiculo> {
        val response = veiculoService.create(veiculo)
        return HttpResponse.created(response)
    }

    @Get("/{id}")
    fun findById(@PathVariable id: Long): HttpResponse<Veiculo> {
        val response = veiculoService.findById(id)
        return HttpResponse.created(response)
    }

}