/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.dev.thaissa.FastFuriousFood.domain.repository;

import br.dev.thaissa.FastFuriousFood.domain.model.Produto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
  //List<Produto>findAll();
    List<Produto>findByCategoriaIgnoreCase(String categoria);
}
