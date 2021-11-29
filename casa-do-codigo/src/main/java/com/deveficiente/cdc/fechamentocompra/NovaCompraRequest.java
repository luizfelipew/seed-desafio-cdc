package com.deveficiente.cdc.fechamentocompra;

import com.deveficiente.cdc.compartilhado.ExistsId;
import com.deveficiente.cdc.paisestado.Estado;
import com.deveficiente.cdc.paisestado.Pais;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NovaCompraRequest {

    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String nome;
    @NotBlank
    private String sobrenome;
    @NotBlank
    private String documento;
    @NotBlank
    private String endereco;
    @NotBlank
    private String complemento;
    @NotBlank
    private String cidade;
    @NotNull
    @ExistsId(domainClass = Pais.class, fieldName = "id")
    private Long idPais;
    @ExistsId(domainClass = Estado.class, fieldName = "id")
    private Long idEstado;
    @NotBlank
    private String telefone;
    @NotBlank
    private String cep;
    @Valid
    @NotNull
    private NovoPedidoRequest pedido;

    public NovaCompraRequest(@Email @NotBlank String email, @NotBlank String nome,
                             @NotBlank String sobrenome, @NotBlank String documento, @NotBlank String endereco,
                             @NotBlank String complemento, @NotBlank String cidade, @NotNull Long idPais, Long idEstado,
                             @NotBlank String telefone, @NotBlank String cep, @Valid @NotNull NovoPedidoRequest pedido) {
        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.documento = documento;
        this.endereco = endereco;
        this.complemento = complemento;
        this.cidade = cidade;
        this.idPais = idPais;
        this.idEstado = idEstado;
        this.telefone = telefone;
        this.cep = cep;
        this.pedido = pedido;
    }

    public NovoPedidoRequest getPedido() {
        return pedido;
    }

    public String getDocumento() {
        return documento;
    }

    public Long getIdPais() {
        return idPais;
    }

    public Long getIdEstado() {
        return idEstado;
    }

    public Compra toModel(EntityManager manager) {
        @NotNull Pais pais = manager.find(Pais.class, idPais);
        final Compra compra = new Compra(email, nome, sobrenome, documento, endereco, complemento, pais, telefone, cep);
        if (idEstado != null) {
            compra.setEstado(manager.find(Estado.class, idEstado));
        }
        return compra;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("NovaCompraRequest{");
        sb.append("email='").append(email).append('\'');
        sb.append(", nome='").append(nome).append('\'');
        sb.append(", sobrenome='").append(sobrenome).append('\'');
        sb.append(", documento='").append(documento).append('\'');
        sb.append(", endereco='").append(endereco).append('\'');
        sb.append(", complemento='").append(complemento).append('\'');
        sb.append(", cidade='").append(cidade).append('\'');
        sb.append(", idPais=").append(idPais);
        sb.append(", idEstado=").append(idEstado);
        sb.append(", telefone='").append(telefone).append('\'');
        sb.append(", cep='").append(cep).append('\'');
        sb.append(", pedido=").append(pedido);
        sb.append('}');
        return sb.toString();
    }

    public boolean documentoValido() {
        Assert.hasLength(documento, "voce nao deveria validar o documento se ele nao tiver sido preenchido");

        final CPFValidator cpfValidator = new CPFValidator();
        cpfValidator.initialize(null);
        final CNPJValidator cnpjValidator = new CNPJValidator();
        cnpjValidator.initialize(null);

        return cpfValidator.isValid(documento, null)
            || cnpjValidator.isValid(documento, null);
    }

    public boolean temEstado() {
        return idEstado != null;
    }
}
