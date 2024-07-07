package com.alura.forohub.forohub.domain.usuario;

public record DTORespuestaUsuarioActualizado(
        Long id,
        String nombre,
        String email
) {
    public DTORespuestaUsuarioActualizado(Usuario usuario){

        this(usuario.getId(), usuario.getNombre(), usuario.getEmail());
    }
}
