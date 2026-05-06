
package br.dev.thaissa.FastFuriousFood.api.controller;

import br.dev.thaissa.FastFuriousFood.domain.model.Produto;
import br.dev.thaissa.FastFuriousFood.domain.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    
    
    // GET - listar todos
     @Operation(summary = "Listar produtos", description = "Retorna todos os produtos cadastrados")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping
    public List<Produto> listar() {
        return produtoService.listar();
    }
    
    // GET/{id}
     @Operation(summary = "Buscar produto por ID", description = "Retorna um produto específico pelo ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Produto encontrado"),
        @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    @GetMapping("/{id}")
    public Produto buscar(
            @Parameter(description = "ID do produto", example = "1")
            @PathVariable Long id) {
        return produtoService.buscarOuFalhar(id);
    }
    
    // POST
    @Operation(summary = "Cadastrar produto", description = "Cria um novo produto")
    @ApiResponse(responseCode = "201", description = "Produto criado com sucesso")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto adicionar (@Valid @RequestBody Produto produto){
        return produtoService.salvar(produto);
    }
    
    // PUT
    @Operation(summary = "Atualizar produto", description = "Atualiza um produto existente pelo ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Produto atualizado"),
        @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    @PutMapping("/{id}")
    public Produto atualizar(
            @Parameter(description = "ID do produto", example = "1")
            @PathVariable Long id,
            @Valid @RequestBody Produto produto) {
        
        return produtoService.atualizar(id, produto);
    }
    
    // DELETE/{id}
    @Operation(summary = "Excluir produto", description = "Remove um produto pelo ID")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Produto excluído"),
        @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(
            @Parameter(description = "ID do produto", example = "1")
            @PathVariable Long id) {
        
        produtoService.excluir(id); 
    }
    
    // GET/cat/{categoria}
    @Operation(summary = "Buscar produtos por categoria", description = "Retorna produtos filtrados por categoria")
    @ApiResponse(responseCode = "200", description = "Lista filtrada com sucesso")
    @GetMapping("/cat/{categoria}")
    public List<Produto> buscarPorCategoria(
            @Parameter(description = "Categoria do produto", example = "lanche")
            @PathVariable String categoria) {
        
        return produtoService.buscarPorCategoria(categoria);
    }


}
