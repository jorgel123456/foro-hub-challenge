package com.alura.forohub.forohub.domain.topico;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record IdValidar(
        @NotNull(message = "El ID no puede ser nulo")
        @Min(value = 1, message = "El ID debe ser mayor que 0")
        Long id
) {
}
