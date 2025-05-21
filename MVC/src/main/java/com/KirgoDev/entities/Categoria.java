package com.KirgoDev.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "categorías")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(length = 400)
    private String descripcion;

    // Constructor vacio
    public Categoria() {}

    // Constructor con parámetros
    public Categoria(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // toString
    @Override
    public String toString() {
        return "Categoria{" + "id=" + id + ", nombre=" + nombre + ", descripción=" + descripcion + '}';
    }
}
/*
 * @author KirgoDev
 * @version 1.0
 * @created 10-05-2022 10:07:29 a. m.
 * @description Clase que representa una categoría de productos.
 */