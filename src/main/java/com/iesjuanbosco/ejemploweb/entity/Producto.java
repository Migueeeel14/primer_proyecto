package com.iesjuanbosco.ejemploweb.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

//esta clase funcionará como entidad en la base de datos
@Entity
//nombre de la tabla en la base de datos
@Table(name="productos")
public class Producto {

    //clave principal de la bd
    @Id
    //de esta forma el id se generara de manera autoincrementada
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "El titulo no puede estar en blanco")
    private String titulo;
    @NotNull(message = "La cantidad no puede estar en blanco")
    private Integer cantidad;
    @NotNull(message = "El precio no puede estar en blanco")
    @Min(value=0, message = "El precio debe ser positivo")
    private Double precio;

    public Producto() {
    }

    public Producto(Long id, String titulo, Integer cantidad, Double precio) {
        this.id = id;
        this.titulo = titulo;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", cantidad=" + cantidad +
                ", precio=" + precio +
                '}';
    }
}
