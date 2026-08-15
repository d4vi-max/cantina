package br.com.cantina.cantina;

import br.com.cantina.cantina.service.ProdutoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication

public class CantinaApplication {

    final ProdutoService produtoService;

    public CantinaApplication (ProdutoService produtoService){
        this.produtoService = produtoService;
    }

    public static void main(String[] args) {
        SpringApplication.run(CantinaApplication.class, args);



    }


}

