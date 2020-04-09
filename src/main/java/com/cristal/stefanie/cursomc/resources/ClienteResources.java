package com.cristal.stefanie.cursomc.resources;

import com.cristal.stefanie.cursomc.domain.Cliente;
import com.cristal.stefanie.cursomc.dto.ClienteDTO;
import com.cristal.stefanie.cursomc.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResources {

    @Autowired
    private ClienteService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id) {
        Cliente obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }




    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDTO, @PathVariable Integer id) {
        Cliente obj = service.fromDTO(objDTO);
        obj.setId(id);
        obj = service.update(obj);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> findAll() {
        List<Cliente> list = service.findAll();
        List<ClienteDTO> listDTO = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<?> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, @RequestParam(value = "direction", defaultValue = "ASC") String direction, @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy) {
        Page<Cliente> pages = service.findPage(page, linesPerPage, direction, orderBy);
        Page<ClienteDTO> pagesDTO = pages.map(obj -> new ClienteDTO(obj));
        return ResponseEntity.ok().body(pagesDTO);
    }
}