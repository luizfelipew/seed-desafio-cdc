package com.deveficiente.cdc.novoautor;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

//3
@RestController
public class AutoresController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping("/autores")
    @Transactional
    //1
    //2
    public String cria(@RequestBody @Valid NovoAutorRequest request) {
        //1
        Autor autor = request.toModel();
        manager.persist(autor);
        return autor.toString();
    }
}
