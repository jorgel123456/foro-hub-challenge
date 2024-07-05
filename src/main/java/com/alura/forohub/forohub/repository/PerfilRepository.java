package com.alura.forohub.forohub.repository;

import com.alura.forohub.forohub.domain.perfil.Perfil;
import com.alura.forohub.forohub.domain.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {

}
