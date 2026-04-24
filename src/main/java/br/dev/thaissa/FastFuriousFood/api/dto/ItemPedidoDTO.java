
package br.dev.thaissa.FastFuriousFood.api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


public class ItemPedidoDTO {
    
    @NotNull
    private Long produtoId;
    
    @NotNull
    @Positive
    private Integer quantidade;
    
    public Long getProdutoId(){
        return produtoId;
    }
    
    public void setProdutoId(Long produtoId){
        this.produtoId = produtoId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
    
    
}
