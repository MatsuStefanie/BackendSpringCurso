package com.cristal.stefanie.cursomc.services;

import com.cristal.stefanie.cursomc.domain.PagamentoComBoleto;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class BoletoService {
    public static void preencherPagamentoComBoleto(PagamentoComBoleto pagamentoComBoleto, Date instanteDoPedido){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(instanteDoPedido);
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        pagamentoComBoleto.setDataVencimento(calendar.getTime());

    }
}
