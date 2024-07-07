package com.alura.forohub.forohub.controller;

import com.alura.forohub.forohub.domain.curso.DTORegistroCurso;
import com.alura.forohub.forohub.domain.curso.DTORespuestaCurso;
import com.alura.forohub.forohub.service.CursoService;
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
@RequestMapping("/cursos")
@SecurityRequirement(name = "bearer-key")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @PostMapping
    @Transactional
    public ResponseEntity<DTORespuestaCurso> registrarCursos(@RequestBody @Valid DTORegistroCurso registroCurso){

        var cursoRegistrado = cursoService.registrarCurso(registroCurso);

        return ResponseEntity.ok(cursoRegistrado);
    }
}
