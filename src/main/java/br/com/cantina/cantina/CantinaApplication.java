package br.com.cantina.cantina;

import br.com.cantina.cantina.model.Produto;
import br.com.cantina.cantina.repository.ProdutoRepository;
import br.com.cantina.cantina.service.ProdutoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@SpringBootApplication

public class CantinaApplication implements CommandLineRunner {

    final ProdutoService produtoService;

    public CantinaApplication (ProdutoService produtoService){
        this.produtoService = produtoService;
    }

    public static void main(String[] args) {
        SpringApplication.run(CantinaApplication.class, args);



    }

    @Override
    public void run(String... args){
        List<Produto> todosProdutos = produtoService.listarDisponiveis();

        for(Produto p : todosProdutos){
            if(p.isAtivo() && p.getEstoqueDia() >0){
                System.out.println(p.getNome());
            }
        }
    }


}

