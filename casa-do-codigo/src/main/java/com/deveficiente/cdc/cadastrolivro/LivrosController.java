package com.deveficiente.cdc.cadastrolivro;

import lombok.val;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
public class LivrosController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping("/livros")
    @Transactional
    // 1
    public String cria(@RequestBody @Valid NovoLivroRequest request){
        // 1
        val novoLivro = request.toModel(manager);
        manager.persist(novoLivro);
        return novoLivro.toString();
    }

}
