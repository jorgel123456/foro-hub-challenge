package com.alura.forohub.forohub.domain.usuario;

import com.alura.forohub.forohub.domain.perfil.Perfil;
import com.alura.forohub.forohub.infra.errores.ValidacionIntegridad;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails {

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


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return clave;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void actualizarUsuario(DTOActualizarUsuario actualizarUsuario, Perfil perfil) {
        this.nombre=actualizarUsuario.nombre();
        this.email=actualizarUsuario.email();
        this.clave=actualizarUsuario.clave();
        this.perfil=perfil;

    }
}
