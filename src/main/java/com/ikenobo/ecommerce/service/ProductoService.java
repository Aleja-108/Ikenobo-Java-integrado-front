package com.ikenobo.ecommerce.service;

import com.ikenobo.ecommerce.exception.ProductoNoEncontradoException;
import com.ikenobo.ecommerce.exception.ProductoDuplicadoException;
//import com.ikenobo.ecommerce.exception.PrecioInvalidoException;
//import com.ikenobo.ecommerce.exception.StockInsuficienteException;
import com.ikenobo.ecommerce.model.Producto;
import com.ikenobo.ecommerce.repository.ProductoRepository;

import org.springframework.stereotype.Service;
//import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoService {

    //private List<Producto> productos =
    //        new ArrayList<>();

    //private static int contadorId = 1;

    // Inyección por constructor: Spring pasa el repositorio.
    private final ProductoRepository repository;

    public ProductoService(ProductoRepository repository) {
        this.repository = repository;
    }


    // CREATE - Agrega un nuevo producto con validaciones completas
    public Producto guardar(Producto p) throws ProductoDuplicadoException {
        

    // 1. EL NOMBRE NO DEBE ESTAR VACÍO
    //    if (p.getNombre() == null || p.getNombre().trim().isBlank()) {
    //        throw new IllegalArgumentException("El nombre del producto no puede estar vacío");
    //}

    // 2. VALIDACIÓN: El precio debe ser mayor a cero
    //if (p.getPrecio() <= 0) {
    //    throw new PrecioInvalidoException("El precio debe ser mayor a cero. Ingreaste: " + p.getPrecio());
    //}

    // 3. VALIDACIÓN: El stock no puede ser negativo
    //if (p.getStock() < 0) {
    //    throw new StockInsuficienteException("El stock no puede ser negativo. Ingreaste: " + p.getStock());
    //}

    // 4. VALIDACIÓN: Revisar si el nombre ya existe
    //    for (Producto existente : productos) {
    //    if (existente.getNombre().equalsIgnoreCase(p.getNombre())) {
    //        throw new ProductoDuplicadoException(
    //            "Ya existe un producto con el nombre: " + p.getNombre());
    //    }
    // }
    //    for (Producto existente : repository.findAll()) {
    //    if (existente.getNombre().equalsIgnoreCase(p.getNombre())) {
    //        throw new ProductoDuplicadoException(
    //            "Ya existe un producto con el nombre: " + p.getNombre());
    //            }
    //}

    // 5. SI PASÓ TODAS LAS VALIDACIONES, SE GUARDA REALMENTE:
    //    p.setId(contadorId);

    //    contadorId++;

    //    productos.add(p);
    //    System.out.println("Producto guardado: " + p); 
    //    return p;
    
        return repository.save(p);
    }

    // READ
    public List<Producto> listarTodos() {
        return repository.findAll();
    }

    // READ BY ID
    public Producto obtenerPorId(int id) 
            throws ProductoNoEncontradoException {

        //for (Producto p : productos) {
        //    if (p.getId() == id) {
        //        return p;
        //    }
        //}

        //throw new ProductoNoEncontradoException(
        //        "No se encontró el producto con ID "
        //                + id);
        
        return repository.findById(id)
                .orElseThrow(() -> new ProductoNoEncontradoException(
                        "No se encontró el producto ID " + id));
    }

    // UPDATE
    public Producto actualizar(
            int id,
            Producto datos) 
            throws ProductoNoEncontradoException {
        
    //    if (datos.getNombre() == null || datos.getNombre().isBlank()) {
    //        throw new IllegalArgumentException("El nombre del producto no puede estar vacío");
    //    }
    //    if (datos.getPrecio() <= 0) {
    //        throw new PrecioInvalidoException("El precio debe ser mayor a cero. Ingresaste: " + datos.getPrecio());
    //    }
    //    if (datos.getStock() < 0) {
    //        throw new StockInsuficienteException("El stock no puede ser negativo. Ingresaste: " + datos.getStock());
    //    }

        Producto p =
                obtenerPorId(id);


        p.setNombre(datos.getNombre());

        p.setPrecio(datos.getPrecio());

        p.setStock(datos.getStock());

        p.setCategoria(datos.getCategoria());
        if (datos.getImagenUrl() != null) {
            p.setImagenUrl(datos.getImagenUrl());
        }; 

        return repository.save(p);
    }

    // DELETE
    public void eliminar(Integer id) 
    //        throws ProductoNoEncontradoException 
            {

        Producto p =
                obtenerPorId(id);

        repository.delete(p);
    }

    public List<Producto> buscarPorNombre(String nombre) {
        return repository.findByNombreContaining(nombre);
    }

    public List<Producto> buscarPorCategoria(String categoria) {
        return repository.findByCategoriaNombreContainingIgnoreCase(categoria);
    }
}