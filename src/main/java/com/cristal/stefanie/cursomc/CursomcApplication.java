package com.cristal.stefanie.cursomc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CursomcApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
  /*produtoRepository.findAll().forEach(produtoRepository::delete);
        categoriaRepository.findAll().forEach(categoriaRepository::delete);
        clienteRepository.findAll().forEach(clienteRepository::delete);
        estadoRepository.findAll().forEach(estadoRepository::delete);
        enderecoRepository.findAll().forEach(enderecoRepository ::delete);
        pedidoRepository.findAll().forEach(pedidoRepository::delete);
        pagamentoRepository.findAll().forEach(pagamentoRepository::delete);*/