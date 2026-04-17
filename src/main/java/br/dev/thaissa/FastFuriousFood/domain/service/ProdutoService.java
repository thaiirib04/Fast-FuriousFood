
package br.dev.thaissa.FastFuriousFood.domain.service;

import br.dev.thaissa.FastFuriousFood.domain.model.Produto;
import br.dev.thaissa.FastFuriousFood.domain.repository.ProdutoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {
    
    @Autowired
    private ProdutoRepository produtoRepository;
    
    public List<Produto> listar(){
        return produtoRepository.findAll();
    }
    
    public Produto buscarOuFalhar(Long id){
        Optional<Produto> produto = produtoRepository.findById(id);
        
        if(!produto.isPresent()){
            throw new RuntimeException("Produto não encontrado");
        }
        
        return produto.get();
    }
    
    public Produto salvar(Produto produto){
        return produtoRepository.save(produto);
    }
    
    public Produto atualizar(Long id, Produto produto) {
        if (!produtoRepository.existsById(id)) {
            throw new RuntimeException("Produto não encontrado");
        }
        
        produto.setId(id);
        return produtoRepository.save(produto);
    }
    
    public void excluir(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new RuntimeException("Produto não encontrado");
        }
        
        produtoRepository.deleteById(id);
    }
    
    public List<Produto> buscarPorCategoria(String categoria) {
        return produtoRepository.findByCategoriaIgnoreCase(categoria);
    }
}
