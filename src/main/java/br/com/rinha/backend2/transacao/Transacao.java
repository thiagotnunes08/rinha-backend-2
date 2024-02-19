package br.com.rinha.backend2.transacao;

import br.com.rinha.backend2.cliente.Cliente;
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
    @ManyToOne
    private Cliente cliente;

    public Transacao(NovaTransaoRequest request, Cliente cliente) {
        this.valor = request.valor();
        this.tipo = request.tipo();
        this.descricao = request.descricao();
        this.realizadaEm = LocalDateTime.now();
        this.cliente = cliente;
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

    public Cliente getCliente() {
        return cliente;
    }
}
