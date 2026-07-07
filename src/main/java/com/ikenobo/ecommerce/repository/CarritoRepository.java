package com.ikenobo.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ikenobo.ecommerce.model.Carrito;

public interface CarritoRepository extends JpaRepository<Carrito, Integer> {
    
}
