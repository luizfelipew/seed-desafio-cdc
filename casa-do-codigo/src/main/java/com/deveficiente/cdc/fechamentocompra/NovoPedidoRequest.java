package com.deveficiente.cdc.fechamentocompra;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class NovoPedidoRequest {

    @Positive
    @NotNull
    private BigDecimal total;
    @Size(min = 1)
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

    public Function<Compra, Pedido> toModel(EntityManager manager) {
        Set<ItemPedido> itensCalculados = itens.stream()
                .map(item -> item.toModel(manager))
                .collect(Collectors.toSet());

        return (compra) -> {
            Pedido pedido = new Pedido(compra, itensCalculados);
            Assert.isTrue(pedido.totalIgual(total), "O total enviado não corresponde ao total real");
            return pedido;
        };
    }
}
