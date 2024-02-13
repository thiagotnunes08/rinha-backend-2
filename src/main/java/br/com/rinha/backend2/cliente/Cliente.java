package br.com.rinha.backend2.cliente;

import br.com.rinha.backend2.transacao.Transacao;
import br.com.rinha.backend2.transacao.TransacaoResponse;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
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

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Transacao> transacaos = new ArrayList<>();

    public boolean naoTemLimite(BigDecimal valor) {
        return this.limite.compareTo(valor) < 0;
    }

    public void atualizaSaldo(BigDecimal valor) {
        this.saldoInicial = saldoInicial.subtract(valor);
    }

    public BigDecimal getSaldo() {
        return saldoInicial;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public void adicionaTransacao(Transacao transacao){
        this.transacaos.add(transacao);
    }

    public List<TransacaoResponse> mapToResponse() {
       return this.transacaos.stream().map(TransacaoResponse::new).collect(Collectors.toList());
    }
}
