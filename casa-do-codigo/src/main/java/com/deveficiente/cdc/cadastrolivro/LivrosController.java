package com.deveficiente.cdc.cadastrolivro;

import lombok.val;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class LivrosController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping("/livros")
    @Transactional
    // 1
    public String cria(@RequestBody @Valid NovoLivroRequest request) {
        // 1
        val novoLivro = request.toModel(manager);
        manager.persist(novoLivro);
        return novoLivro.toString();
    }

    @GetMapping("/livros")
    // 1
    public List<LivroResponse> listar() {
        final LivroResponse livroResponse = new LivroResponse();
        final List<Livro> consulta = manager.createQuery("select l from Livro l", Livro.class).getResultList();
        // 1
        val listLivros = consulta.stream()
            .map(livro -> {
                livroResponse.setId(livro.getId());
                livroResponse.setNome(livro.getTitulo());
                return livroResponse;
            })
            .collect(Collectors.toList());

        return listLivros;
    }
}
