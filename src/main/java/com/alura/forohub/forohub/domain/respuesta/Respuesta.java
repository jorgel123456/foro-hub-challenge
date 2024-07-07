package com.alura.forohub.forohub.domain.respuesta;

import com.alura.forohub.forohub.domain.curso.Curso;
import com.alura.forohub.forohub.domain.topico.DTOCrearTopico;
import com.alura.forohub.forohub.domain.topico.Topico;
import com.alura.forohub.forohub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "respuestas")
public class Respuesta {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String mensaje;

    private LocalDateTime fecha;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "topico_id")
    private Topico topico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    private boolean activo;
    private boolean soluccion;

    public Respuesta(DTORegistroRespuesta registroRespuesta, Topico topico, Usuario usuario) {
        this.mensaje = registroRespuesta.mensaje();
        LocalDateTime hora = LocalDateTime.now();
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String horaFormateada = hora.format(formateador);
        this.fecha = LocalDateTime.parse(horaFormateada, formateador);
        this.usuario=usuario;
        this.topico=topico;
        this.activo= true;
        this.soluccion=true;
    }


    public void actualizarRespuesta(DTOActualizarRespuesta actualizarRespuesta) {
        this.mensaje=actualizarRespuesta.mensaje();
    }
}
