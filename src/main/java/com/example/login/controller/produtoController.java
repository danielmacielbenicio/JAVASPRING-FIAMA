package com.example.login.controller;

import com.example.login.dto.produtoRequestDTO;
import com.example.login.entity.Produto;
import com.example.login.repositories.ProdutoRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("produto")
public class produtoController {

    @Autowired
    ProdutoRepo produtoRepo;

    @PostMapping(value = "/cadastro")
    public ResponseEntity<?> saveProduto(@Valid @RequestBody produtoRequestDTO produto) {
        Produto product = new Produto(produto.getName(), produto.getPrice(), produto.getAmount());
        produtoRepo.save(product);
        return ResponseEntity.ok(produto);
    }
    @GetMapping(value = "/listar")
    public ResponseEntity<?> listarProduct(){
        return ResponseEntity.ok(produtoRepo.findAll());
        }

        @GetMapping(value = "/{id}")
                public Optional<Produto> produtoPorId(@PathVariable int id){
        return produtoRepo.findById(id);
        }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Produto> atualizarProduct(@PathVariable int id ,@RequestBody Produto novo){
        Optional<Produto> produtoExitente = produtoRepo.findById(id);

        if (produtoExitente.isPresent()){
            Produto Produto = produtoExitente.get();
            Produto.setAmount(novo.getAmount());
            produtoRepo.save(Produto);
            return ResponseEntity.ok(Produto);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id){
        if (produtoRepo.existsById(id)){
            produtoRepo.deleteById(id);
           return ResponseEntity.status(HttpStatus.OK).body("Excluido com sucesso!");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Esse ID não existe");
        }
    }


}
