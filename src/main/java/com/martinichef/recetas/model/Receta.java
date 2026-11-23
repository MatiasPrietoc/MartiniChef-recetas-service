package com.martinichef.recetas.model;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "recetas")
public class Receta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String titulo;

    @Column(nullable = false, length = 2000)
    private String descripcion;

    @Column(nullable = false)
    private Integer tiempoMinutos;

    @ElementCollection
    @CollectionTable(
            name = "receta_ingredientes",
            joinColumns = @JoinColumn(name = "receta_id")
    )
    @OrderColumn(name = "posicion")
    @Column(name = "ingrediente", nullable = false, length = 500)
    private List<String> ingredientes = new ArrayList<>();

    @ElementCollection
    @CollectionTable(
            name = "receta_pasos",
            joinColumns = @JoinColumn(name = "receta_id")
    )
    @OrderColumn(name = "posicion")
    @Column(name = "paso", nullable = false, length = 2000)
    private List<String> pasos = new ArrayList<>();

    public Receta() {
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getTiempoMinutos() {
        return tiempoMinutos;
    }

    public void setTiempoMinutos(Integer tiempoMinutos) {
        this.tiempoMinutos = tiempoMinutos;
    }

    public List<String> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<String> ingredientes) {
        this.ingredientes = new ArrayList<>(ingredientes);
    }

    public List<String> getPasos() {
        return pasos;
    }

    public void setPasos(List<String> pasos) {
        this.pasos = new ArrayList<>(pasos);
    }
}