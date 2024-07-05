package com.alura.forohub.forohub.domain.topico;

import com.alura.forohub.forohub.domain.curso.DTOListarCurso;
import com.alura.forohub.forohub.domain.usuario.DTOListarUsuarios;

import java.time.LocalDateTime;

public record DTORespuestaTopicoPorId(
        Long id,
        String titulo,

        String mensaje,

        LocalDateTime fecha,
        String activo,

        DTOListarUsuarios usuario,

        DTOListarCurso curso) {
    public DTORespuestaTopicoPorId(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFecha(),
                topico.getActivo(),
                new DTOListarUsuarios(topico.getUsuario()),
                new DTOListarCurso(topico.getCurso())
                );
    }

}
