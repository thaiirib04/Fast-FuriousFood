
package br.dev.thaissa.FastFuriousFood.api.controller;

import br.dev.thaissa.FastFuriousFood.domain.model.Pedido;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PedidoController {
    
    List<Pedido> listaPedidos;
    
    @GetMapping ("/pedido")
    public List<Pedido> listas() {
        listaPedidos = new ArrayList<Pedido>();
        listaPedidos.add(new Pedido(1, "Pedro", "23405429584"));
        listaPedidos.add(new Pedido(1, "Julia", "341256874367"));
        listaPedidos.add(new Pedido(1, "Maria", "543264648683"));
        
        return listaPedidos;
    }
}
