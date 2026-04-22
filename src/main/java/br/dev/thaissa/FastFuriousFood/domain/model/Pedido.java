
package br.dev.thaissa.FastFuriousFood.domain.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Pedido {
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    
    
    private Integer numero; //numero do ticket
    
    @Enumerated(EnumType.STRING)
    private StatusPedido status;
    
    private LocalDateTime dataHora;
    
    @JsonManagedReference
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido> itens = new ArrayList<>();
    
    public void adicionarItem(ItemPedido item) {
        if (item == null){
        throw new IllegalArgumentException("Item não pode ser nulo");
        }
        if (item.getProduto() == null) {
            throw new IllegalArgumentException("Produto é obrigatório");
        }

        if (item.getQuantidade() == null || item.getQuantidade() <= 0) {
            throw new IllegalArgumentException("Quantidade inválida");
        }
    
        //evita duplicidade
        for (ItemPedido existente : itens) {
        if (existente.getProduto().equals(item.getProduto())) {
            existente.setQuantidade(
                existente.getQuantidade() + item.getQuantidade()
            );
            return;
        }
    }

    item.setPedido(this);
    this.itens.add(item);
    
    }
    
    //total do pedido
    public double getTotal() {
        return itens.stream()
            .mapToDouble(i -> i.getPrecoUnitario() * i.getQuantidade())
            .sum();
    }
  
    public Pedido() {
    }

    public Pedido(Long id, Integer numero, StatusPedido status, LocalDateTime dataHora) {
        this.id = id;
        this.numero = numero;
        this.status = status;
        this.dataHora = dataHora;
    }

   @PrePersist
   public void prePersist(){
       this.dataHora = LocalDateTime.now();
   }

    public Long getId() {
        return id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }
    
    

}
