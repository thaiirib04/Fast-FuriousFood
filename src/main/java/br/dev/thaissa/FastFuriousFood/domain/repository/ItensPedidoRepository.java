
package br.dev.thaissa.FastFuriousFood.domain.repository;

import br.dev.thaissa.FastFuriousFood.domain.model.ItensPedido;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ItensPedidoRepository extends JpaRepository<ItensPedido, Long> {
    
}
