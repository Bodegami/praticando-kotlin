package br.com.curso.http.fallback

import br.com.curso.dto.output.Veiculo
import br.com.curso.http.VeiculoHttp
import com.fasterxml.jackson.databind.ObjectMapper
import io.micronaut.retry.annotation.Fallback
import redis.clients.jedis.JedisPool
import redis.clients.jedis.JedisPoolConfig

@Fallback
class VeiculoHttpFallback(
    private val objectMapper: ObjectMapper
): VeiculoHttp {

    override fun findById(id: Long): Veiculo {
        val redisPool = JedisPool(JedisPoolConfig(), "venda-redis", 6379)
        val jedis = redisPool.resource
        val veiculoJSON = jedis.get(id.toString())
        return objectMapper.readValue(veiculoJSON, Veiculo::class.java)
    }
}