package br.com.rinha.backend2.extrato;

import br.com.rinha.backend2.cliente.Cliente;
import br.com.rinha.backend2.transacao.TransacaoResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record SaldoResponse(ExtratoClienteResponse saldo, @JsonProperty(value = "ultimas_transacoes") List<TransacaoResponse> ultimasTransacoes) {

    public SaldoResponse(Cliente cliente) {
        this(new ExtratoClienteResponse(cliente),cliente.mapToResponse());
    }
}
