package com.KirgoDev.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.io.Serializable;

@Entity
@Table(name = "productos")
public class Producto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * El nombre del producto no puede estar vacío ni tener caracteres especiales.
     * Los caracteres permitidos son letras y números.
     */
    @NotBlank(message = "El nombre no puede estar vacío")
    @Pattern(regexp = "^[a-zA-Z0-9\\s]+$", message = "El nombre solo puede contener letras")
    private String nombre;

    /**
     * El precio debe ser mayor o igual a 0.00 y tener 2 decimales.
     */
    @NotNull(message = "El precio no puede ser nulo")
    @DecimalMin(value = "0.00", message = "El precio debe ser mayor o igual a 0.00")
    @Digits(integer = 10, fraction = 2, message = "El precio debe tener 2 decimales")
    private Double precio;

    /**
     * El stock debe ser mayor o igual a 0.
     */
    @NotNull(message = "El stock no puede ser nulo")
    @Min(value = 0, message = "El stock debe ser mayor o igual a 0")
    private Integer stock;

    /**
     * El estado del producto puede ser true o false.
     * Indica si el producto está disponible para la venta o no.
     */
    private boolean disponible;

    /**
     * La descripción del producto no puede estar vacía ni tener más de 200 caracteres.
     * Los caracteres permitidos son letras, números y espacios en blanco.
     */
    @NotBlank(message = "La descripción no puede estar vacía")
    @Size(min = 10, max = 200, message = "La descripción debe tener entre 10 y 200 caracteres")
    private String descripcion;

    /**
     * Relación Muchos-a-uno con la entidad Categoria.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id", nullable = false)
    @NotNull(message = "El producto debe pertenecer a una categoría")
    private Categoria categoria;

    public Producto(String s, String cervezaEstrellaGalicia, double v, int i, Categoria bebidas) {
    }

    // Getters and Setters
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

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Producto{" + "id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", stock=" + stock + ", disponible=" + disponible + ", descripcion=" + descripcion + ", categoria=" + (categoria != null ? categoria.getNombre() : "Sin categoria") + '}';
    }
}
