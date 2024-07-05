package com.alura.forohub.forohub.validaciones.topico;

import com.alura.forohub.forohub.domain.topico.DTOCrearTopico;
import com.alura.forohub.forohub.repository.CursoRepository;
import com.alura.forohub.forohub.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarUsuarioInactivo implements ValidarTopico {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Override
    public void validar(DTOCrearTopico crearTopico) {
        var usuarioActivo=usuarioRepository.findActivoById(crearTopico.idUsuario());
        if (!usuarioActivo.isActivo()) {
            throw new RuntimeException("No se puede crear un topico,el usuario se encuentra inactivo");
        }
    }
}
