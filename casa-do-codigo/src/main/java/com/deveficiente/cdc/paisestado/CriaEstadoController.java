package com.deveficiente.cdc.paisestado;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class CriaEstadoController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping("/estados")
    @Transactional
    public String cria(@RequestBody @Valid NovoEstadoRequest request) {
        final Estado novoEstado = request.toModel(manager);
        manager.persist(novoEstado);

        return novoEstado.toString();
    }
}
