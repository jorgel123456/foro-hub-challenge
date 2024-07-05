package com.alura.forohub.forohub.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DTORegistroUsuario(
        Long id,
        Long idPerfil,
        @NotBlank(message = "Nombre es obligatorio")
        String nombre,
        @Email
        @NotNull(message = "Email es obligatorio")
        String email,
        @NotBlank(message = "Clave es obligatorio")
        String clave



) {
    public DTORegistroUsuario(Long idPerfil, String nombre, String email, String clave) {
        this(null, idPerfil, nombre, email, clave);

    }
}
