
package br.dev.thaissa.FastFuriousFood.api.controller;

import br.dev.thaissa.FastFuriousFood.domain.model.Produto;
import br.dev.thaissa.FastFuriousFood.domain.service.ProdutoService;
import jakarta.validation.Valid;
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
public class ProdutoController {
    
    @Autowired
    private ProdutoService produtoService;
    
    // GET/produto
    @GetMapping("/produto")
    public List<Produto> lista() {
        return produtoService.listar();
    }
    
    // GET/produto/{id}
    @GetMapping("/produto/{id}")
    public ResponseEntity<Produto> buscar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(produtoService.buscarOuFalhar(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    // POST/produto
    @PostMapping("/produto")
    @ResponseStatus(HttpStatus.CREATED)
    public Produto adicionar (@Valid @RequestBody Produto produto){
        return produtoService.salvar(produto);
    }
    
    // PUT/produto/{id}
    @PutMapping("/produto/{id}")
    public ResponseEntity<Produto> atualizar(@Valid @PathVariable Long id,
                                             @RequestBody Produto produto){
        try {
            Produto atualizado = produtoService.atualizar(id, produto);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    // DELETE/produto/{id}
    @DeleteMapping("/produto/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        
        try {
            produtoService.excluir(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    // GET/produto/cat/{categoria}
    @GetMapping("/produto/cat/{categoria}")
    public ResponseEntity<List<Produto>> buscarPorCategoria(@PathVariable String categoria){
        List<Produto> produtos = produtoService.buscarPorCategoria(categoria);
        return ResponseEntity.ok(produtos);
    }


}
