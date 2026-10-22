package br.com.cantina.cantina.controller;

import br.com.cantina.cantina.model.Usuario;
import br.com.cantina.cantina.model.enums.Papel;
import br.com.cantina.cantina.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @GetMapping("/login")
    public String telaLogin(){
        return "login";
    }

    @PostMapping("/login")
    public String autenticar(@RequestParam String login, @RequestParam String senha, HttpSession session, RedirectAttributes redirect){
        try{
            Usuario usuario = usuarioService.autenticar(login, senha);
            session.setAttribute("usuarioId", usuario.getId());
            return "redirect:/cardapio";
        } catch (RuntimeException e) {
            redirect.addFlashAttribute("erro", e.getMessage());
            return "redirect:/login";
        }
    }

    @GetMapping("/cadastro")
    public String telaCadastro() {
        return "cadastro";
    }

    @PostMapping("/cadastro")
    public String cadastrar(@RequestParam String nome, @RequestParam String turma, @RequestParam Integer anoConclusao, @RequestParam String login, @RequestParam String senha, RedirectAttributes redirect){

        try{
            Usuario usuario = new Usuario(nome, turma, anoConclusao, login, "", Papel.ALUNO, true);
            usuarioService.cadastrar(usuario, senha);
            redirect.addFlashAttribute("sucesso", "Cadastro realizado! Faça login para continuar");
            return "redirect:/login";
        } catch (RuntimeException e) {
            redirect.addFlashAttribute("erro", e.getMessage());
            return "redirect:/cadastro";
        }

    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

}
