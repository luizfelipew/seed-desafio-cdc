package com.deveficiente.cdc.fechamentocompra;

import com.deveficiente.cdc.cadastrolivro.Livro;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Objects;

@Embeddable
public class ItemPedido {
    @ManyToOne
    private Livro livro;
    @Positive
    private int quantidade;
    @Positive
    private BigDecimal precoMomento;

    public ItemPedido(@NotNull Livro livro, @Positive int quantidade) {
        this.livro = livro;
        this.quantidade = quantidade;
        this.precoMomento = livro.getPreco();
    }

    @Deprecated
    public ItemPedido() {

    }

    public BigDecimal total() {
        return precoMomento.multiply(new BigDecimal(quantidade));
    }

    @Override
    public String toString() {
        return "ItemPedido{" +
                "livro=" + livro +
                ", quantidade=" + quantidade +
                ", precoMomento=" + precoMomento +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemPedido that = (ItemPedido) o;
        return quantidade == that.quantidade && Objects.equals(livro, that.livro) && Objects.equals(precoMomento, that.precoMomento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(livro, quantidade, precoMomento);
    }
}
