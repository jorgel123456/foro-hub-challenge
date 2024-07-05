package com.alura.forohub.forohub.domain.topico;

import com.alura.forohub.forohub.domain.curso.Curso;
import com.alura.forohub.forohub.domain.respuesta.Respuesta;
import com.alura.forohub.forohub.domain.usuario.Usuario;
import com.alura.forohub.forohub.infra.errores.ValidacionIntegridad;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "topicos")
public class Topico implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fecha;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private Usuario usuario;

    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    List<Respuesta> respuesta;

    private boolean activo;

    public String getActivo() {
        String act;
        if (activo){
            act= "activo";
        }else {
            act="inactivo";
        }
        return act;
    }

    public Topico(DTOCrearTopico crearTopico, Curso curso, Usuario usuario) {
        this.titulo = crearTopico.titulo();
        this.mensaje = crearTopico.mensaje();
        LocalDateTime hora = LocalDateTime.now();
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String horaFormateada = hora.format(formateador);
        this.fecha = LocalDateTime.parse(horaFormateada, formateador);
        this.usuario=usuario;
        this.curso=curso;
        this.activo= true;
    }

    public void actualizarTopico(DTOActualizarTopico actualizarTopico, Curso curso) {
        this.titulo = actualizarTopico.titulo();
        this.mensaje = actualizarTopico.mensaje();
        this.curso=curso;
        if (actualizarTopico.estado().toUpperCase().equals("ACTIVO")){
            this.activo= true;
        } else if (actualizarTopico.estado().toUpperCase().equals("INACTIVO")) {
            this.activo=false;
            }else{
            throw new ValidacionIntegridad("Ingrese un estado valido");
        }

    }



}
