package br.com.cantina.cantina.service;

import br.com.cantina.cantina.model.Usuario;
import br.com.cantina.cantina.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.Optional;

@Service

public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder){
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<Usuario> buscarPorLogin(String login){
        return usuarioRepository.findByLogin(login);
    }

    public boolean podeFazerPedido(Usuario usuario){

        return usuario.isAtivo() && (usuario.getAnoConclusao() == null || usuario.getAnoConclusao() >= Year.now().getValue());

    }

    public Usuario cadastrar(Usuario usuario, String senhaPura){

        if(usuarioRepository.existsByLogin(usuario.getLogin())){
            throw new IllegalArgumentException("ERRO: Usuário já existe!");
        }

        usuario.setSenhaHash(passwordEncoder.encode(senhaPura));

        return usuarioRepository.save(usuario);

    }

    public Usuario autenticar(String login, String senhaPura){
        Usuario usuario = usuarioRepository.findByLogin(login).orElseThrow(() -> new IllegalArgumentException("ERRO: RA ou senha inválidos!"));

        if(!passwordEncoder.matches(senhaPura, usuario.getSenhaHash())) {
            throw new IllegalArgumentException("ERRO: RA ou senha inválidos!");
        }
        return usuario;
    }


    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
    }



}
