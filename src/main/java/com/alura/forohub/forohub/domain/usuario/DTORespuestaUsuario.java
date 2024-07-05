package com.alura.forohub.forohub.domain.usuario;

public record DTORespuestaUsuario(
        Long id,
        String nombre,
        String email
) {
    public DTORespuestaUsuario(Usuario usuario){

        this(usuario.getId(), usuario.getNombre(), usuario.getEmail());
    }
}
