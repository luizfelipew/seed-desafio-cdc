package com.deveficiente.cdc.fechamentocompra;

import com.deveficiente.cdc.cadastrocupom.Cupom;
import com.deveficiente.cdc.compartilhado.ExistsId;
import com.deveficiente.cdc.paisestado.Estado;
import com.deveficiente.cdc.paisestado.Pais;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.function.Function;

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
    // 1
    private NovoPedidoRequest pedido;

    @ExistsId(domainClass = Cupom.class, fieldName = "codigo")
    private String codigoCupom;

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

    public void setCodigoCupom(String codigoCupom) {
        this.codigoCupom = codigoCupom;
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

    // 1
    public Compra toModel(EntityManager manager, CupomRepository cupomRepository) {
        @NotNull
        // 1
        Pais pais = manager.find(Pais.class, idPais);

        // 1
        Function<Compra, Pedido> funcaoCriacaoPedido = pedido.toModel(manager);

        // 1 função como argumento
        final Compra compra = new Compra(email, nome, sobrenome, documento, endereco,
                complemento, pais, telefone, cep, funcaoCriacaoPedido);
        // 1
        if (idEstado != null) {
            compra.setEstado(manager.find(Estado.class, idEstado));
        }

        // 1
        if (StringUtils.hasText(codigoCupom)) {
            Cupom cupom = cupomRepository.getByCodigo(codigoCupom);
            compra.aplicaCupom(cupom);
        }


        return compra;
    }

    @Override
    public String toString() {
        return "NovaCompraRequest{" +
                "email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", documento='" + documento + '\'' +
                ", endereco='" + endereco + '\'' +
                ", complemento='" + complemento + '\'' +
                ", cidade='" + cidade + '\'' +
                ", idPais=" + idPais +
                ", idEstado=" + idEstado +
                ", telefone='" + telefone + '\'' +
                ", cep='" + cep + '\'' +
                ", pedido=" + pedido +
                '}';
    }

    public boolean documentoValido() {
        Assert.hasLength(documento, "voce nao deveria validar o documento se ele nao tiver sido preenchido");

        final CPFValidator cpfValidator = new CPFValidator();
        cpfValidator.initialize(null);
        final CNPJValidator cnpjValidator = new CNPJValidator();
        cnpjValidator.initialize(null);

        // 1
        return cpfValidator.isValid(documento, null)
                || cnpjValidator.isValid(documento, null);
    }

    public boolean temEstado() {
        return idEstado != null;
    }

    public Optional<String> getCodigoCupom() {
        return Optional.ofNullable(codigoCupom);
    }
}
