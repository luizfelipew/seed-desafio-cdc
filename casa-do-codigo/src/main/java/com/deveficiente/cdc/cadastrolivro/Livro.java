package com.deveficiente.cdc.cadastrolivro;

import com.deveficiente.cdc.cadastrocategoria.Categoria;
import com.deveficiente.cdc.novoautor.Autor;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
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
    private String isbn;
    @NotNull
    @Future
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataPublicacao;
    @NotNull
    @Valid
    @ManyToOne
    private Autor autor;
    @NotNull
    @Valid
    @ManyToOne
    private Categoria categoria;


    public Livro(@NotBlank String titulo, @NotBlank @Size(max = 500) String resumo,
                 @NotBlank String sumario, @NotNull @Min(20) BigDecimal preco, @Min(100) int numeroPaginas,
                 @NotBlank String isbn, @Future @NotNull LocalDate dataPublicacao,
                 @NotNull @Valid Autor autor, @NotNull @Valid Categoria categoria) {

        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numeroPaginas = numeroPaginas;
        this.isbn = isbn;
        this.dataPublicacao = dataPublicacao;
        this.autor = autor;
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Livro{");
        sb.append("id=").append(id);
        sb.append(", titulo='").append(titulo).append('\'');
        sb.append(", resumo='").append(resumo).append('\'');
        sb.append(", sumario='").append(sumario).append('\'');
        sb.append(", preco=").append(preco);
        sb.append(", numeroPaginas=").append(numeroPaginas);
        sb.append(", isbn='").append(isbn).append('\'');
        sb.append(", dataPublicacao=").append(dataPublicacao);
        sb.append(", autor=").append(autor);
        sb.append(", categoria=").append(categoria);
        sb.append('}');
        return sb.toString();
    }
}
