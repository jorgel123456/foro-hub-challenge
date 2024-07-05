package com.alura.forohub.forohub.domain.curso;

public record DTOListarCurso(
        Long id,
        String nombre
) {
    public DTOListarCurso(Curso curso) {
        this(
                curso.getId(),
                curso.getNombre()
        );
    }
}
