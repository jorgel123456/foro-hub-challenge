package com.alura.forohub.forohub.service;

import com.alura.forohub.forohub.domain.respuesta.*;
import com.alura.forohub.forohub.domain.topico.DTORespuestaTopico;
import com.alura.forohub.forohub.domain.topico.DTORespuestaTopicoActualizado;
import com.alura.forohub.forohub.domain.topico.Topico;
import com.alura.forohub.forohub.domain.usuario.Usuario;
import com.alura.forohub.forohub.infra.errores.ValidacionIntegridad;
import com.alura.forohub.forohub.repository.RespuestaRepository;
import com.alura.forohub.forohub.repository.TopicoRepository;
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
public class RespuestaService {
    @Autowired
    private RespuestaRepository respuestaRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;
    public ResponseEntity<DTORespuesta> crearRespuesta(DTORegistroRespuesta registroRespuesta,
                                                       UriComponentsBuilder uriComponentsBuilder) {
        if(registroRespuesta.idUsuario()==null){
            throw new ValidacionIntegridad("Para crear un rtespuesta al topico se quiere ingresar como usuario.");
        }
        if (registroRespuesta.idTopico()==null){
            throw  new ValidacionIntegridad("Ingrese un topico al que desea generar una respuesta ");
        }

        if (!topicoRepository.findById(registroRespuesta.idTopico()).isPresent()){
            throw new ValidacionIntegridad("El topico al que requiere genrerar un respuesta, no se encuentra creado");
        }

        if (!usuarioRepository.findById(registroRespuesta.idUsuario()).isPresent()){
            throw new ValidacionIntegridad("El usuario de la respuesta no fue encontrado.");
        }

        Topico topico = topicoRepository.getReferenceById(registroRespuesta.idTopico());
        Usuario autor = usuarioRepository.getReferenceById(registroRespuesta.idUsuario());
        Respuesta respuesta = new Respuesta(registroRespuesta, topico, autor);
        Respuesta nuevaRespuesta = respuestaRepository.save(respuesta);

        DTORespuesta respuestaTopico=new DTORespuesta(nuevaRespuesta);

        URI url = uriComponentsBuilder.path("/respuestas/{id}").buildAndExpand(nuevaRespuesta.getId()).toUri();

        return ResponseEntity.created(url).body(respuestaTopico);

    }


    public ResponseEntity<Page> listarRespuestas(Pageable paginacion) {
        return ResponseEntity.ok(respuestaRepository.findByActivoTrue(paginacion)
                .map(DTOListarRespuesta::new));
    }

    public ResponseEntity<DTORespuesta> actualizarRespuesta(DTOActualizarRespuesta actualizarRespuesta, Long id, UriComponentsBuilder uriComponentsBuilder) {
        if (!respuestaRepository.existsById(id)){
            throw new ValidacionIntegridad("El topico con ese id, no ha sido creado");
        }


        Respuesta  respuesta=respuestaRepository.getReferenceById(id);
        respuesta.actualizarRespuesta(actualizarRespuesta);
        DTORespuesta respuestaActualizado=new DTORespuesta(respuesta);

        //return respuestaTopicoActualizado;

        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(respuesta.getId()).toUri();

        return ResponseEntity.created(url).body(respuestaActualizado);


    }

    public ResponseEntity eliminarTopicoPorId(Long id) {
        Optional<Respuesta> respuestaOptional = respuestaRepository.findById(id);
        if (respuestaOptional.isPresent()) {
            Respuesta respuesta = respuestaOptional.get();
            topicoRepository.deleteById(respuesta.getId());
            return ResponseEntity.ok().body("El respuesta se eliminó exitosamente");
        }

        return ResponseEntity.noContent().build();
    }
}
