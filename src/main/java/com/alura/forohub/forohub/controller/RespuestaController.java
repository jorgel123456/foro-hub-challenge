package com.alura.forohub.forohub.controller;

import com.alura.forohub.forohub.domain.respuesta.DTOActualizarRespuesta;
import com.alura.forohub.forohub.domain.respuesta.DTORegistroRespuesta;
import com.alura.forohub.forohub.domain.respuesta.DTORespuesta;
import com.alura.forohub.forohub.domain.topico.DTOCrearTopico;
import com.alura.forohub.forohub.domain.topico.DTORespuestaTopico;
import com.alura.forohub.forohub.service.RespuestaService;
import com.alura.forohub.forohub.service.TopicoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.regex.Pattern;

@RestController
@RequestMapping("/respuestas")
@SecurityRequirement(name = "bearer-key")
public class RespuestaController {

    @Autowired
    private RespuestaService respuestaService;

    @PostMapping
    @Transactional
    public ResponseEntity<DTORespuesta> crearRespuesta(@RequestBody @Valid DTORegistroRespuesta registroRespuesta,
                                                       UriComponentsBuilder uriComponentsBuilder){
        var nuevaRespuesta=respuestaService.crearRespuesta(registroRespuesta,uriComponentsBuilder);
        return nuevaRespuesta;
    }
    //funcion para listar respuestas
    @GetMapping
    public ResponseEntity<Page> listadoRespuesta(@PageableDefault(size = 10) Pageable paginacion){

        return respuestaService.listarRespuestas(paginacion);
    }
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DTORespuesta> actualizaRespuesta(@RequestBody @Valid DTOActualizarRespuesta actualizarRespuesta,
                                                                    @PathVariable Long id, UriComponentsBuilder uriComponentsBuilder){

        return respuestaService.actualizarRespuesta(actualizarRespuesta, id, uriComponentsBuilder);
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarRespuesta(@PathVariable String id){
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

        return respuestaService.eliminarTopicoPorId(id1);
    }

}
