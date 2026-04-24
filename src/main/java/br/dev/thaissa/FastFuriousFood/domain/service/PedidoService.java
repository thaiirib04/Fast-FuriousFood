
package br.dev.thaissa.FastFuriousFood.domain.service;

import br.dev.thaissa.FastFuriousFood.api.dto.ItemPedidoDTO;
import br.dev.thaissa.FastFuriousFood.api.dto.PedidoDTO;
import br.dev.thaissa.FastFuriousFood.domain.model.Pedido;
import br.dev.thaissa.FastFuriousFood.domain.model.StatusPedido;
import br.dev.thaissa.FastFuriousFood.domain.repository.PedidoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.dev.thaissa.FastFuriousFood.domain.model.ItemPedido;
import br.dev.thaissa.FastFuriousFood.domain.model.Produto;
import br.dev.thaissa.FastFuriousFood.domain.repository.ProdutoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


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

    public Pedido cancelar(Long id) {
        Pedido pedido = buscarOuFalhar(id);

        if (pedido.getStatus() == StatusPedido.ENTREGUE) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Pedido já entregue não pode ser cancelado");
        }

        pedido.setStatus(StatusPedido.CANCELADO);
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> buscarPorStatus(StatusPedido status) {
        return pedidoRepository.findByStatus(status);
    }
    
    
    //atualizar status com regra
    public Pedido atualizarStatus(Long id, StatusPedido novoStatus) {
        
        Pedido pedido = buscarOuFalhar(id);
        if (pedido.getStatus() == StatusPedido.ABERTO &&
                novoStatus == StatusPedido.PRONTO) {

            pedido.setStatus(novoStatus);

        } else if (pedido.getStatus() == StatusPedido.PRONTO &&
                novoStatus == StatusPedido.ENTREGUE) {

            pedido.setStatus(novoStatus);

        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Transição de status inválida");
        }

        return pedidoRepository.save(pedido);
    }
    
    @Autowired
    private ProdutoRepository produtoRepository;
    
    public Pedido criar(PedidoDTO dto) {

        Pedido novoPedido = new Pedido();
        novoPedido.setStatus(StatusPedido.ABERTO);

        for (ItemPedidoDTO itemDTO : dto.getItens()) {

            Produto produto = produtoRepository.findById(itemDTO.getProdutoId())
                    .orElseThrow(() ->
                            new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto não encontrado"));

            ItemPedido item = new ItemPedido();
            item.setProduto(produto);
            item.setQuantidade(itemDTO.getQuantidade());
            item.setPrecoUnitario(produto.getPreco());

            novoPedido.adicionarItem(item);
        }

        novoPedido = pedidoRepository.save(novoPedido);
        novoPedido.setNumero(novoPedido.getId().intValue());

        return pedidoRepository.save(novoPedido);
    }
    
    //atualizar
    public Pedido atualizar(Long id, Pedido pedidoAtualizado) {

    Pedido existente = buscarOuFalhar(id);

    if (pedidoAtualizado.getItens() == null || pedidoAtualizado.getItens().isEmpty()) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pedido sem itens");
    }

    existente.getItens().clear();

    for (ItemPedido item : pedidoAtualizado.getItens()) {

        if (item.getProduto() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto obrigatório");
        }

        item.setPrecoUnitario(item.getProduto().getPreco());
        existente.adicionarItem(item);
    }

        return pedidoRepository.save(existente);
    }
}


