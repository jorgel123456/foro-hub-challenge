package com.alura.forohub.forohub.service;

import com.alura.forohub.forohub.domain.curso.Curso;
import com.alura.forohub.forohub.domain.curso.DTORegistroCurso;
import com.alura.forohub.forohub.domain.curso.DTORespuestaCurso;
import com.alura.forohub.forohub.domain.perfil.Perfil;
import com.alura.forohub.forohub.domain.usuario.DTORegistroUsuario;
import com.alura.forohub.forohub.domain.usuario.DTORespuestaUsuario;
import com.alura.forohub.forohub.domain.usuario.Usuario;
import com.alura.forohub.forohub.infra.errores.ValidacionIntegridad;
import com.alura.forohub.forohub.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;

    public DTORespuestaCurso registrarCurso(DTORegistroCurso registroCurso) {
        Curso curso=cursoRepository.save(new Curso(registroCurso));
        DTORespuestaCurso respuestaCurso= new DTORespuestaCurso(curso);
        //URI url=uriComponentsBuilder.path("/medico/{id}").buildAndExpand(medico.getId()).toUri();
        return respuestaCurso;
    }
}
