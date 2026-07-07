package com.ikenobo.ecommerce.service;

//import java.util.ArrayList;
import java.util.List;
import com.ikenobo.ecommerce.exception.CategoriaNoEncontradaException;
import com.ikenobo.ecommerce.exception.CategoriaNombreInvalidoException;
import com.ikenobo.ecommerce.model.Categoria;
import com.ikenobo.ecommerce.repository.CategoriaRepository;

import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    //private List<Categoria> categorias = new ArrayList<>();
    //private static int contadorId = 1;
    private final CategoriaRepository repository;

    public CategoriaService(CategoriaRepository repository) {
        this.repository = repository;
    }

    public Categoria guardar(Categoria c) {
        if (c.getNombre() == null || c.getNombre().isBlank()) {
            throw new CategoriaNombreInvalidoException("El nombre de la categoría no puede estar vacío.");
        }
        //c.setId(contadorId);
        //contadorId++;
        //categorias.add(c);
        //return c;
        return repository.save(c);
    }

    public List<Categoria> listarTodas() {
        return repository.findAll();
    }

    //public Categoria obtenerPorId(int id) {
    //    for (Categoria c : categorias) {
    //        if (c.getId() == id) {
    //            return c;
    //        }
    //    }
    //    throw new CategoriaNoEncontradaException("No se encontró una categoría con id " + id);
    //}
    public Categoria obtenerPorId(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new CategoriaNoEncontradaException("No se encontró una categoría con id " + id));
    }

    public Categoria actualizar(int id, Categoria datos) {
        if (datos.getNombre() == null || datos.getNombre().isBlank()) {
            throw new CategoriaNombreInvalidoException("El nombre de la categoría no puede estar vacío.");
        }
        Categoria c = obtenerPorId(id);
        c.setNombre(datos.getNombre());
        c.setDescripcion(datos.getDescripcion());
        return repository.save(c);
    }

    public void eliminar(int id) {
        Categoria c = obtenerPorId(id);
        repository.delete(c);
    }
}
