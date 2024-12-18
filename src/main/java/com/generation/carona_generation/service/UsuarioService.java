package com.generation.carona_generation.service;


import com.generation.carona_generation.model.Usuario;
import com.generation.carona_generation.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    //Post
    public Optional<Usuario> addUsuario(Usuario usuario) throws HttpClientErrorException {

        if (usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent()) {
            throw new HttpClientErrorException(HttpStatus.CONFLICT, "Usuario ja existente");
        }
        //usuario.setSenha(encryptPassword(usuario.getSenha()));



        return Optional.of(usuarioRepository.save(usuario));

    }


    //private String encryptPassword(String senha) {
    //    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    //  return encoder.encode(senha);
    //}


}
