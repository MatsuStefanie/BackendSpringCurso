package com.cristal.stefanie.cursomc.resources;

import com.cristal.stefanie.cursomc.domain.Produto;
import com.cristal.stefanie.cursomc.dto.ProdutoDTO;
import com.cristal.stefanie.cursomc.resources.utils.URL;
import com.cristal.stefanie.cursomc.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResources {

    @Autowired
    private ProdutoService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id) {
        Produto obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> findPage(
            @RequestParam(value = "nome", defaultValue = "") String nome,
            @RequestParam(value = "categorias") List<Integer> categorias,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy) {
        String nomeDecode = URL.decodeParam(nome);
//        List<Integer> ids = URL.decodeIntList(categorias);
        Page<Produto> pages = service.search(nome, categorias, page, linesPerPage, direction, orderBy);
//        Page<Produto> pages = service.search(nome, ids, page, linesPerPage, direction, orderBy);
        Page<ProdutoDTO> pagesDTO = pages.map(obj -> new ProdutoDTO(obj));
        return ResponseEntity.ok().body(pagesDTO);
    }
}
