package com.cristal.stefanie.cursomc.domain;

import com.cristal.stefanie.cursomc.domain.enuns.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonTypeId;
import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.persistence.Entity;

@Entity
@JsonTypeName("pagamentoComCartao")
//@JsonType("pagamentoComCartao")
public class PagamentoComCartao extends Pagamento {
    private static final long serialVersionUID = 1L;

    private Integer numeroDeParcelas;

    public PagamentoComCartao(){
    }

    public PagamentoComCartao(Integer id, EstadoPagamento estadoPagamento, Pedido pedido, Integer numeroDeParcelas) {
        super(id, estadoPagamento, pedido);
        this.numeroDeParcelas = numeroDeParcelas;
    }

    public Integer getNumeroDeParcelas() {
        return numeroDeParcelas;
    }

    public void setNumeroDeParcelas(Integer numeroDeParcelas) {
        this.numeroDeParcelas = numeroDeParcelas;
    }
}
