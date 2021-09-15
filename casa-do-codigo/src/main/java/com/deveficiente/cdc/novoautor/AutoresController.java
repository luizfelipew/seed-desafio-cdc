package com.deveficiente.cdc.novoautor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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

    @Autowired
    private ProibeEmailDuplicadoAutorAutorValidator proibeEmailDuplicadoAutorAutorValidator;

    @InitBinder
    public void init(WebDataBinder binder) {
        // 1
        binder.addValidators(proibeEmailDuplicadoAutorAutorValidator);
    }

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
