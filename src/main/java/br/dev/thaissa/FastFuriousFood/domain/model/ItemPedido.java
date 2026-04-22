
package br.dev.thaissa.FastFuriousFood.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // relação com pedido
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;
    
    // relação com produto
    @NotNull
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;
    
    
    @NotNull
    @Positive
    private Integer quantidade;
    
    @NotNull
    private Double precoUnitario;

    public ItemPedido() {
    }

    public ItemPedido(Long id, Pedido pedido, Produto produto, Integer quantidade, Double precoUnitario) {
        this.id = id;
        this.pedido = pedido;
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }

    public Long getId() {
        return id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public void setPrecoUnitario(Double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }
    
    
}
