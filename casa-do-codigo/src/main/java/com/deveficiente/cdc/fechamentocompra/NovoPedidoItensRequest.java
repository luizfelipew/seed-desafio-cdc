package com.deveficiente.cdc.fechamentocompra;

import com.deveficiente.cdc.cadastrolivro.Livro;
import com.deveficiente.cdc.compartilhado.ExistsId;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class NovoPedidoItensRequest {

    @NotNull
    @ExistsId(domainClass = Livro.class, fieldName = "id")
    private Long idLivro;
    @Positive
    private int quantidade;

    public NovoPedidoItensRequest(@NotNull Long idLivro, @Positive int quantidade) {
        this.idLivro = idLivro;
        this.quantidade = quantidade;
    }

    public Long getIdLivro() {
        return idLivro;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("NovoPedidoItensRequest{");
        sb.append("idLivro=").append(idLivro);
        sb.append(", quantidade=").append(quantidade);
        sb.append('}');
        return sb.toString();
    }
}
