package com.deveficiente.cdc.cadastrocupom;

import com.deveficiente.cdc.compartilhado.UniqueValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Setter;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

import static com.fasterxml.jackson.annotation.JsonFormat.*;

public class NovoCuponRequest {

    @NotBlank
    @UniqueValue(domainClass = Cupom.class, fieldName = "codigo")
    private String codigo;

    @Positive
    @NotNull
    private BigDecimal percentualDesconto;

    @Setter
    @Future
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy", shape = Shape.STRING)
    private LocalDate validade;

    public NovoCuponRequest(String codigo, BigDecimal percentualDesconto) {
        super();
        this.codigo = codigo;
        this.percentualDesconto = percentualDesconto;
    }

    public Cupom toModel() {
        return new Cupom(codigo, percentualDesconto, validade);
    }
}
