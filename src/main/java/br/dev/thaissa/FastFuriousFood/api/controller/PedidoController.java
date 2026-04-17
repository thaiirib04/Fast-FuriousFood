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

@RestController
@RequestMapping("/fastfurious")
public class PedidoController {
    
    @Autowired
    private PedidoService pedidoService;
        
    @GetMapping("/pedido")
    public List<Pedido> lista(){
       return pedidoService.listar();
      
    }
    
    @GetMapping("/pedido/{id}")
    public ResponseEntity<Pedido> buscar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(pedidoService.buscarOuFalhar(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/pedido")
    @ResponseStatus(HttpStatus.CREATED)
    public Pedido adicionar (@RequestBody Pedido pedido){
        return pedidoService.criar(pedido);
    }
    
    @PutMapping("/pedido/{id}")
    public ResponseEntity<Pedido> atualizar(@PathVariable Long id,
                                             @RequestBody Pedido pedido){
        try {
            return ResponseEntity.ok(pedidoService.atualizar(id, pedido));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    // CANCELAR (não deletar!)
    @DeleteMapping("/pedido/{id}")
    public ResponseEntity<Pedido> cancelar(@PathVariable Long id){
        try {
            return ResponseEntity.ok(pedidoService.cancelar(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/pedido/status/{status}")
    public List<Pedido> buscarPorStatus(@PathVariable StatusPedido status){
        return pedidoService.buscarPorStatus(status);
    }
    
    @PutMapping("/pedido/status/{id}")
    public ResponseEntity<Pedido> atualizarStatus(@PathVariable Long id,
                                                  @RequestBody StatusPedido status){
        try {
            return ResponseEntity.ok(pedidoService.atualizarStatus(id, status));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
