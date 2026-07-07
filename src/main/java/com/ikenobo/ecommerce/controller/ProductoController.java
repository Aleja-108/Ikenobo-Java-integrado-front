package com.ikenobo.ecommerce.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.bind.annotation.RequestMapping;

//import com.ikenobo.ecommerce.exception.ProductoNoEncontradoException;
import com.ikenobo.ecommerce.model.Producto;
import com.ikenobo.ecommerce.service.ProductoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/productos")
@CrossOrigin(origins = "*") // <-- Esto es obligatorio

public class ProductoController {
    private final ProductoService productoService;

    public ProductoController(ProductoService service) {
        this.productoService = service;
    }
    
/*    @GetMapping
    public List<Producto> listarProductos() {
        return productoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Producto obtenerProducto (@PathVariable int id){
        return productoService.obtenerPorId(id);
    }

    @PostMapping("")
    public Producto crearProducto(@RequestBody Producto producto) {
        return productoService.guardar(producto);
    }

    @PutMapping("/{id}")
    public Producto actualizar(@PathVariable int id, @RequestBody Producto datos) {

        return productoService.actualizar(id, datos);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id) {
        productoService.eliminar(id);
    }
        */
    
    @GetMapping
    public ResponseEntity<List<Producto>> listarTodos() {
    //    List<Producto> productos = productoService.listarTodos();
        return ResponseEntity.ok(productoService.listarTodos());
    }

    
    // Si existe: 200 - Si no existe: 404 Not Found
    
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProducto(@PathVariable Integer id) {
    //    try {
    //        Producto producto = productoService.obtenerPorId(id);
    //        return ResponseEntity.ok(producto);
    //    } catch (ProductoNoEncontradoException e) {
            return ResponseEntity.ok(productoService.obtenerPorId(id));
        }
    

    // Devuelve 201 Created 

    @PostMapping
    public ResponseEntity<Producto> crearProducto(@Valid @RequestBody Producto nuevoProducto) {
    //    Producto creado = productoService.guardar(nuevoProducto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.guardar(nuevoProducto));
    }

        // Si existe: 200 - Si no existe: 404 Not Found
    
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizar(@PathVariable Integer id, @Valid @RequestBody Producto datos) {
    //    try {
    //        Producto actualizado = productoService.actualizar(id, datos);
            return ResponseEntity.ok(productoService.actualizar(id, datos));
    //    } catch (ProductoNoEncontradoException e) {
    //        return ResponseEntity.notFound().build();
        }
    

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
    //    try {
           productoService.eliminar(id);
            return ResponseEntity.ok().build();
    //    } catch (ProductoNoEncontradoException e) {
    //        return ResponseEntity.notFound().build();
    //    }
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<Producto>> buscarPorNombre(@PathVariable String nombre) {
        return ResponseEntity.ok(productoService.buscarPorNombre(nombre));
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Producto>> buscarPorCategoria(@PathVariable String categoria) {
        return ResponseEntity.ok(productoService.buscarPorCategoria(categoria));
    }
}
