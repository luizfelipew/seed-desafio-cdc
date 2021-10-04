package com.deveficiente.cdc.compartilhado;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.List;

@RestController
public class ListaTudo {

    @PersistenceContext
    private EntityManager manager;

    @GetMapping("/lista-tudo")
    public HashMap<String, Object> list(){

        final List autores = manager.createQuery("select a from Autor a").getResultList();

        HashMap<String, Object> resultado = new HashMap<>();
        resultado.put("autores", autores.toString());

        List calegorias = manager.createQuery("select c from Categoria c").getResultList();
        resultado.put("categorias", calegorias.toString());

        return resultado;
    }
}
