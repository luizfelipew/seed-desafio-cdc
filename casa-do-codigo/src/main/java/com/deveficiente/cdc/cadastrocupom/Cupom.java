package com.deveficiente.cdc.cadastrocupom;

import org.springframework.util.Assert;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Cupom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String codigo;
    @Positive
    @NotNull
    private BigDecimal percentualDesconto;
    @Future
    @NotNull
    private LocalDate validade;

    @Deprecated
    public Cupom() {

    }

    public Cupom(@NotBlank String codigo,
                 @Positive @NotNull BigDecimal percentualDesconto,
                 @FutureOrPresent @NotNull LocalDate validade) {
        Assert.isTrue(validade.compareTo(LocalDate.now()) >= 0,"A validade tem que ser no futuro");
        this.codigo = codigo;
        this.percentualDesconto = percentualDesconto;
        this.validade = validade;
    }

    public boolean valido() {
        return LocalDate.now().compareTo(this.validade) <= 0;
    }

    public BigDecimal getPercentualDesconto() {
        return percentualDesconto;
    }

    public LocalDate getValidade() {
        return validade;
    }

    @Override
    public String toString() {
        return "Cupom{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", percentualDesconto=" + percentualDesconto +
                ", validade=" + validade +
                '}';
    }
}
