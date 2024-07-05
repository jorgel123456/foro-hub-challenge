package com.alura.forohub.forohub.domain.usuario;

import com.alura.forohub.forohub.domain.perfil.Perfil;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
@Entity
@Table(name = "usuarios")
public class Usuario{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
    private String nombre;
    private String email;
    private String clave;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "perfil_id")
    private Perfil perfil;
    private boolean activo;

    public Usuario(Perfil perfil,DTORegistroUsuario registroUsuario) {
        this.nombre= registroUsuario.nombre();
        this.email= registroUsuario.email();
        this.clave=registroUsuario.clave();
        this.perfil=perfil;
        this.activo=true;
    }



}
