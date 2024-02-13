package br.com.rinha.backend2.transacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransacaoResponse(BigDecimal valor, String tipo, String descricao, LocalDateTime realizadaEm) {
    public TransacaoResponse(Transacao transacao) {
       this(transacao.getValor(),transacao.getTipo(),transacao.getDescricao(),transacao.getRealizadaEm());
    }
}
