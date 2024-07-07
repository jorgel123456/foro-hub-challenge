package com.alura.forohub.forohub.controller;

import com.alura.forohub.forohub.domain.usuario.DTOAutenticacionAusuario;
import com.alura.forohub.forohub.domain.usuario.Usuario;
import com.alura.forohub.forohub.infra.security.DTOJwtToken;
import com.alura.forohub.forohub.infra.security.TokenService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @PostMapping
    public ResponseEntity autenticacionUsuario(@RequestBody @Valid DTOAutenticacionAusuario dtoAutenticacionAusuario){
        System.out.println(dtoAutenticacionAusuario.email() + dtoAutenticacionAusuario.clave());
        Authentication AuthToken = new UsernamePasswordAuthenticationToken(dtoAutenticacionAusuario.email(),dtoAutenticacionAusuario.clave());
        var usuarioAutenticado= authenticationManager.authenticate(AuthToken);
        var jwtToken=tokenService.generartoken((Usuario) usuarioAutenticado.getPrincipal());
        System.out.println(jwtToken);
        return ResponseEntity.ok(new DTOJwtToken(jwtToken));
    }
}
