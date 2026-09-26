package br.com.cantina.cantina.service;

import br.com.cantina.cantina.model.Usuario;
import br.com.cantina.cantina.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.Optional;

@Service

public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario addUsuario(Usuario usuario){

        if(usuarioRepository.existsByLogin(usuario.getLogin())){
            throw new IllegalArgumentException("ERRO: Usuário já existe!");
        }

        return usuarioRepository.save(usuario);

    }

    public Optional<Usuario> buscarPorLogin(String login){
        return usuarioRepository.findByLogin(login);
    }

    public boolean podeFazerPedido(Usuario usuario){

        return usuario.isAtivo() && (usuario.getAnoConclusao() == null || usuario.getAnoConclusao() >= Year.now().getValue());

    }






}
