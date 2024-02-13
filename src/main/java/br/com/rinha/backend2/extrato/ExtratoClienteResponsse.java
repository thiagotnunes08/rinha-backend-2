package br.com.rinha.backend2.extrato;

import br.com.rinha.backend2.cliente.Cliente;
import br.com.rinha.backend2.transacao.TransacaoResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record ExtratoClienteResponsse(BigDecimal total, LocalDateTime dataExtrato, BigDecimal limite, List<TransacaoResponse> ultimasTransacoes) {


    public ExtratoClienteResponsse(Cliente possivelCliente) {
        this(possivelCliente.getSaldo(),LocalDateTime.now(),possivelCliente.getLimite(),possivelCliente.mapToResponse());
    }
}
