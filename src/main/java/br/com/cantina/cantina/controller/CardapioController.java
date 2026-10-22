package br.com.cantina.cantina.controller;

import br.com.cantina.cantina.dto.ItemCarrinhoDTO;
import br.com.cantina.cantina.dto.PedidoFormDTO;
import br.com.cantina.cantina.model.JanelaRetirada;
import br.com.cantina.cantina.model.Pedido;
import br.com.cantina.cantina.model.Usuario;
import br.com.cantina.cantina.service.JanelaRetiradaService;
import br.com.cantina.cantina.service.PedidoService;
import br.com.cantina.cantina.service.ProdutoService;
import br.com.cantina.cantina.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller

public class CardapioController {

    private final ProdutoService produtoService;
    private final JanelaRetiradaService janelaRetiradaService;
    private final UsuarioService usuarioService;
    private final PedidoService pedidoService;

    public CardapioController (ProdutoService produtoService, JanelaRetiradaService janelaRetiradaService, UsuarioService usuarioService, PedidoService pedidoService){
        this.produtoService = produtoService;
        this.janelaRetiradaService = janelaRetiradaService;
        this.usuarioService = usuarioService;
        this.pedidoService = pedidoService;
    }

    @GetMapping("/cardapio")
    public String mostrarCardapio (Model model, HttpSession session){

        if (session.getAttribute("usuarioId") == null) {
            return "redirect:/login";
        }

        model.addAttribute("produtos", produtoService.listarDisponiveis());
        model.addAttribute("janelas", janelaRetiradaService.listarAtivas());
        model.addAttribute("form", new PedidoFormDTO());
        return "cardapio";
    }

    @PostMapping("/pedido")
    public String criarPedido(@ModelAttribute PedidoFormDTO form, RedirectAttributes redirect, HttpSession session) {

        if (session.getAttribute("usuarioId") == null) {
            return "redirect:/login";
        }

        try {
            List<ItemCarrinhoDTO> itensValidos = new ArrayList<>();

            for(ItemCarrinhoDTO dto : form.getItens()){
                if(dto.getQuantidade() > 0){
                    itensValidos.add(dto);
                }
            }

            Long usuarioId = (Long) session.getAttribute("usuarioId");
            Usuario usuario = usuarioService.buscarPorId(usuarioId);

            JanelaRetirada janela = janelaRetiradaService.buscarPorId(form.getJanelaId());

            Pedido pedido = pedidoService.criarPedido(usuario, janela, LocalDate.now(), itensValidos);
            return "redirect:/pedido/" + pedido.getId();
        } catch (RuntimeException e){
            redirect.addFlashAttribute("erro", e.getMessage());
            return "redirect:/cardapio";
        }


    }

    @GetMapping("/pedido/{id}")
    public String cerPedido(@PathVariable Long id, Model model, HttpSession session){

        if (session.getAttribute("usuarioId") == null) {
            return "redirect:/login";
        }

        model.addAttribute("pedido", pedidoService.buscarPorId(id));
        return "pedido";
    }

    @GetMapping("/")
    public String raiz() {
        return "redirect:/cardapio";
    }
}
