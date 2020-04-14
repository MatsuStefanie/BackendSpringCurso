package com.cristal.stefanie.cursomc.repositores;

import com.cristal.stefanie.cursomc.domain.Categoria;
import com.cristal.stefanie.cursomc.domain.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    @Transactional(readOnly = true)
    @Query("SELECT distinct obj from Produto obj inner join obj.categorias cat where obj.nome Like %:nome% and cat in :categorias")
    Page<Produto> search(@Param("nome")String nome, @Param("categorias") List<Categoria> categorias, Pageable pageRequest);
}