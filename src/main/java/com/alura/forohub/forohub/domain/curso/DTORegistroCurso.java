package com.alura.forohub.forohub.domain.curso;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DTORegistroCurso(
        Long id,
        @NotBlank
        String nombre,
        @NotNull
        Categoria categorias
) {
        public DTORegistroCurso(String nombre, Categoria categorias) {
                this(null, nombre, categorias);

        }


}
