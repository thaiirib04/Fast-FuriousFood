
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
@RequestMapping("/fastfurious/produto")
public class ProdutoController {
    
    @Autowired
    private ProdutoService produtoService;
    
    // GET
    @GetMapping
    public List<Produto> listar() {
        return produtoService.listar();
    }
    
    // GET/{id}
    @GetMapping("/{id}")
    public Produto buscar(@PathVariable Long id) {
        return produtoService.buscarOuFalhar(id);
    }
    
    // POST
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto adicionar (@Valid @RequestBody Produto produto){
        return produtoService.salvar(produto);
    }
    
    // PUT
    @PutMapping("/{id}")
    public Produto atualizar(@PathVariable Long id,
                             @Valid @RequestBody Produto produto) {
        return produtoService.atualizar(id, produto);
    }
    
    // DELETE/{id}
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        produtoService.excluir(id);
    }
    
    // GET/cat/{categoria}
    @GetMapping("/cat/{categoria}")
    public List<Produto> buscarPorCategoria(@PathVariable String categoria) {
        return produtoService.buscarPorCategoria(categoria);
    }


}
