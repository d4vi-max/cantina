package br.com.cantina.cantina.controller;

import br.com.cantina.cantina.model.enums.Status;
import br.com.cantina.cantina.service.PedidoService;
import br.com.cantina.cantina.service.ProdutoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;


@Controller
public class CantinaController {

    private final PedidoService pedidoService;
    private final ProdutoService produtoService;

    @Value("${cantina.senha}")
    private String senhaCantina;

    public CantinaController (PedidoService pedidoService, ProdutoService produtoService){
        this.pedidoService = pedidoService;
        this.produtoService = produtoService;
    }

    @GetMapping("/cantina")
    public String painel(Model model, HttpSession session){

        if (session.getAttribute("cantinaOk") == null) {
            return "redirect:/cantina/login";
        }

        model.addAttribute("pendentes", pedidoService.listarPendentes(LocalDate.now()));
        model.addAttribute("finalizados", pedidoService.listarFinalizados(LocalDate.now()));
        return "cantina";
    }

    @PostMapping("/cantina/{id}/status")
    public String mudarStatus(@PathVariable Long id, @RequestParam Status novo, HttpSession session) {

        if (session.getAttribute("cantinaOk") == null) {
            return "redirect:/cantina/login";
        }

        pedidoService.mudarStatus(id, novo);
        return "redirect:/cantina";
    }

    @PostMapping("/cantina/{id}/cancelar")
    public String cancelar(@PathVariable Long id, HttpSession session){

        if (session.getAttribute("cantinaOk") == null) {
            return "redirect:/cantina/login";
        }

        pedidoService.cancelar(id);
        return "redirect:/cantina";
    }

    @GetMapping("/cantina/login")
    public String telaLogin() {
        return "cantina-login";
    }

    @PostMapping("/cantina/login")
    public String autenticar(@RequestParam String senha, HttpSession session, RedirectAttributes redirect){
        if(senhaCantina.equals(senha)){
            session.setAttribute("cantinaOk", true);
            return "redirect:/cantina";
        }
        else {
            redirect.addFlashAttribute("erro", "Senha incorreta");
            return "redirect:/cantina/login";
        }
    }

    @GetMapping("/cantina/estoque")
    public String telaEstoque(Model model, HttpSession session){
        if(session.getAttribute("cantinaOk") == null){
            return "redirect:/cantina/login";
        }
        model.addAttribute("produtos", produtoService.listarTodos());
        return "estoque";
    }

    @PostMapping("/cantina/estoque")
    public String salvarEstoque(@RequestParam List<Long> produtoId, @RequestParam List<Integer> quantidade, HttpSession session){
        if(session.getAttribute("cantinaOk") == null){
            return "redirect:/cantina/login";
        }
        for(int i = 0; i < produtoId.size(); i++){
            produtoService.atualizarEstoque(produtoId.get(i), quantidade.get(i));
        }
        return "redirect:/cantina/estoque";
    }

}
