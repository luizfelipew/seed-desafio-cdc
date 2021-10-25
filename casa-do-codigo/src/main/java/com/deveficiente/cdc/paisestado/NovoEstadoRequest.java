package com.deveficiente.cdc.paisestado;


import com.deveficiente.cdc.compartilhado.ExistsId;
import com.deveficiente.cdc.compartilhado.UniqueValue;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NovoEstadoRequest {

    @NotBlank
    @UniqueValue(domainClass = Estado.class, fieldName = "nome")
    private String nome;

    @NotNull
    @ExistsId(domainClass = Pais.class, fieldName = "id")
    private Long idPais;

    public NovoEstadoRequest(@NotBlank String nome, @NotNull Long idPais) {
        this.nome = nome;
        this.idPais = idPais;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("NovoEstadoRequest{");
        sb.append("nome='").append(nome).append('\'');
        sb.append(", idPais=").append(idPais);
        sb.append('}');
        return sb.toString();
    }

    public Estado toModel(EntityManager manager) {
        return new Estado(nome, manager.find(Pais.class, idPais));
    }
}
