package com.cristal.stefanie.cursomc.services;

import com.cristal.stefanie.cursomc.domain.ItemPedido;
import com.cristal.stefanie.cursomc.domain.PagamentoComBoleto;
import com.cristal.stefanie.cursomc.domain.Pedido;
import com.cristal.stefanie.cursomc.domain.enuns.EstadoPagamento;
import com.cristal.stefanie.cursomc.repositores.ItemPedidoRepository;
import com.cristal.stefanie.cursomc.repositores.PagamentoRepository;
import com.cristal.stefanie.cursomc.repositores.PedidoRepository;
import com.cristal.stefanie.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repo;
    @Autowired
    private BoletoService boletoService;
    @Autowired
    private ProdutoService service;
    @Autowired
    private PagamentoRepository pagamentoRepository;
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public Pedido find(Integer id) {
        Optional<Pedido> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
    }

    @Transactional
    public Pedido insert(Pedido obj) {
        obj.setId(null);
        obj.setInstante(new Date());
        obj.getPagamento().setEstadoPagamento(EstadoPagamento.PENDENTE);
        obj.getPagamento().setPedido(obj);
        if (obj.getPagamento() instanceof PagamentoComBoleto) {
            PagamentoComBoleto pagamentoComBoleto = (PagamentoComBoleto) obj.getPagamento();
            boletoService.preencherPagamentoComBoleto(pagamentoComBoleto, obj.getInstante());
        }
        obj = repo.save(obj);
        pagamentoRepository.save(obj.getPagamento());

        for (ItemPedido itemPedido : obj.getItens()) {
            itemPedido.setDesconto(0.0);
            itemPedido.setPreco(service.find(itemPedido.getProduto().getId()).getPreco());
            itemPedido.setPedido(obj);
        }
        itemPedidoRepository.saveAll(obj.getItens());
        return obj;
    }
}
