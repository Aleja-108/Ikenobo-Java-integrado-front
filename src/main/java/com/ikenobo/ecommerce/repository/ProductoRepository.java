package com.ikenobo.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ikenobo.ecommerce.model.Producto;
import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    List<Producto> findByNombreContaining(String nombre);

    // Navega la relación hacia Categoria.
    // IgnoreCase → sin distinción de mayúsculas
    // Containing → LIKE %valor%
    List<Producto> findByCategoriaNombreContainingIgnoreCase(String nombre);
    
}
