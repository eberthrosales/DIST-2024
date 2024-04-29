package com.example.mspedido.feign;

import com.example.mspedido.dto.ClienteDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-cliente-service", path = "/cliente")
public interface ClienteFeign {
    @GetMapping("/{id}")
    @CircuitBreaker(name = "clienteListarPorIdCB", fallbackMethod = "fallBackcliente")
    ResponseEntity<ClienteDto> productoBuscarPorId(@PathVariable(required = true) Integer id);

    default ResponseEntity<ClienteDto> fallBackcliente(Integer id) {
        return ResponseEntity.ok(new ClienteDto());

    }
}
