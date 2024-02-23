package br.com.rinha.backend2.cliente;

import java.math.BigDecimal;

public record ClienteResponse(BigDecimal limite, BigDecimal saldo) {

    public ClienteResponse(Cliente cliente) {
       this(cliente.getLimite(),cliente.getSaldoInicial());
    }
}
