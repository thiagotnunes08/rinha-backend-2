package br.com.rinha.backend2.cliente;

import br.com.rinha.backend2.transacao.NovaTransaoRequest;
import br.com.rinha.backend2.transacao.Transacao;
import br.com.rinha.backend2.transacao.TransacaoResponse;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private BigDecimal limite;
    private BigDecimal saldoInicial;
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,mappedBy = "cliente")
    private List<Transacao> transacoes = new ArrayList<>();

    public void creditaSaldo(BigDecimal valor) {
        this.saldoInicial = saldoInicial.add(valor);
    }

    public void debitaSaldo(BigDecimal valor) {
        this.saldoInicial = this.saldoInicial.subtract(valor);
    }

    public boolean execedeuLimite() {

        if (saldoInicial.compareTo(BigDecimal.ZERO) < 0) {

            return saldoInicial.multiply(new BigDecimal("-1")).compareTo(limite) > 0;
        }

        return saldoInicial.compareTo(limite) > 0;
    }

    public BigDecimal getSaldoInicial() {
        return saldoInicial;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public void adicionaTransacao(NovaTransaoRequest request) {
        this.transacoes.add(new Transacao(request,this));
    }

    public List<TransacaoResponse> getUltimasDezTransacoes() {
         return transacoes.stream()
                .sorted(Comparator
                        .comparing(Transacao::getRealizadaEm).reversed())
                .limit(10L)
                .map(TransacaoResponse::new)
                .collect(Collectors.toList());
    }
}
