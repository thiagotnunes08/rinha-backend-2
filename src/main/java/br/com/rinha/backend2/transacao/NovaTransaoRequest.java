package br.com.rinha.backend2.transacao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

public record NovaTransaoRequest(@Positive @NotNull BigDecimal valor, @NotBlank @Length(max = 1) String tipo, @NotBlank @Length(max = 10) String descricao){

    public boolean ehDebito() {
        return this.tipo.equals("d");
    }
}
