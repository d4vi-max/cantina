package br.com.cantina.cantina.repository;

import br.com.cantina.cantina.model.Pedido;
import br.com.cantina.cantina.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Repository
public interface PedidoRepository extends JpaRepository <Pedido, Long> {

    List<Pedido> findByDataRetirada (LocalDate dataRetirada);

    List<Pedido> findByDataRetiradaAndStatus (LocalDate dataRetirada, Status status);

    Optional<Pedido> findByDataRetiradaAndCodigoRetirada (LocalDate dataRetirada, String codigoRetirada);


}
