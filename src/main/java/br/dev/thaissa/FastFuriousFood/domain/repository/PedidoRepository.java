
package br.dev.thaissa.FastFuriousFood.domain.repository;

import br.dev.thaissa.FastFuriousFood.domain.model.Pedido;
import br.dev.thaissa.FastFuriousFood.domain.model.StatusPedido;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido>findByStatus(StatusPedido status);
}
