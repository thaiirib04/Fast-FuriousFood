package br.dev.thaissa.FastFuriousFood.api.controller;

import br.dev.thaissa.FastFuriousFood.domain.model.Pedido;
import br.dev.thaissa.FastFuriousFood.domain.model.StatusPedido;
import br.dev.thaissa.FastFuriousFood.domain.service.PedidoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import br.dev.thaissa.FastFuriousFood.api.dto.StatusDTO;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/fastfurious/pedido")
public class PedidoController {
    
    @Autowired
    private PedidoService pedidoService;
        
    @GetMapping
    public List<Pedido> listar(){
       return pedidoService.listar();
      
    }
    
    @GetMapping("/{id}")
    public Pedido buscar(@PathVariable Long id) {
        return pedidoService.buscarOuFalhar(id);
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pedido criar (@Valid @RequestBody Pedido pedido){
        return pedidoService.criar(pedido);
    }
    
    @PutMapping("/{id}")
    public Pedido atualizar(@Valid @PathVariable Long id,
                            @RequestBody Pedido pedido) {
        return pedidoService.atualizar(id, pedido);
    }
    
    // CANCELAR (não deletar!)
    @DeleteMapping("/{id}")
    public Pedido cancelar(@PathVariable Long id) {
        return pedidoService.cancelar(id);
    }
    
    @GetMapping("/status/{status}")
    public List<Pedido> buscarPorStatus(@PathVariable StatusPedido status){
        return pedidoService.buscarPorStatus(status);
    }
    
    @PutMapping("/status/{id}")
    public Pedido atualizarStatus(@PathVariable Long id,
                                 @RequestBody StatusDTO dto) {
        return pedidoService.atualizarStatus(id, dto.getStatus());
    }
}
