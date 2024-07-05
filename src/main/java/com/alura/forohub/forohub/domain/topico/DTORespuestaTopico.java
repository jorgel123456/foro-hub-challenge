package com.alura.forohub.forohub.domain.topico;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DTORespuestaTopico(

        Long id,

        String titulo,

        String mensaje,

        LocalDateTime fecha,

        Long idCurso,

        Long idUsuario
) {

    public DTORespuestaTopico(Topico topico) {
        this(topico.getId(),topico.getTitulo(), topico.getMensaje(),topico.getFecha(),topico.getCurso().getId(),topico.getUsuario().getId());
    }
}
