package com.alura.forohub.forohub.service;

import com.alura.forohub.forohub.domain.perfil.Perfil;
import com.alura.forohub.forohub.domain.topico.DTORespuestaTopicoActualizado;
import com.alura.forohub.forohub.domain.topico.Topico;
import com.alura.forohub.forohub.domain.usuario.*;
import com.alura.forohub.forohub.infra.errores.ValidacionIntegridad;
import com.alura.forohub.forohub.repository.PerfilRepository;
import com.alura.forohub.forohub.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

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

    public ResponseEntity<Page> listarUsuarios(Pageable paginacion) {
        Page<Usuario> usuario=usuarioRepository.findAll(paginacion);
        return ResponseEntity.ok(usuario
                .map(DTORespuestaUsuario::new));
    }
    public ResponseEntity<DTORespuestaUsuarioActualizado> actualizarUsuario(DTOActualizarUsuario actualizarUsuario, Long id, UriComponentsBuilder uriComponentsBuilder) {
        if (!usuarioRepository.existsById(id)){
            throw new ValidacionIntegridad("El topico con ese id, no ha sido creado");
        }
        if (actualizarUsuario.idPerfil()==null){
            throw  new ValidacionIntegridad("Ingrese el ferfil de usuario a modificar ");
        }
        if(!usuarioRepository.findById(actualizarUsuario.idPerfil()).isPresent()){
            throw new ValidacionIntegridad("El curso no se encuentra registrado.");
        }

        var perfil =perfilRepository.findById(actualizarUsuario.idPerfil()).get();

        Usuario usuario=usuarioRepository.getReferenceById(id);
        usuario.actualizarUsuario(actualizarUsuario,perfil);
        DTORespuestaUsuarioActualizado respuestaUsuarioActualizado=new DTORespuestaUsuarioActualizado(usuario);

        //return respuestaTopicoActualizado;

        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(url).body(respuestaUsuarioActualizado);

    }

    public ResponseEntity eliminarUsuarioPorId(Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            usuarioRepository.deleteById(usuario.getId());
            return ResponseEntity.ok().body("El usuario se eliminó exitosamente");
        }

        return ResponseEntity.noContent().build();
    }
}
