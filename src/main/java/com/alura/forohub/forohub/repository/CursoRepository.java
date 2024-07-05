package com.alura.forohub.forohub.repository;

import com.alura.forohub.forohub.domain.curso.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    Optional<Curso> findByNombreContainsIgnoreCase(String s);
   Curso findActivoById(Long aLong);
}
