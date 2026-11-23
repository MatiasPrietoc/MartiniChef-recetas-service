package com.martinichef.recetas.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

    public record ErrorRespuesta(
            int estado,
            String mensaje,
            Map<String, String> errores
    ) {
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorRespuesta> manejarValidacion(
            MethodArgumentNotValidException exception
    ) {
        Map<String, String> errores = new LinkedHashMap<>();

        exception.getBindingResult()
                .getFieldErrors()
                .forEach(error -> errores.putIfAbsent(
                        error.getField(),
                        error.getDefaultMessage() != null
                                ? error.getDefaultMessage()
                                : "Valor inválido."
                ));

        ErrorRespuesta respuesta = new ErrorRespuesta(
                400,
                "Revisa los datos de la receta.",
                errores
        );

        return ResponseEntity.badRequest().body(respuesta);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorRespuesta> manejarJsonInvalido(
            HttpMessageNotReadableException exception
    ) {
        ErrorRespuesta respuesta = new ErrorRespuesta(
                400,
                "El cuerpo de la petición tiene un formato incorrecto.",
                Map.of()
        );

        return ResponseEntity.badRequest().body(respuesta);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorRespuesta> manejarErrorHttp(
            ResponseStatusException exception
    ) {
        ErrorRespuesta respuesta = new ErrorRespuesta(
                exception.getStatusCode().value(),
                exception.getReason() != null
                        ? exception.getReason()
                        : "No se pudo completar la operación.",
                Map.of()
        );

        return ResponseEntity
                .status(exception.getStatusCode())
                .body(respuesta);
    }
}