package com.ikenobo.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ikenobo.ecommerce.model.Categoria;


public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    
}
