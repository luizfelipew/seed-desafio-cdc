package com.deveficiente.cdc.cadastrolivro;

import com.deveficiente.cdc.cadastrocategoria.Categoria;
import com.deveficiente.cdc.compartilhado.ExistsId;
import com.deveficiente.cdc.compartilhado.UniqueValue;
import com.deveficiente.cdc.novoautor.Autor;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

public class NovoLivroRequest {

    @NotBlank
    @UniqueValue(domainClass = Livro.class, fieldName = "titulo")
    private String titulo;
    @NotBlank
    @Size(max = 500)
    private String resumo;
    @NotBlank
    private String sumario;
    @NotNull
    @Min(20)
    private BigDecimal preco;
    @Min(100)
    private int numeroPaginas;
    @NotBlank
    @UniqueValue(domainClass = Livro.class, fieldName = "isbn")
    private String isbn;
    @NotNull
    @Future
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataPublicacao;
    @NotNull
    @ExistsId(domainClass = Categoria.class, fieldName = "id")
    private Long idCategoria;
    @NotNull
    @ExistsId(domainClass = Autor.class, fieldName = "id")
    private Long idAutor;


    public NovoLivroRequest(@NotBlank String titulo, @NotBlank @Size(max = 500) String resumo,
                            @NotBlank String sumario, @NotNull @Min(20) BigDecimal preco, @Min(100) int numeroPaginas,
                            @NotBlank String isbn, @Future @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy") LocalDate dataPublicacao,
                            @NotNull Long idCategoria, @NotNull Long idAutor) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numeroPaginas = numeroPaginas;
        this.isbn = isbn;
        this.dataPublicacao = dataPublicacao;
        this.idCategoria = idCategoria;
        this.idAutor = idAutor;
    }

    public Livro toModel(EntityManager manager) {
        @NotNull Autor autor = manager.find(Autor.class, idAutor);
        @NotNull Categoria categoria = manager.find(Categoria.class, idCategoria);

        Assert.state(autor != null, "Você esta querendo cadastrar um livro para um autor que nao existe no banco:  " + idAutor);
        Assert.state(categoria != null, "Você esta querendo cadastrar um livro para uma categoria que nao existe no banco:  " + idCategoria);


        return new Livro(titulo, resumo, sumario, preco, numeroPaginas,
            isbn, dataPublicacao, autor, categoria);
    }

}
