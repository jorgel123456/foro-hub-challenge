package com.alura.forohub.forohub.domain.usuario;

public record DTOAutenticacionAusuario(String email, String clave) {
    public DTOAutenticacionAusuario(Usuario usuario) {
        this(usuario.getEmail(),usuario.getClave());
    }
}
