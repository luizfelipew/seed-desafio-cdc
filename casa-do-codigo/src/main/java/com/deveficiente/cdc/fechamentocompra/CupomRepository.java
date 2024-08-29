package com.deveficiente.cdc.fechamentocompra;

import com.deveficiente.cdc.cadastrocupom.Cupom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CupomRepository extends CrudRepository<Cupom, Long> {

    public Cupom getByCodigo(final String codigo);
}
