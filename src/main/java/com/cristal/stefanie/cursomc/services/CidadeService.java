package com.cristal.stefanie.cursomc.services;

import com.cristal.stefanie.cursomc.domain.Cidade;
import com.cristal.stefanie.cursomc.repositores.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository repo;

    public List<Cidade> findByEstado(Integer estadoId){
        return repo.findCidadesByEstadoId(estadoId);
    }
}
