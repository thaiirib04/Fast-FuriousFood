
package br.dev.thaissa.FastFuriousFood.api.controller;

import br.dev.thaissa.FastFuriousFood.domain.model.Produto;
import br.dev.thaissa.FastFuriousFood.domain.repository.ProdutoRepository;
import jakarta.validation.Valid;
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
public class ProdutoController {
    
    @Autowired
    private ProdutoRepository produtoRepository;
    
    // GET/produto
    @GetMapping("/produto")
    public List<Produto> lista() {
        return produtoRepository.findAll();
    }
    
    // GET/produto/{id}
    @GetMapping("/produto/{id}")
    public ResponseEntity<Produto> buscar(@PathVariable Long id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        if (produto.isPresent()) {
            return ResponseEntity.ok(produto.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    // POST/produto
    @PostMapping("/produto")
    @ResponseStatus(HttpStatus.CREATED)
    public Produto adicionar (@Valid @RequestBody Produto produto){
        return produtoRepository.save(produto);
    }
    
    // PUT/produto/{id}
    @PutMapping("/produto/{id}")
    public ResponseEntity<Produto> atualizar(@Valid @PathVariable Long id,
                                             @RequestBody Produto produto){
        if (!produtoRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        
        produto.setId(id);
        produto = produtoRepository.save(produto);
        return ResponseEntity.ok(produto);
    }
    
    // DELETE/produto/{id}
    @DeleteMapping("/produto/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        
        if (!produtoRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        
        produtoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
    // GET/produto/cat/{categoria}
    @GetMapping("/produto/cat/{categoria}")
    public ResponseEntity<List<Produto>> buscarPorCategoria(@PathVariable String categoria){
        List<Produto> produto = produtoRepository.findByCategoriaIgnoreCase(categoria);
        
        if(produto.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(produto);
    }
}
