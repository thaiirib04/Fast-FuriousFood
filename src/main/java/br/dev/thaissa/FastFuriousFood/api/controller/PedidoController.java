package br.dev.thaissa.FastFuriousFood.api.controller;

import br.dev.thaissa.FastFuriousFood.api.dto.PedidoDTO;
import br.dev.thaissa.FastFuriousFood.domain.model.Pedido;
import br.dev.thaissa.FastFuriousFood.domain.model.StatusPedido;
import br.dev.thaissa.FastFuriousFood.domain.service.PedidoService;
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
import br.dev.thaissa.FastFuriousFood.api.dto.StatusDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/fastfurious/pedido")
public class PedidoController {
    
    @Autowired
    private PedidoService pedidoService;
    
    //GET - listar todos
    @Operation(summary = "Listar pedidos", description = "Retorna todos os pedidos cadastrados")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping
    public List<Pedido> listar(){
        
       return pedidoService.listar();
      
    }
    
    //GET/{id}
    @Operation(summary = "Buscar pedido por ID", description = "Retorna um pedido específico pelo ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Pedido encontrado"),
        @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
    })
    @GetMapping("/{id}")
    public Pedido buscar(
            @Parameter(description = "ID do pedido", example = "1")
            @PathVariable Long id) {
        
        return pedidoService.buscarOuFalhar(id);
    }
    
    //POST
    @Operation(summary = "Criar pedido", description = "Cria um novo pedido com status ABERTO")
    @ApiResponse(responseCode = "201", description = "Pedido criado com sucesso")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pedido criar (@Valid @RequestBody PedidoDTO dto){
        
        return pedidoService.criar(dto);
    }
    
    //PUT
    @Operation(summary = "Atualizar pedido", description = "Atualiza dados básicos do pedido (não altera itens)")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Pedido atualizado"),
        @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
    })
    @PutMapping("/{id}")
    public Pedido atualizar(
            @Parameter(description = "ID do pedido", example = "1")
            @Valid @PathVariable Long id,
            @RequestBody Pedido pedido) {
        
        return pedidoService.atualizar(id, pedido);
    }
    
    // DELETE (cancelar)
    @Operation(summary = "Cancelar pedido", description = "Altera o status do pedido para CANCELADO")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Pedido cancelado"),
        @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
    })
    @DeleteMapping("/{id}")
    public Pedido cancelar(
            @Parameter(description = "ID do pedido", example = "1")
            @PathVariable Long id) {
        
        return pedidoService.cancelar(id);
    }
    
    //GET status
    @Operation(summary = "Buscar pedidos por status", description = "Retorna pedidos filtrados por status (ABERTO, PRONTO, ENTREGUE)")
    @ApiResponse(responseCode = "200", description = "Lista filtrada com sucesso")
    @GetMapping("/status/{status}")
    public List<Pedido> buscarPorStatus(
            @Parameter(description = "Status do pedido", example = "ABERTO")
            @PathVariable StatusPedido status){
        
        return pedidoService.buscarPorStatus(status);
    }
    
    //PUT status
    @Operation(summary = "Atualizar status do pedido", description = "Altera o status do pedido (ABERTO → PRONTO → ENTREGUE)")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Status atualizado"),
        @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
    })
    @PutMapping("/status/{id}")
    public Pedido atualizarStatus(
            @Parameter(description = "ID do pedido", example = "1")
            @PathVariable Long id,
            @RequestBody StatusDTO dto) {
        
        return pedidoService.atualizarStatus(id, dto.getStatus());
    }
}
