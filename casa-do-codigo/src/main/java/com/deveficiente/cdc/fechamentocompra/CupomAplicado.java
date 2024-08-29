package com.deveficiente.cdc.fechamentocompra;

import com.deveficiente.cdc.cadastrocupom.Cupom;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

@Embeddable
public class CupomAplicado {

    @ManyToOne
    private Cupom cupom;

    @Positive
    @NotNull
    private BigDecimal percentualDescontoMomento;

    @NotNull
    @Future
    private LocalDate validadeMomento;

    public CupomAplicado() {
    }

    public CupomAplicado(Cupom cupom) {
        this.cupom = cupom;
        this.percentualDescontoMomento = cupom.getPercentualDesconto();
        this.validadeMomento = cupom.getValidade();
    }

    @Override
    public String toString() {
        return "CupomAplicado{" +
                "cupom=" + cupom +
                ", percentualDescontoMomento=" + percentualDescontoMomento +
                ", validadeMomento=" + validadeMomento +
                '}';
    }
}
