package br.com.rinha.backend2.cliente;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private BigDecimal limite;
    private BigDecimal saldoInicial;

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
}
