package com.alura.forohub.forohub.controller;


import com.alura.forohub.forohub.domain.usuario.DTORegistroUsuario;
import com.alura.forohub.forohub.domain.usuario.DTORespuestaUsuario;
import com.alura.forohub.forohub.service.UsuarioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DTORespuestaUsuario> registrarUsuario(@RequestBody @Valid DTORegistroUsuario registroUsuario){

        var respuesta=service.registarUsuario(registroUsuario);

        return ResponseEntity.ok(respuesta);
    }

}
