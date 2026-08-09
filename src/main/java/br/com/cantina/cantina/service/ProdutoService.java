package br.com.cantina.cantina.service;

import br.com.cantina.cantina.model.Produto;
import br.com.cantina.cantina.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class ProdutoService  {

    final ProdutoRepository produtoRepository;
    private boolean disponivel;

    public ProdutoService (ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> listarDisponiveis(){

        List<Produto> todosProdutos = produtoRepository.findAll();
        List<Produto> produtosValidos = new ArrayList<>();

        for(Produto p : todosProdutos){
            if(p.isAtivo() && p.getEstoqueDia() >0){
                produtosValidos.add(p);
            }
        }

        return produtosValidos;

    }


}
