package com.alura.forohub.forohub.repository;

import com.alura.forohub.forohub.domain.topico.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {
    //query para listar topicos, por un determinado titulo
    List<Topico> findByTitulo(String titulo);

    //listar Topicos activos, paginados cadda 10 topicos y de manera descendente
    @Query("SELECT t FROM Topico t ORDER BY t.fecha DESC")
    Page<Topico> findByActivoTrue(Pageable paginacion);

    //query para listar topicos por Curso y año
    @Query(value = "SELECT * FROM topicos t WHERE t.curso_id = :idCurso AND YEAR(t.fecha) = :fecha", nativeQuery = true)
    Page<Topico> findTopicoByCursoAndFecha(@Param("idCurso") Long idCurso, @Param("fecha") int fecha, Pageable pageable);



}
