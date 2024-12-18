package com.generation.carona_generation.service;


import com.generation.carona_generation.model.Produto;
import com.generation.carona_generation.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    //Post
    public Produto addProduto(Produto produto){

        produto.setHorarioPrevistaoChegada(calculateHorarioChegada(produto));

        return produtoRepository.save(produto);
    }

    //calculo para o horario de chegada
    public LocalDateTime calculateHorarioChegada(Produto produto){
        BigDecimal distancia = produto.getDistancia();
        double velocidade = produto.getVelocidadeMedia();
        LocalDateTime horarioPartida = produto.getHorarioPartida();

        // Converte a velocidade para BigDecimal
        BigDecimal velocidadeBigDecimal = BigDecimal.valueOf(velocidade);

        // Faz a divisão para calcular o tempo em horas
        BigDecimal tempoEmHoras = distancia.divide(velocidadeBigDecimal, 2, BigDecimal.ROUND_HALF_UP);

        // Divide o tempo em horas e minutos
        long horas = tempoEmHoras.longValue(); // Parte inteira das horas
        long minutos = tempoEmHoras.remainder(BigDecimal.ONE).multiply(BigDecimal.valueOf(60)).longValue(); // Parte decimal em minutos

        // Soma o tempo calculado ao horário de saída
        return horarioPartida.plusHours(horas).plusMinutes(minutos);
    }

    //Get
    public List<Produto> listAllProdutos() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> getProdutoById(Long id) throws HttpClientErrorException {
        if(produtoRepository.existsById(id)) {
            return produtoRepository.findById(id);
        }
        throw new HttpClientErrorException(HttpStatus.NOT_FOUND,"Produto com o id " + id +" não encontrado");
    }


}
