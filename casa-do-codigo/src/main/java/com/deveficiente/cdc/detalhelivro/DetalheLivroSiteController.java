package com.deveficiente.cdc.detalhelivro;

import com.deveficiente.cdc.cadastrolivro.Livro;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Objects;

@RestController
public class DetalheLivroSiteController {

    @PersistenceContext
    private EntityManager manager;

    @GetMapping("/produtos/{id}")
    public ResponseEntity<?> detalhe(@PathVariable("id") Long id) {

        // 1
        val livroBuscado = manager.find(Livro.class, id);
        // o find pode retornar nulo, entao tenho que tratar
        // 1
        if (Objects.isNull(livroBuscado)){
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            return ResponseEntity.notFound().build();
        }

        // 1
        DetalheSiteLivroResponse detalheSiteLivroResponse = new DetalheSiteLivroResponse(livroBuscado);
        return ResponseEntity
            .ok(detalheSiteLivroResponse);
    }
}
