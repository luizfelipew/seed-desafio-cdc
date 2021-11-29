package com.deveficiente.cdc.fechamentocompra;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class NovoPedidoRequest {

    @Positive
    @NotNull
    private BigDecimal total;
    @Size
    @Valid
    private List<NovoPedidoItensRequest> itens = new ArrayList<>();

    public NovoPedidoRequest(@Positive @NotNull BigDecimal total, @Size @Valid List<NovoPedidoItensRequest> itens) {
        this.total = total;
        this.itens = itens;
    }

    public List<NovoPedidoItensRequest> getItens() {
        return itens;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("NovoPedidoRequest{");
        sb.append("total=").append(total);
        sb.append(", itens=").append(itens);
        sb.append('}');
        return sb.toString();
    }
}
