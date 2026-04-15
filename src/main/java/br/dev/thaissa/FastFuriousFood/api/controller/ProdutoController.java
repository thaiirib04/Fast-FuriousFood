
package br.dev.thaissa.FastFuriousFood.api.controller;

import br.dev.thaissa.FastFuriousFood.domain.model.Produto;
import br.dev.thaissa.FastFuriousFood.domain.repository.ProdutoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    
    @GetMapping("/produto")
    public List<Produto> lista() {
        return produtoRepository.findAll();
    }
    
    @GetMapping("/produto/{id}")
    public ResponseEntity<Produto> buscar(@PathVariable Long id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        if (produto.isPresent()) {
            return ResponseEntity.ok(produto.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/produto")
    @ResponseStatus(HttpStatus.CREATED)
    public Produto adicionar (@RequestBody Produto produto){
        return produtoRepository.save(produto);
    }
    
    @PutMapping("/produto/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable Long id,
                                             @RequestBody Produto produto){
        if (!produtoRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        
        produto.setId(id);
        produto = produtoRepository.save(produto);
        return ResponseEntity.ok(produto);
    }
}
