
package br.dev.thaissa.FastFuriousFood.api.dto;

import br.dev.thaissa.FastFuriousFood.domain.model.StatusPedido;


public class StatusDTO {
    private StatusPedido status;
    
    public StatusPedido getStatus(){
        return status;
    }
    
    public void setStatus(StatusPedido status){
        this.status = status;
    }
}
