package com.deveficiente.cdc.cadastrocategoria;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
public class CategoriasController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping("/categorias")
    @Transactional
    public String criar(@RequestBody @Valid NovaCategoriaRequest request) {

        final Categoria novaCategoria = new Categoria(request.getNome());
        manager.persist(novaCategoria);
        return novaCategoria.toString();
    }
}
