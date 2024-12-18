package com.generation.carona_generation.repository;


import com.generation.carona_generation.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    public Boolean existsByNomeIgnoreCase(@Param("nome") String nome);

}
