package com.alura.forohub.forohub.domain.respuesta;

import com.alura.forohub.forohub.domain.topico.Topico;

import java.time.LocalDateTime;

public record DTORespuesta(
        Long id,
        String mensaje,
        LocalDateTime fecha,
        Long idTopico,
        Long idUsuario
) {
    public DTORespuesta(Respuesta respuesta) {
        this(respuesta.getId(),

                respuesta.getMensaje(),
                respuesta.getFecha(),
                respuesta.getTopico().getId(),
                respuesta.getUsuario().getId());
    }
}
