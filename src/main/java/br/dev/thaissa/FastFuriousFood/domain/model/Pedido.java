
package br.dev.thaissa.FastFuriousFood.domain.model;

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
    
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItensPedido> itens;
  
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

    public List<ItensPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItensPedido> itens) {
        this.itens = itens;
    }
    
    

}
