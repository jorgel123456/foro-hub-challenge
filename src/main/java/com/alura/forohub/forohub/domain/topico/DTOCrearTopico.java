package com.alura.forohub.forohub.domain.topico;

import com.alura.forohub.forohub.domain.curso.Categoria;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DTOCrearTopico(
        Long id,

        @NotBlank(message = "El titulo es obligatorio")
        String titulo,
        @NotBlank(message = "El mensaje es obligatorio")
        String mensaje,
        Long idCurso,

        Long idUsuario

) {
        public DTOCrearTopico(String titulo, String mensaje, Long idCurso, Long idUsuario) {
                this(null, titulo, mensaje, idCurso,idUsuario);

        }

}
