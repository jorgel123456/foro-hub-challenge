package com.alura.forohub.forohub.controller;


import com.alura.forohub.forohub.domain.perfil.DTORegistroPerfil;
import com.alura.forohub.forohub.domain.perfil.DTORespuestaPerfil;
import com.alura.forohub.forohub.domain.usuario.DTORegistroUsuario;
import com.alura.forohub.forohub.domain.usuario.DTORespuestaUsuario;
import com.alura.forohub.forohub.service.PerfilService;
import com.alura.forohub.forohub.service.UsuarioService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/perfiles")
@SecurityRequirement(name = "bearer-key")
public class PerfilController {
    @Autowired
    private PerfilService perfilService;

    @PostMapping
    @Transactional
    public ResponseEntity<DTORespuestaPerfil> registrarPerfil(@RequestBody @Valid DTORegistroPerfil registroPerfil){

        var respuesta=perfilService.registarPerfil(registroPerfil);

        return ResponseEntity.ok(respuesta);
    }

}
