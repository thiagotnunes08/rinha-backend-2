package br.com.rinha.backend2.extrato;

import br.com.rinha.backend2.cliente.Cliente;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ExtratoClienteResponse(BigDecimal total, @JsonProperty(value = "data_extrato") LocalDateTime dataExtrato, BigDecimal limite) {


    public ExtratoClienteResponse(Cliente cliente) {
        this(cliente.getSaldo(),LocalDateTime.now(),cliente.getLimite());
    }
}
