package com.generation.carona_generation.service;


import com.generation.carona_generation.model.Produto;
import com.generation.carona_generation.model.Usuario;
import com.generation.carona_generation.repository.ProdutoRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private UsuarioService usuarioService;

    //Post
    public Produto addProduto(Produto produto) throws HttpClientErrorException{
        Long usuarioId = produto.getUsuario().getId();
        System.out.println(usuarioId);
        Optional<Usuario> usuario = usuarioService.getUsuarioById(usuarioId);


        if(usuario.get().getModeloCarro() != null){
            produto.setHorarioPrevistaoChegada(calculateHorarioChegada(produto));

            return produtoRepository.save(produto);
        }

        throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,"O usuario precisa ter um carro para disponibilizar uma viagem");


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

    @Transactional
    public void delete(Long id) {
        Optional<Produto> produto = produtoRepository.findById(id);

        // Verifica se o produto existe
        if (produto.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado");
        }

        // Deleta o produto
        produtoRepository.deleteById(id);
    }
    
}
