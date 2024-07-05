package com.alura.forohub.forohub.service;

import com.alura.forohub.forohub.domain.topico.*;
import com.alura.forohub.forohub.infra.errores.ValidacionIntegridad;
import com.alura.forohub.forohub.repository.CursoRepository;
import com.alura.forohub.forohub.repository.TopicoRepository;
import com.alura.forohub.forohub.repository.UsuarioRepository;
import com.alura.forohub.forohub.validaciones.topico.ValidarTopico;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    List<ValidarTopico> validadores;

    public DTORespuestaTopico crearTopico(DTOCrearTopico crearTopico){


        if(crearTopico.idUsuario()==null){
            throw new ValidacionIntegridad("Para crear un Topico se quiere ingresar como usuario.");
        }
        if (crearTopico.idCurso()==null){
            throw  new ValidacionIntegridad("Ingrese un curso para generar el topico ");
        }
        //validaciones respetando los principios SOLID
        validadores.forEach(v -> v.validar(crearTopico));

        if(!cursoRepository.findById(crearTopico.idCurso()).isPresent()){
            throw new ValidacionIntegridad("El curso no se encuentra registrado.");
        }
        if (!usuarioRepository.findById(crearTopico.idUsuario()).isPresent()){
            throw new ValidacionIntegridad("El usuario no se encuentra registrado");
        }
        //validaciones respetando los principios SOLID
        validadores.forEach(v -> v.validar(crearTopico));

        List<Topico> topicos = topicoRepository.findByTitulo(crearTopico.titulo());
        topicos.stream()
                .filter(t -> t.getMensaje().equalsIgnoreCase(crearTopico.mensaje()))
                .findFirst()
                .ifPresent(t -> {
                    throw new ValidationException("Ya se encuentra creado un topico con el titulo y mensaje");
                });


        var curso =cursoRepository.findById(crearTopico.idCurso()).get();
        var usuario=usuarioRepository.findById(crearTopico.idUsuario()).get();

        Topico topico = new Topico(crearTopico, curso, usuario);
        Topico nuevoTopico = topicoRepository.save(topico);
        DTORespuestaTopico respuestaTopico=new DTORespuestaTopico(nuevoTopico);

        return respuestaTopico;

    }

    public Page<DTOListarTopico> listarTopicos(Pageable paginacion) {
        return topicoRepository.findByActivoTrue(paginacion)
                .map(DTOListarTopico::new);
    }
    public Page<DTOListarTopico> listarTopicoPorCursoAndFecha(Long idCurso, int year, Pageable pageable) {
        return topicoRepository.findTopicoByCursoAndFecha(idCurso, year ,pageable)
                .map(DTOListarTopico::new);
    }

    public DTORespuestaTopicoPorId mostrarTopicoPorId(Long id) {
        Topico topico=topicoRepository.getReferenceById(id);
        DTORespuestaTopicoPorId topicoPorId=new DTORespuestaTopicoPorId(topico);
        return topicoPorId;
    }

    public ResponseEntity<DTORespuestaTopicoActualizado> actualizarTopicoPorId(DTOActualizarTopico actualizarTopico,
                                                               Long id, UriComponentsBuilder uriComponentsBuilder) {
        if (!topicoRepository.existsById(id)){
            throw new ValidacionIntegridad("El topico con ese id, no ha sido creado");
        }
        if (actualizarTopico.idCurso()==null){
            throw  new ValidacionIntegridad("Ingrese un curso para actualizar el topico ");
        }
        if(!cursoRepository.findById(actualizarTopico.idCurso()).isPresent()){
            throw new ValidacionIntegridad("El curso no se encuentra registrado.");
        }

        var curso =cursoRepository.findById(actualizarTopico.idCurso()).get();

        Topico topico=topicoRepository.getReferenceById(id);
        topico.actualizarTopico(actualizarTopico,curso);
        DTORespuestaTopicoActualizado respuestaTopicoActualizado=new DTORespuestaTopicoActualizado(topico);

        //return respuestaTopicoActualizado;

        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(url).body(respuestaTopicoActualizado);
    }

    public ResponseEntity eliminarTopicoPorId(Long id) {
        Optional<Topico> topicoOptional = topicoRepository.findById(id);
        if (topicoOptional.isPresent()) {
            Topico topico = topicoOptional.get();
            topicoRepository.deleteById(topico.getId());
            return ResponseEntity.ok().body("El topico se eliminó exitosamente");
        }

        return ResponseEntity.noContent().build();
    }
}
