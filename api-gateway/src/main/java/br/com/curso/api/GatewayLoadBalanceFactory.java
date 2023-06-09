package br.com.curso.api;

import io.micronaut.context.annotation.Factory;
import io.micronaut.http.client.LoadBalancer;
import io.micronaut.http.client.loadbalance.DiscoveryClientLoadBalancerFactory;
import jakarta.inject.Singleton;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Factory
public class GatewayLoadBalanceFactory {

    @Singleton
    public Map<String, LoadBalancer> servicesLoadBalancers(GatewayProperties gatewayProperties,
                                                              DiscoveryClientLoadBalancerFactory factory) {
        Set<String> services = gatewayProperties.getServices();
        Map<String, LoadBalancer> loadBalancers = new HashMap<>();
        services.forEach(serviceName -> loadBalancers.put(serviceName, factory.create(serviceName)));
        return Collections.unmodifiableMap(loadBalancers);
    }

}
