package com.alura.forohub.forohub.domain.respuesta;

import com.alura.forohub.forohub.domain.topico.Topico;
import com.alura.forohub.forohub.domain.usuario.Usuario;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.UniqueElements;

import java.time.LocalDateTime;

public record DTORegistroRespuesta(

String mensaje,
Long idTopico,
Long idUsuario

) {
}
