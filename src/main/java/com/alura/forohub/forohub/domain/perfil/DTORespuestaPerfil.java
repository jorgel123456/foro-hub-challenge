package com.alura.forohub.forohub.domain.perfil;

import com.alura.forohub.forohub.domain.usuario.Usuario;

public record DTORespuestaPerfil(
        Long id,
        String nombre
) {
    public DTORespuestaPerfil(Perfil perfil){

        this(perfil.getId(), perfil.getNombre());
    }
}
