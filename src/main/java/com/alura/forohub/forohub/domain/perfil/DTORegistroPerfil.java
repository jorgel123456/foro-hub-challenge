package com.alura.forohub.forohub.domain.perfil;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DTORegistroPerfil(

        @NotBlank(message = "Nombre es obligatorio")
        String nombre

) {
    public DTORegistroPerfil(String nombre) {
        this.nombre=nombre;
    }
}
