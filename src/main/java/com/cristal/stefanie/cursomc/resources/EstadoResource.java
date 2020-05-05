package com.cristal.stefanie.cursomc.resources;

import com.cristal.stefanie.cursomc.domain.Cidade;
import com.cristal.stefanie.cursomc.domain.Estado;
import com.cristal.stefanie.cursomc.dto.CidadeDTO;
import com.cristal.stefanie.cursomc.dto.EstadoDTO;
import com.cristal.stefanie.cursomc.services.CidadeService;
import com.cristal.stefanie.cursomc.services.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/estados")
public class EstadoResource {

    @Autowired
    private EstadoService service;

    @Autowired
    private CidadeService serviceCidade;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<EstadoDTO>> findAll() {
        List<Estado> list = service.findAll();
        List<EstadoDTO> listDTO = list.stream().map(EstadoDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @RequestMapping(value = "/{estadoId}/cidades", method = RequestMethod.GET)
    public ResponseEntity<List<CidadeDTO>> findCidades(@PathVariable Integer estadoId) {
        List<Cidade> list = serviceCidade.findByEstado(estadoId);
        List<CidadeDTO> listDTO = list.stream().map(CidadeDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }
}
