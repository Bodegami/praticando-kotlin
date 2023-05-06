package br.com.curso.model

data class Veiculo(
    var id: Long,
    var marca: String,
    var modelo: String,
    var placa: String
) {

    constructor(): this(0, "", "", "")

}
