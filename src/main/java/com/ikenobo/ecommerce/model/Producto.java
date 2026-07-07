package com.ikenobo.ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity // Le avisa a Java que esto es una entidad de la BD
@Table(name = "producto") // Define el nombre de la tabla en MySQL

public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El nombre del producto no puede estar vacío")
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
    
    @Positive(message = "El precio del producto debe ser mayor a cero")
    @Column(name = "precio", nullable = false)
    private double precio;

    @PositiveOrZero(message = "El stock del producto no debe sernegativo")
    @Column(name = "stock", nullable = false)
    private int stock;

    @Column(name = "imagen_url")
    private String imagenUrl;
    //@Column(name = "categoria", nullable = false, length = 50)
    //private String categoria;

    // CAMBIA A:
    // @ManyToOne: muchos productos apuntan a una misma categoría.
    // @JoinColumn: la columna categoria_id de la tabla producto guarda la
    // clave foránea hacia la tabla categoria. Hibernate crea la FK solo.
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    /*public Producto(String nombre,
                     double precio,
                     int stock,
                     Categoria categoria) {

        //this.id = id;//
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
    }

    public Producto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    // Cambiado de String a Categoria
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    

    @Override
    public String toString() {

        return "ID: " + id +
                " | Nombre: " + nombre +
                " | Precio: $" + precio +
                " | Stock: " + stock +
                " | Categoria: " + categoria;
    }*/
}