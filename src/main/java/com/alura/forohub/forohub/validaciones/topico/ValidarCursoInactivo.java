package com.alura.forohub.forohub.validaciones.topico;

import com.alura.forohub.forohub.domain.topico.DTOCrearTopico;
import com.alura.forohub.forohub.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ValidarCursoInactivo implements ValidarTopico{
    @Autowired
    private CursoRepository cursoRepository;
    @Override
    public void validar(DTOCrearTopico crearTopico) {
        var cursoActivo=cursoRepository.findActivoById(crearTopico.idCurso());
        if (!cursoActivo.isActivo()) {
            throw new RuntimeException("No se puede crear el topico, por que el curso de encuentra inactivo");
        }
    }
}
