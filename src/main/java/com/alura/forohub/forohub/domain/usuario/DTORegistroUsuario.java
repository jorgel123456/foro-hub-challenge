package com.alura.forohub.forohub.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public record DTORegistroUsuario(

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
        this.idPerfil=idPerfil;
        this.nombre=nombre;
        this.email=email;
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(clave);
        this.clave=encodedPassword;


    }
}
