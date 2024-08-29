package com.deveficiente.cdc.compartilhado;

import lombok.val;
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

        val autores = manager.createQuery("select a from Autor a").getResultList();

        HashMap<String, Object> resultado = new HashMap<>();
        resultado.put("autores", autores.toString());

        val categorias = manager.createQuery("select c from Categoria c").getResultList();
        resultado.put("categorias", categorias.toString());

        val cupons = manager.createQuery("select c from Cupom c").getResultList();
        resultado.put("cupons", cupons.toString());

        return resultado;
    }
}
