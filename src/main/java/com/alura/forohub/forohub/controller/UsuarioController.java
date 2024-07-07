package com.alura.forohub.forohub.controller;


import com.alura.forohub.forohub.domain.usuario.DTOActualizarUsuario;
import com.alura.forohub.forohub.domain.usuario.DTORegistroUsuario;
import com.alura.forohub.forohub.domain.usuario.DTORespuestaUsuario;
import com.alura.forohub.forohub.domain.usuario.DTORespuestaUsuarioActualizado;
import com.alura.forohub.forohub.service.UsuarioService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuarios")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @Transactional
    public ResponseEntity<DTORespuestaUsuario> registrarUsuario(@RequestBody @Valid DTORegistroUsuario registroUsuario){

        var respuesta=usuarioService.registarUsuario(registroUsuario);

        return ResponseEntity.ok(respuesta);
    }
    @GetMapping
    public ResponseEntity<Page> listarUsuarios(@PageableDefault(size = 10) Pageable paginacion){

        return usuarioService.listarUsuarios(paginacion);
    }
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DTORespuestaUsuarioActualizado> actualizarUsuario(@RequestBody DTOActualizarUsuario actualizarUsuario, @PathVariable Long id, UriComponentsBuilder uriComponentsBuilder){
        return usuarioService.actualizarUsuario(actualizarUsuario, id, uriComponentsBuilder);
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarUsuario(@PathVariable Long id){

        return usuarioService.eliminarUsuarioPorId(id);
    }

}
