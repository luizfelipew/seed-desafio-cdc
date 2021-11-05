package com.deveficiente.cdc.fechamentocompra;

import com.deveficiente.cdc.paisestado.Estado;
import com.deveficiente.cdc.paisestado.Pais;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class EstadoPertenceAPaisValidator implements Validator {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public boolean supports(Class<?> clazz) {
        return NovaCompraRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        NovaCompraRequest request = (NovaCompraRequest) target;

        final Pais pais = manager.find(Pais.class, request.getIdPais());
        final Estado estado = manager.find(Estado.class, request.getIdEstado());

        if (!estado.pertenceAPais(pais)){
            errors.rejectValue("idEstado", null, "esse estado nao e do pais selecionado");
        }
    }
}
