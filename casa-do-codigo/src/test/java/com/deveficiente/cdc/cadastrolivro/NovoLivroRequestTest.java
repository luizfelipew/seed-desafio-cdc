package com.deveficiente.cdc.cadastrolivro;

import com.deveficiente.cdc.cadastrocategoria.Categoria;
import com.deveficiente.cdc.novoautor.Autor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class NovoLivroRequestTest {

    private NovoLivroRequest request = new NovoLivroRequest("", "", "",
            BigDecimal.TEN, 100, "",
            LocalDate.now(), 1L, 1L);

    @Test
    @DisplayName("cria o livro categoria e autor estão cadastrados")
    void test1() throws Exception {
        EntityManager manager = mock(EntityManager.class);

        when(manager.find(Categoria.class, 1l))
                .thenReturn(new Categoria(""));

        when(manager.find(Autor.class, 1l))
                .thenReturn(new Autor("","",""));

        assertNotNull(request.toModel(manager));
    }

    @Test
    @DisplayName("não cria o livro caso o autor nao exista no banco")
    void test2() throws Exception {
        EntityManager manager = mock(EntityManager.class);

        when(manager.find(Categoria.class, 1l))
                .thenReturn(new Categoria(""));

        when(manager.find(Autor.class, 1l))
                .thenReturn(null);

        assertThrows(IllegalStateException.class,
                () -> request.toModel(manager));
    }
}