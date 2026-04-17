package br.dev.thaissa.FastFuriousFood.api.controller;

import br.dev.thaissa.FastFuriousFood.domain.model.Pedido;
import br.dev.thaissa.FastFuriousFood.domain.repository.PedidoRepository;
import java.util.List;
import java.util.Optional;
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
    private PedidoRepository pedidoRepository;
        
    @GetMapping("/pedido")
    public List<Pedido> lista(){
       return pedidoRepository.findAll();
      
    }
    
    @GetMapping("/pedido/{id}")
    public ResponseEntity<Pedido> buscar(@PathVariable Long id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        if (pedido.isPresent()) {
            return ResponseEntity.ok(pedido.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/pedido")
    @ResponseStatus(HttpStatus.CREATED)
    public Pedido adicionar (@RequestBody Pedido pedido){
        return pedidoRepository.save(pedido);
    }
    
    @PutMapping("/pedido/{id}")
    public ResponseEntity<Pedido> atualizar(@PathVariable Long id,
                                             @RequestBody Pedido pedido){
        if (!pedidoRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        
        pedido.setId(id);
        pedido = pedidoRepository.save(pedido);
        return ResponseEntity.ok(pedido);
    }
    
    @DeleteMapping("/pedido/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        
        if (!pedidoRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        
        pedidoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
