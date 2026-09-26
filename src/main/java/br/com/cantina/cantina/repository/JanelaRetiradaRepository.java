package br.com.cantina.cantina.repository;

import br.com.cantina.cantina.model.JanelaRetirada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JanelaRetiradaRepository extends JpaRepository<JanelaRetirada, Long> {
}
