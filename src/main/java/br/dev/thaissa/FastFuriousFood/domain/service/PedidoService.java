
package br.dev.thaissa.FastFuriousFood.domain.service;

import br.dev.thaissa.FastFuriousFood.domain.model.Pedido;
import br.dev.thaissa.FastFuriousFood.domain.model.StatusPedido;
import br.dev.thaissa.FastFuriousFood.domain.repository.PedidoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.dev.thaissa.FastFuriousFood.domain.model.ItensPedido;


@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }

    public Pedido buscarOuFalhar(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }

    public Pedido atualizar(Long id, Pedido pedido) {
        if (!pedidoRepository.existsById(id)) {
            throw new RuntimeException("Pedido não encontrado");
        }

        pedido.setId(id);
        return pedidoRepository.save(pedido);
    }

    public Pedido cancelar(Long id) {
        Pedido pedido = buscarOuFalhar(id);
        pedido.setStatus(StatusPedido.CANCELADO);
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> buscarPorStatus(StatusPedido status) {
        return pedidoRepository.findByStatus(status);
    }

    public Pedido atualizarStatus(Long id, StatusPedido status) {
        Pedido pedido = buscarOuFalhar(id);
        pedido.setStatus(status);
        return pedidoRepository.save(pedido);
    }
    
    public Pedido criar(Pedido pedido) {
        Long numero = pedidoRepository.count() + 1;
    
        pedido.setNumero(numero.intValue());
        pedido.setStatus(StatusPedido.ABERTO);
        
        //vincular itens ao pedido
        if (pedido.getItens() != null){
            pedido.getItens().forEach(item -> {
                item.setPedido(pedido);
        
        if (item.getProduto() != null) {
            item.setPrecoUnitario(item.getProduto().getPreco());
        }
    });
}
    
        return pedidoRepository.save(pedido);
    }
}
