package com.deveficiente.cdc.fechamentocompra;

import com.deveficiente.cdc.cadastrocupom.Cupom;
import com.deveficiente.cdc.paisestado.Estado;
import com.deveficiente.cdc.paisestado.Pais;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.function.Function;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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

    @OneToOne(mappedBy = "compra",cascade = CascadeType.PERSIST)
    private Pedido pedido;

    @Embedded
    private CupomAplicado cupomAplicado;

    public Compra(@Email @NotBlank String email, @NotBlank String nome, @NotBlank String sobrenome,
                  @NotBlank String documento, @NotBlank String endereco, @NotBlank String complemento,
                  @NotNull Pais pais, @NotBlank String telefone, @NotBlank String cep,
                  Function<Compra, Pedido> funcaoCriacaoPedido) {

        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.documento = documento;
        this.endereco = endereco;
        this.complemento = complemento;
        this.pais = pais;
        this.telefone = telefone;
        this.cep = cep;
        this.pedido = funcaoCriacaoPedido.apply(this);
    }

    @Deprecated
    public Compra() {

    }

    public void setEstado(@NotNull @Valid Estado estado) {
        Assert.notNull(pais, "Não rola associar um estado enquanto o país for nulo");
        Assert.isTrue(estado.pertenceAPais(pais), "Este estado não é do país associado a compra");
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Compra{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", documento='" + documento + '\'' +
                ", endereco='" + endereco + '\'' +
                ", complemento='" + complemento + '\'' +
                ", pais=" + pais +
                ", estado=" + estado +
                ", telefone='" + telefone + '\'' +
                ", cep='" + cep + '\'' +
                ", pedido=" + pedido +
                '}';
    }

    public void aplicaCupom(Cupom cupom) {
        Assert.isTrue(cupom.valido(), "Olha o cupom que está sendo aplicado não está mais valido");
        Assert.isNull(cupomAplicado, "Olha você não pode trocar um cupom de uma compra");
        this.cupomAplicado = new CupomAplicado(cupom);
    }
}
