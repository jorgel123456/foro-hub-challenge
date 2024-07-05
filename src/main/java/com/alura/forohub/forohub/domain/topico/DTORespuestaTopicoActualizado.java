package com.alura.forohub.forohub.domain.topico;

import com.alura.forohub.forohub.domain.curso.DTOListarCurso;
import com.alura.forohub.forohub.domain.usuario.DTOListarUsuarios;

import java.time.LocalDateTime;

public record DTORespuestaTopicoActualizado(

        Long id,

        String titulo,

        String mensaje,

        LocalDateTime fecha,

        DTOListarUsuarios usuario,

        DTOListarCurso curso
) {

    public DTORespuestaTopicoActualizado(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFecha(),
                new DTOListarUsuarios(topico.getUsuario()),
                new DTOListarCurso(topico.getCurso())
        );
    }
}
