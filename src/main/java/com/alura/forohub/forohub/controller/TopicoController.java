package com.alura.forohub.forohub.controller;

import com.alura.forohub.forohub.domain.topico.*;
import com.alura.forohub.forohub.service.TopicoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    @Transactional
    public ResponseEntity<DTORespuestaTopico> crearTopico(@RequestBody @Valid DTOCrearTopico crearTopico){
        var nuevoTopico=topicoService.crearTopico(crearTopico);
        return ResponseEntity.ok(nuevoTopico);
    }
    @GetMapping
    @Transactional
    public ResponseEntity<Page<DTOListarTopico>> listarTopico(@PageableDefault(size = 10) Pageable paginacion) {
        Page<DTOListarTopico> topicos = topicoService.listarTopicos(paginacion);
        return ResponseEntity.ok(topicos);
    }
    //endpoint para listar topicos por curso y año
    @GetMapping("/cursos")
    public ResponseEntity<Page<DTOListarTopico>> listarTopicoPorCursoAndFecha(@RequestParam Long idCurso, @RequestParam int year,@PageableDefault(size = 10, sort = {"fecha"}, direction = Sort.Direction.ASC) Pageable pageable ) {
        Page<DTOListarTopico> topicos = topicoService.listarTopicoPorCursoAndFecha(idCurso,year, pageable);
        return ResponseEntity.ok(topicos);
    }
    @GetMapping("/{id}")
    public ResponseEntity<DTORespuestaTopicoPorId> mostrarTopicoPorId(@PathVariable @NotBlank String id){

        if (!id.isEmpty()) {
            throw new ValidationException( "El ID no debe nulo");
        }

        if (!Pattern.matches("^-?\\d+$", id)){
            throw new ValidationException( "El ID debe ser una numero");
        }
        Long id1=Long.parseLong(id);
        if (id1<=0){
            throw new ValidationException( "El ID debe de ser mayor a cero");
        }

        var busqueda=topicoService.mostrarTopicoPorId(id1);
        return ResponseEntity.ok(busqueda);
    }
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DTORespuestaTopicoActualizado> ActualizarTopicoPorId(@RequestBody @Valid DTOActualizarTopico actualizarTopico,
                                                                               @PathVariable String id, UriComponentsBuilder uriComponentsBuilder){
        if (id.isEmpty()) {
            throw new ValidationException( "El ID no debe nulo");
        }

        if (!Pattern.matches("^-?\\d+$", id)){
            throw new ValidationException( "El ID debe ser una numero");
        }
        Long id1=Long.parseLong(id);
        if (id1<=0){
            throw new ValidationException( "El ID debe de ser mayor a cero");
        }
        return topicoService.actualizarTopicoPorId(actualizarTopico, id1, uriComponentsBuilder);

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopicoPorId(@PathVariable String id){
        if (id.isEmpty()) {
            throw new ValidationException( "El ID no debe nulo");
        }

        if (!Pattern.matches("^-?\\d+$", id)){
            throw new ValidationException( "El ID debe ser una numero");
        }
        Long id1=Long.parseLong(id);
        if (id1<=0){
            throw new ValidationException( "El ID debe de ser mayor a cero");
        }

        return topicoService.eliminarTopicoPorId(id1);
    }


}
