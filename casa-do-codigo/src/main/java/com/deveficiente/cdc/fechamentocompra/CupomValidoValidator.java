package com.deveficiente.cdc.fechamentocompra;


import com.deveficiente.cdc.cadastrocupom.Cupom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class CupomValidoValidator implements Validator {


    private final CupomRepository cupomRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        // a classe passada como argumento é igual ou filha de NovaCompraRequest
        return NovaCompraRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        NovaCompraRequest request = (NovaCompraRequest) target;
        Optional<String> possivelCodigo = request.getCodigoCupom();

        if (possivelCodigo.isPresent()) {
            Cupom cupom = cupomRepository.getByCodigo(possivelCodigo.get());
            if (!cupom.valido()){
                errors.rejectValue("codigoCupom", null, "Este cupom não é mais válido");
            }
        }
    }
}
