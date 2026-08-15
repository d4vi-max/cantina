package br.com.cantina.cantina.controller;

import br.com.cantina.cantina.service.ProdutoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class CardapioController {

    private final ProdutoService produtoService;

    public CardapioController (ProdutoService produtoService){
        this.produtoService = produtoService;
    }

    @GetMapping("/cardapio")
    public String mostrarCardapio (Model model){
        model.addAttribute("produtos", produtoService.listarDisponiveis());
        return "cardapio";
    }


}
