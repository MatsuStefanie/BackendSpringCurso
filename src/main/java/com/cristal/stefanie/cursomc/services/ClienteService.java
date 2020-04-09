package com.cristal.stefanie.cursomc.services;

import com.cristal.stefanie.cursomc.domain.Cliente;
import com.cristal.stefanie.cursomc.dto.ClienteDTO;
import com.cristal.stefanie.cursomc.repositores.ClienteRepository;
import com.cristal.stefanie.cursomc.services.exceptions.DataIntregrityException;
import com.cristal.stefanie.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;


    public Cliente find(Integer id) {
        Optional<Cliente> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }

    public Cliente update(Cliente obj) {
        Cliente newOBJ = find(obj.getId());
        updateData(newOBJ, obj);
        return repo.save(newOBJ);
    }

    public void delete(Integer id) {
        find(id);
        try {
            repo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntregrityException("não é possivel excluir um cliente que possui outras informações atreladas");
        }
    }

    public List<Cliente> findAll() {
        return repo.findAll();
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    public Cliente fromDTO(ClienteDTO objDTO) {
        return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null);
    }
    private void updateData(Cliente newObj, Cliente obj){
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
    }
}
