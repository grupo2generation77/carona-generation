package com.generation.carona_generation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O atributo distancia é obrigatorio")
    private BigDecimal distancia;

    @NotNull(message = "O atributo vagas é obrigatorio")
    private Integer vagas;

    @NotNull(message = "O atributo velocidade media é obrigatorio")
    private Double velocidadeMedia;

    @NotNull(message = "O atributo horario de partida é obrigatorio")
    private LocalDateTime horarioPartida;

    private LocalDateTime horarioPrevistaoChegada;

    @NotBlank(message = "O atributo lugar de partida é obrigatorio")
    @Size(min = 2, max = 500)
    private String lugarPartida;

    @NotBlank(message = "O atributo lugar de destino é obrigatorio")
    @Size(min = 2, max = 500)
    private String lugarDestino;

    @NotNull(message = "O atributo viagem recorrente é obrigatorio")
    private Boolean viagemRecorrente;

    @NotNull(message = "O atributo preco é obrigatorio")
    private Long preco;

    @ManyToOne
    @JsonIgnoreProperties("produtos")
    private Categoria categoria;

    @ManyToOne
    @JsonIgnoreProperties("produtos")
    private Usuario usuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull(message = "O atributo distancia é obrigatorio") BigDecimal getDistancia() {
        return distancia;
    }

    public void setDistancia(@NotNull(message = "O atributo distancia é obrigatorio") BigDecimal distancia) {
        this.distancia = distancia;
    }

    public @NotNull(message = "O atributo vagas é obrigatorio") Integer getVagas() {
        return vagas;
    }

    public void setVagas(@NotNull(message = "O atributo vagas é obrigatorio") Integer vagas) {
        this.vagas = vagas;
    }

    public @NotNull(message = "O atributo velocidade media é obrigatorio") Double getVelocidadeMedia() {
        return velocidadeMedia;
    }

    public void setVelocidadeMedia(@NotNull(message = "O atributo velocidade media é obrigatorio") Double velocidadeMedia) {
        this.velocidadeMedia = velocidadeMedia;
    }

    public @NotNull(message = "O atributo horario de partida é obrigatorio") LocalDateTime getHorarioPartida() {
        return horarioPartida;
    }

    public void setHorarioPartida(@NotNull(message = "O atributo horario de partida é obrigatorio") LocalDateTime horarioPartida) {
        this.horarioPartida = horarioPartida;
    }

    public LocalDateTime getHorarioPrevistaoChegada() {
        return horarioPrevistaoChegada;
    }

    public void setHorarioPrevistaoChegada(LocalDateTime horarioPrevistaoChegada) {
        this.horarioPrevistaoChegada = horarioPrevistaoChegada;
    }

    public @NotBlank(message = "O atributo lugar de partida é obrigatorio") String getLugarPartida() {
        return lugarPartida;
    }

    public void setLugarPartida(@NotBlank(message = "O atributo lugar de partida é obrigatorio") String lugarPartida) {
        this.lugarPartida = lugarPartida;
    }

    public @NotBlank(message = "O atributo lugar de destino é obrigatorio") String getLugarDestino() {
        return lugarDestino;
    }

    public void setLugarDestino(@NotBlank(message = "O atributo lugar de destino é obrigatorio") String lugarDestino) {
        this.lugarDestino = lugarDestino;
    }

    public @NotNull(message = "O atributo viagem recorrente é obrigatorio") Boolean getViagemRecorrente() {
        return viagemRecorrente;
    }

    public void setViagemRecorrente(@NotNull(message = "O atributo viagem recorrente é obrigatorio") Boolean viagemRecorrente) {
        this.viagemRecorrente = viagemRecorrente;
    }

    public @NotNull(message = "O atributo preco é obrigatorio") Long getPreco() {
        return preco;
    }

    public void setPreco(@NotNull(message = "O atributo preco é obrigatorio") Long preco) {
        this.preco = preco;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
