package com.deveficiente.cdc.cadastrocategoria;

import com.deveficiente.cdc.compartilhado.UniqueValue;

import javax.validation.constraints.NotBlank;

public class NovaCategoriaRequest {

    @NotBlank
    @UniqueValue(domainClass = Categoria.class, fieldName = "nome")
    private String nome;

    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getNome() {
        return nome;
    }
}
