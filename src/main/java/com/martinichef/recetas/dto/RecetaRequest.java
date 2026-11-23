package com.martinichef.recetas.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record RecetaRequest(

        @NotBlank(message = "El título es obligatorio.")
        @Size(
                max = 120,
                message = "El título no puede superar los 120 caracteres."
        )
        String titulo,

        @NotBlank(message = "La descripción es obligatoria.")
        @Size(
                max = 2000,
                message = "La descripción no puede superar los 2000 caracteres."
        )
        String descripcion,

        @NotNull(message = "El tiempo de preparación es obligatorio.")
        @Min(
                value = 1,
                message = "El tiempo debe ser de al menos 1 minuto."
        )
        @Max(
                value = 1440,
                message = "El tiempo no puede superar los 1440 minutos."
        )
        Integer tiempoMinutos,

        @NotEmpty(message = "Debes agregar al menos un ingrediente.")
        @Size(
                max = 100,
                message = "No puedes agregar más de 100 ingredientes."
        )
        List<
                @NotBlank(message = "Los ingredientes no pueden estar vacíos.")
                @Size(
                        max = 500,
                        message = "Cada ingrediente admite hasta 500 caracteres."
                )
                        String
                > ingredientes,

        @NotEmpty(message = "Debes agregar al menos un paso.")
        @Size(
                max = 100,
                message = "No puedes agregar más de 100 pasos."
        )
        List<
                @NotBlank(message = "Los pasos no pueden estar vacíos.")
                @Size(
                        max = 2000,
                        message = "Cada paso admite hasta 2000 caracteres."
                )
                        String
                > pasos

) {
}