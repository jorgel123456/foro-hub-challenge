package com.alura.forohub.forohub.domain.topico;

import java.time.LocalDateTime;

public record DTOListarTopicoCurso(
        Long idCurso,
        LocalDateTime fecha
) {
}
