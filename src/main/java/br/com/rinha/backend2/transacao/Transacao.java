package br.com.rinha.backend2.transacao;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Entity
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal valor;
    private String tipo;
    private String descricao;
    private LocalDateTime realizadaEm;

    public Transacao(NovaTransaoRequest request) {
        this.valor = request.valor();
        this.tipo = request.tipo();
        this.descricao = request.descricao();
        this.realizadaEm = LocalDateTime.now();
    }

    /**
     * @deprecated hibernate only
     */
    @Deprecated
    public Transacao() {

    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getRealizadaEm() {
        return realizadaEm;
    }
}
