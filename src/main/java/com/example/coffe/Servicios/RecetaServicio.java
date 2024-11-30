package com.example.coffe.Servicios;

import com.example.coffe.DTO.RecetaDTO;
import com.example.coffe.Entidades.Productos;
import com.example.coffe.Entidades.Receta;
import com.example.coffe.Repositorios.MateriaPrimaRepositorio;
import com.example.coffe.Repositorios.ProductoRepositorio;
import com.example.coffe.Repositorios.RecetaRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecetaServicio {
    private final RecetaRepositorio recetaRepositorio;
    private final MateriaPrimaRepositorio materiaRepositorio;
    private final ProductoRepositorio productoRepositorio;


    public RecetaServicio( RecetaRepositorio recetaRepositorio, MateriaPrimaRepositorio materiaRepositorio, ProductoRepositorio productoRepositorio) {
        this.recetaRepositorio = recetaRepositorio;
        this.materiaRepositorio = materiaRepositorio;
        this.productoRepositorio= productoRepositorio;


    }

    // Obtener todas las recetas
    public List<Receta> obtenerTodasLasRecetas() {
        return recetaRepositorio.findAll();
    }

    // Obtener una receta por su ID
    public Optional<Receta> obtenerRecetaPorId(Integer id) {
        return recetaRepositorio.findById(id);
    }



    public Receta agregarReceta(RecetaDTO recetaDTO){
        Receta receta = new Receta();
        // Buscar el producto por ID
        Productos producto = productoRepositorio.findById(recetaDTO.getProducto())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con el ID: " + recetaDTO.getProducto()));

        receta.setProducto(producto); // Asignar el producto encontrado

        receta.setProcesoReceta(recetaDTO.getProcesoReceta());

        return recetaRepositorio.save(receta);
    }



    public Receta actualizarReceta(Integer id, RecetaDTO recetaDTO) {
        return recetaRepositorio.findById(id).map(r -> {
            // Buscar el producto por ID
            Productos producto = productoRepositorio.findById(recetaDTO.getProducto())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado con el ID: " + recetaDTO.getProducto()));

            r.setProducto(producto); // Asignar el producto encontrado


            r.setProcesoReceta(recetaDTO.getProcesoReceta());
            return recetaRepositorio.save(r);
        }).orElseThrow(() -> new RuntimeException("Receta no encontrada con ID: " + id));
    }

    public void eliminarReceta(Integer id) {
        if (!recetaRepositorio.existsById(id)) {
            throw new RuntimeException("Receta no encontrada con el ID: " + id);
        }
        recetaRepositorio.deleteById(id);
    }

}
