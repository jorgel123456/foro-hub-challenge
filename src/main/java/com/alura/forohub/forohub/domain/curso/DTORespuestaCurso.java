package com.alura.forohub.forohub.domain.curso;

import com.alura.forohub.forohub.domain.usuario.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DTORespuestaCurso(
        Long id,
        @NotBlank(message = "Nombre es obligatorio")
        String nombre

) {
        public DTORespuestaCurso(Curso curso){

                this(curso.getId(), curso.getNombre());
        }
}
