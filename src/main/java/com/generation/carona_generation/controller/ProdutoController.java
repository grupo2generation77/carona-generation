package com.generation.carona_generation.controller;


import com.generation.carona_generation.model.Produto;
import com.generation.carona_generation.model.Categoria;
import com.generation.carona_generation.model.Produto;
import com.generation.carona_generation.repository.CategoriaRepository;
import com.generation.carona_generation.repository.ProdutoRepository;
import com.generation.carona_generation.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;


import java.util.List;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<Produto>> listProdutos() {
        return ResponseEntity.ok(produtoService.listAllProdutos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProdutoById (@PathVariable Long id) {
        try {
            return ResponseEntity.ok(produtoService.getProdutoById(id));
        }catch (HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Produto> registerProduto(@Valid @RequestBody Produto produto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.addProduto(produto));
    }

}
