package br.com.cantina.cantina.service;

import br.com.cantina.cantina.model.JanelaRetirada;
import br.com.cantina.cantina.repository.JanelaRetiradaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class JanelaRetiradaService {

    private final JanelaRetiradaRepository janelaRetiradaRepository;

    public JanelaRetiradaService(JanelaRetiradaRepository janelaRetiradaRepository){
        this.janelaRetiradaRepository = janelaRetiradaRepository;
    }

    public List<JanelaRetirada> listarAtivas(){

        List<JanelaRetirada> tudo = janelaRetiradaRepository.findAll();
        List<JanelaRetirada> todosAtivos = new ArrayList<>();

        for(JanelaRetirada j : tudo){
            if(j.isAtiva()){
                todosAtivos.add(j);
            }
        }

        return todosAtivos;

    }

}

