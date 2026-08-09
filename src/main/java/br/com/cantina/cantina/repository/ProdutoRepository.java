package br.com.cantina.cantina.repository;

import br.com.cantina.cantina.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ProdutoRepository extends JpaRepository<Produto, Long> {



}
