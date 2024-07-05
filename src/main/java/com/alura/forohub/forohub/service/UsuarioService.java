package com.alura.forohub.forohub.service;

import com.alura.forohub.forohub.domain.perfil.Perfil;
import com.alura.forohub.forohub.domain.usuario.DTORegistroUsuario;
import com.alura.forohub.forohub.domain.usuario.DTORespuestaUsuario;
import com.alura.forohub.forohub.domain.usuario.Usuario;
import com.alura.forohub.forohub.infra.errores.ValidacionIntegridad;
import com.alura.forohub.forohub.repository.PerfilRepository;
import com.alura.forohub.forohub.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PerfilRepository perfilRepository;

    public DTORespuestaUsuario registarUsuario(DTORegistroUsuario registroUsuario) {
        if(registroUsuario.idPerfil()==null){
            throw new ValidacionIntegridad("El id del perfil no puede ser nulo");
        }

        Perfil perfil=perfilRepository.findById(registroUsuario.idPerfil())
                .orElseThrow(()->new ValidacionIntegridad("Perfil no encontrado"));

        var usuario=new Usuario(perfil,registroUsuario);
        Usuario usuarioNuevo=usuarioRepository.save(usuario);
        DTORespuestaUsuario respuesta= new DTORespuestaUsuario(usuarioNuevo.getId(),usuarioNuevo.getNombre(),
                usuarioNuevo.getEmail());
        //URI url=uriComponentsBuilder.path("/usuario/{id}").buildAndExpand(usuarioNuevo.getId()).toUri();
        return respuesta;
    }

}
