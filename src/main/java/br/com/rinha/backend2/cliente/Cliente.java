package br.com.rinha.backend2.cliente;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private BigDecimal limite;
    private BigDecimal saldoInicial;
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
}
