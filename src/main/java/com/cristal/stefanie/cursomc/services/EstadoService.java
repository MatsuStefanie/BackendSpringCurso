package com.cristal.stefanie.cursomc.services;

import com.cristal.stefanie.cursomc.domain.Estado;
import com.cristal.stefanie.cursomc.repositores.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoService {
    @Autowired
    private EstadoRepository repository;

    public List<Estado> findAll(){
        return repository.findAllByOrderByNome();
    }
}
