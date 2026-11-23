package com.martinichef.recetas.service;

import com.martinichef.recetas.dto.RecetaRequest;
import com.martinichef.recetas.dto.RecetaResponse;
import com.martinichef.recetas.model.Receta;
import com.martinichef.recetas.repository.RecetaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class RecetaService {

    private final RecetaRepository recetaRepository;

    public RecetaService(RecetaRepository recetaRepository) {
        this.recetaRepository = recetaRepository;
    }

    @Transactional(readOnly = true)
    public List<RecetaResponse> listar() {
        return recetaRepository.findAll()
                .stream()
                .map(RecetaResponse::desdeEntidad)
                .toList();
    }

    @Transactional(readOnly = true)
    public RecetaResponse obtener(Long id) {
        Receta receta = buscarPorId(id);

        return RecetaResponse.desdeEntidad(receta);
    }

    public RecetaResponse crear(RecetaRequest request) {
        Receta receta = new Receta();

        aplicarDatos(receta, request);

        Receta guardada = recetaRepository.save(receta);

        return RecetaResponse.desdeEntidad(guardada);
    }

    public RecetaResponse actualizar(Long id, RecetaRequest request) {
        Receta receta = buscarPorId(id);

        aplicarDatos(receta, request);

        Receta actualizada = recetaRepository.save(receta);

        return RecetaResponse.desdeEntidad(actualizada);
    }

    public void eliminar(Long id) {
        Receta receta = buscarPorId(id);

        recetaRepository.delete(receta);
    }

    private Receta buscarPorId(Long id) {
        return recetaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "No existe una receta con el identificador " + id
                ));
    }

    private void aplicarDatos(Receta receta, RecetaRequest request) {
        receta.setTitulo(request.titulo().strip());
        receta.setDescripcion(request.descripcion().strip());
        receta.setTiempoMinutos(request.tiempoMinutos());

        receta.setIngredientes(
                request.ingredientes()
                        .stream()
                        .map(String::strip)
                        .toList()
        );

        receta.setPasos(
                request.pasos()
                        .stream()
                        .map(String::strip)
                        .toList()
        );
    }
}