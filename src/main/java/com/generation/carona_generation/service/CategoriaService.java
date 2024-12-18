package com.generation.carona_generation.service;


import com.generation.carona_generation.model.Categoria;
import com.generation.carona_generation.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    //Post
    public Optional<Categoria> addCategoria(Categoria categoria) throws HttpClientErrorException {
        //Verifica se existe uma categoria com esse nome
        if(categoriaRepository.existsByNomeIgnoreCase(categoria.getNome())) {
            throw new HttpClientErrorException(HttpStatus.CONFLICT, "Categoria ja existente");
        }

        return Optional.of(categoriaRepository.save(categoria));

    }


}
