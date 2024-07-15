package com.alura.forohub.forohub.domain.topico;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DTORespuestaTopico(

        Long id,

        String titulo,

        String mensaje,

       // LocalDateTime fecha,

        Long idCurso,

        Long idUsuario
) {

    public DTORespuestaTopico(Topico topico) {
        this(topico.getId(),topico.getTitulo(), topico.getMensaje(),topico.getCurso().getId(),topico.getUsuario().getId());
    }



//    public DTORespuestaTopico(Long id, String titulo, String mensaje, Long idCurso, Long idUsuario) {
//        this.id = id;
//        this.titulo = titulo;
//        this.mensaje = mensaje;
//        this.idCurso = idCurso;
//        this.idUsuario = idUsuario;
//    }
}
