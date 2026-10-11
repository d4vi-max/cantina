package br.com.cantina.cantina.controller;

import br.com.cantina.cantina.model.enums.Status;
import br.com.cantina.cantina.service.PedidoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class CantinaController {

    private final PedidoService pedidoService;

    public CantinaController (PedidoService pedidoService){
        this.pedidoService = pedidoService;
    }

    @GetMapping("/cantina")
    public String painel(Model model){
        model.addAttribute("pendentes", pedidoService.listarPendentes(LocalDate.now()));
        model.addAttribute("finalizados", pedidoService.listarFinalizados(LocalDate.now()));
        return "cantina";
    }

    @PostMapping("/cantina/{id}/status")
    public String mudarStatus(@PathVariable Long id, @RequestParam Status novo) {
        pedidoService.mudarStatus(id, novo);
        return "redirect:/cantina";
    }

    @PostMapping("/cantina/{id}/cancelar")
    public String cancelar(@PathVariable Long id){
        pedidoService.cancelar(id);
        return "redirect:/cantina";
    }



}
