package com.deveficiente.cdc.fechamentocompra;

import com.deveficiente.cdc.paisestado.Estado;
import com.deveficiente.cdc.paisestado.Pais;
import org.springframework.util.Assert;

import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Compra {

    private String email;
    private String nome;
    private String sobrenome;
    private String documento;
    private String endereco;
    private String complemento;
    @ManyToOne
    private @NotNull Pais pais;
    @ManyToOne
    private Estado estado;
    private String telefone;
    private String cep;

    public Compra(@Email @NotBlank String email, @NotBlank String nome, @NotBlank String sobrenome,
                  @NotBlank String documento, @NotBlank String endereco, @NotBlank String complemento,
                  @NotNull Pais pais, @NotBlank String telefone, @NotBlank String cep) {

        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.documento = documento;
        this.endereco = endereco;
        this.complemento = complemento;
        this.pais = pais;
        this.telefone = telefone;
        this.cep = cep;
    }

    public void setEstado(@NotNull @Valid Estado estado) {
        Assert.notNull(pais, "Não rola associar um estado enquanto o país for nulo");
        Assert.isTrue(estado.pertenceAPais(pais), "Este estado não é do país associado a compra");
        this.estado = estado;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Compra{");
        sb.append("email='").append(email).append('\'');
        sb.append(", nome='").append(nome).append('\'');
        sb.append(", sobrenome='").append(sobrenome).append('\'');
        sb.append(", documento='").append(documento).append('\'');
        sb.append(", endereco='").append(endereco).append('\'');
        sb.append(", complemento='").append(complemento).append('\'');
        sb.append(", pais=").append(pais);
        sb.append(", estado=").append(estado);
        sb.append(", telefone='").append(telefone).append('\'');
        sb.append(", cep='").append(cep).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
