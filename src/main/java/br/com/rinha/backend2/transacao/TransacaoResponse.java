package br.com.rinha.backend2.transacao;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransacaoResponse(BigDecimal valor, String tipo, String descricao, @JsonProperty(value = "realizada_em") LocalDateTime realizadaEm) {
    public TransacaoResponse(Transacao transacao) {
       this(transacao.getValor(),transacao.getTipo(),transacao.getDescricao(),transacao.getRealizadaEm());
    }
}
