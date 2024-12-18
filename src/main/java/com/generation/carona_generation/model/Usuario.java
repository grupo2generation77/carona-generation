package com.generation.carona_generation.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "tb_usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O atributo nome é obrigatorio")
    @Size(min = 2, max = 100)
    private String nome;

    @Schema(example = "email@email.com.br")
    @NotBlank(message = "O atributo email é obrigatorio")
    @Email(message = "O Atributo Usuário deve ser um email válido!")
    private String usuario;

    @NotBlank(message = "O Atributo senha é obrigatorio!")
    @Size(min = 8, message = "A Senha deve ter no mínimo 8 caracteres")
    private String senha;

    private String modeloCarro;

    private Float avaliacao = (float) 5.0;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "usuario")
    @JsonIgnoreProperties("usuario")
    private List<Produto> produtos;

    public Usuario(String nome, String usuario, String senha, String modeloCarro, Float avaliacao) {
        this.nome = nome;
        this.usuario = usuario;
        this.senha = senha;
        this.modeloCarro = modeloCarro;
        this.avaliacao = avaliacao;
    }

    public Usuario() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "O atributo nome é obrigatorio") @Size(min = 2, max = 100) String getNome() {
        return nome;
    }

    public void setNome(@NotBlank(message = "O atributo nome é obrigatorio") @Size(min = 2, max = 100) String nome) {
        this.nome = nome;
    }

    public @NotBlank(message = "O atributo email é obrigatorio") @Email(message = "O Atributo Usuário deve ser um email válido!") String getUsuario() {
        return usuario;
    }

    public void setUsuario(@NotBlank(message = "O atributo email é obrigatorio") @Email(message = "O Atributo Usuário deve ser um email válido!") String usuario) {
        this.usuario = usuario;
    }

    public @NotBlank(message = "O Atributo senha é obrigatorio!") @Size(min = 8, message = "A Senha deve ter no mínimo 8 caracteres") String getSenha() {
        return senha;
    }

    public void setSenha(@NotBlank(message = "O Atributo senha é obrigatorio!") @Size(min = 8, message = "A Senha deve ter no mínimo 8 caracteres") String senha) {
        this.senha = senha;
    }

    public String getModeloCarro() {
        return modeloCarro;
    }

    public void setModeloCarro(String modeloCarro) {
        this.modeloCarro = modeloCarro;
    }

    public Float getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Float avaliacao) {
        this.avaliacao = avaliacao;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}
