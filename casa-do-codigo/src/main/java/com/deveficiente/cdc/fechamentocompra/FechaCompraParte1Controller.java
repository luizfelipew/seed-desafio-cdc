package com.deveficiente.cdc.fechamentocompra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class FechaCompraParte1Controller {

    @Autowired
    private EstadoPertenceAPaisValidator estadoPertenceAPaisValidator;

    @PersistenceContext
    private EntityManager manager;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(new VerificaDocumentoCpfCnpjValidator(), estadoPertenceAPaisValidator);
    }

    @PostMapping("/compras")
    @Transactional
    public String cria(@RequestBody @Valid NovaCompraRequest request) {
        final Compra novaCompra = request.toModel(manager);
        manager.persist(novaCompra);
        return novaCompra.toString();
    }
}
