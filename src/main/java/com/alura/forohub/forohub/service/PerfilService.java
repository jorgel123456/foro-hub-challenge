package com.alura.forohub.forohub.service;

import com.alura.forohub.forohub.domain.perfil.DTORegistroPerfil;
import com.alura.forohub.forohub.domain.perfil.DTORespuestaPerfil;
import com.alura.forohub.forohub.domain.perfil.Perfil;
import com.alura.forohub.forohub.domain.usuario.DTORegistroUsuario;
import com.alura.forohub.forohub.domain.usuario.DTORespuestaUsuario;
import com.alura.forohub.forohub.domain.usuario.Usuario;
import com.alura.forohub.forohub.infra.errores.ValidacionIntegridad;
import com.alura.forohub.forohub.repository.PerfilRepository;
import com.alura.forohub.forohub.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PerfilService {
    @Autowired
    private PerfilRepository perfilRepository;

    public DTORespuestaPerfil registarPerfil(DTORegistroPerfil registroPerfil) {

        var perfil=new Perfil(registroPerfil.nombre().toUpperCase());
        Perfil perfilNuevo=perfilRepository.save(perfil);
        DTORespuestaPerfil respuesta= new DTORespuestaPerfil(perfilNuevo.getId(),perfilNuevo.getNombre());
        //URI url=uriComponentsBuilder.path("/usuario/{id}").buildAndExpand(usuarioNuevo.getId()).toUri();
        return respuesta;
    }

}
