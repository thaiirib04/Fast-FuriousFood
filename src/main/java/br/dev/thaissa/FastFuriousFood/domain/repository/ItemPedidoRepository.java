
package br.dev.thaissa.FastFuriousFood.domain.repository;

import br.dev.thaissa.FastFuriousFood.domain.model.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {
    
}
