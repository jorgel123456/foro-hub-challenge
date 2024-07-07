package com.alura.forohub.forohub.domain.respuesta;

import com.alura.forohub.forohub.domain.curso.DTOListarCurso;
import com.alura.forohub.forohub.domain.topico.DTOListarTopico;
import com.alura.forohub.forohub.domain.topico.DTORespuestaTopico;
import com.alura.forohub.forohub.domain.usuario.DTOListarUsuarios;

import java.time.LocalDateTime;

public record DTOListarRespuesta(
        Long id,
        String mensaje,
        LocalDateTime fecha,
        DTOListarTopico idTopico,
        DTOListarUsuarios idUsuario
) {
    public DTOListarRespuesta(Respuesta respuesta) {
        this(
                respuesta.getId(),
                respuesta.getMensaje(),
                respuesta.getFecha(),
                new DTOListarTopico(respuesta.getTopico()),
                new DTOListarUsuarios(respuesta.getUsuario())
        );
    }
}
