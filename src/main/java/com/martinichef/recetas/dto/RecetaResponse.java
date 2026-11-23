package com.martinichef.recetas.dto;

import com.martinichef.recetas.model.Receta;

import java.util.List;

public record RecetaResponse(
        Long id,
        String titulo,
        String descripcion,
        Integer tiempoMinutos,
        List<String> ingredientes,
        List<String> pasos
) {

    public static RecetaResponse desdeEntidad(Receta receta) {
        return new RecetaResponse(
                receta.getId(),
                receta.getTitulo(),
                receta.getDescripcion(),
                receta.getTiempoMinutos(),
                List.copyOf(receta.getIngredientes()),
                List.copyOf(receta.getPasos())
        );
    }
}