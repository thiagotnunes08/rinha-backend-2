package br.com.rinha.backend2.extrato;

import br.com.rinha.backend2.cliente.Cliente;
import br.com.rinha.backend2.transacao.Transacao;
import br.com.rinha.backend2.transacao.TransacaoResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.stream.Collectors;

public record SaldoResponse(ExtratoClienteResponse saldo, @JsonProperty(value = "ultimas_transacoes") List<TransacaoResponse> ultimasTransacoes) {

    public SaldoResponse(Cliente cliente, List<Transacao> transacoesCliente) {
        this(new ExtratoClienteResponse(cliente),transacoesCliente.stream().map(TransacaoResponse::new).collect(Collectors.toList()));
    }
}
