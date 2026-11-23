package com.martinichef.recetas.controller;

import com.martinichef.recetas.dto.RecetaRequest;
import com.martinichef.recetas.dto.RecetaResponse;
import com.martinichef.recetas.service.RecetaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/recetas")
public class RecetaController {

    private final RecetaService recetaService;

    public RecetaController(RecetaService recetaService) {
        this.recetaService = recetaService;
    }

    @GetMapping
    public List<RecetaResponse> listar() {
        return recetaService.listar();
    }

    @GetMapping("/{id}")
    public RecetaResponse obtener(@PathVariable("id") Long id) {
        return recetaService.obtener(id);
    }

    @PostMapping
    public ResponseEntity<RecetaResponse> crear(
            @Valid @RequestBody RecetaRequest request
    ) {
        RecetaResponse creada = recetaService.crear(request);

        URI ubicacion = URI.create("/api/recetas/" + creada.id());

        return ResponseEntity.created(ubicacion).body(creada);
    }

    @PutMapping("/{id}")
    public RecetaResponse actualizar(
            @PathVariable("id") Long id,
            @Valid @RequestBody RecetaRequest request
    ) {
        return recetaService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable("id") Long id) {
        recetaService.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}