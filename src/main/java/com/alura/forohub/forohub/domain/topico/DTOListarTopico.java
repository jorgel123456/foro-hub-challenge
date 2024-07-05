package com.alura.forohub.forohub.domain.topico;

import com.alura.forohub.forohub.domain.curso.Curso;
import com.alura.forohub.forohub.domain.curso.DTOListarCurso;
import com.alura.forohub.forohub.domain.curso.DTORespuestaCurso;
import com.alura.forohub.forohub.domain.usuario.DTOListarUsuarios;
import com.alura.forohub.forohub.domain.usuario.DTORegistroUsuario;
import com.alura.forohub.forohub.domain.usuario.DTORespuestaUsuario;
import com.alura.forohub.forohub.domain.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DTOListarTopico(
        Long id,
        String titulo,

        String mensaje,

        LocalDateTime fecha,
        String activo,

        DTOListarUsuarios usuario,

        DTOListarCurso curso) {
    public DTOListarTopico(Topico topico) {
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
