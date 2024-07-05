package com.alura.forohub.forohub.domain.topico;

import jakarta.validation.constraints.NotBlank;

public record DTOActualizarTopico(
        @NotBlank(message = "El titulo es obligatorio")
        String titulo,
        @NotBlank(message = "El mensaje es obligatorio")
        String mensaje,
        Long idCurso,
        String estado

) {
        public DTOActualizarTopico(String titulo, String mensaje, Long idCurso, Long idUsuario, String estado) {
                this(titulo, mensaje, idCurso,estado);

        }

}
