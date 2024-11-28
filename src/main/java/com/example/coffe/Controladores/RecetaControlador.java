package com.example.coffe.Controladores;

import com.example.coffe.DTO.RecetaDTO;
import com.example.coffe.Entidades.Receta;
import com.example.coffe.Servicios.RecetaServicio;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/recetas")
public class RecetaControlador {
    private final RecetaServicio recetaServicio;

    public RecetaControlador(RecetaServicio recetaServicio) {
        this.recetaServicio = recetaServicio;
    }

    // Obtener todos los recetas
    @GetMapping
    public List<Receta> obtenerTodos() {
        return recetaServicio.obtenerTodasLasRecetas();
    }

    // Obtener una receta por ID
    @GetMapping("/{id}")
    public Optional<Receta> obtenerPorId(@PathVariable Integer id) {
        return recetaServicio.obtenerRecetaPorId(id);
    }

    // Agregar un nuevo receta
    @PostMapping
    public Receta agregarReceta(@RequestBody RecetaDTO recetaDTO) {
        return recetaServicio.agregarReceta(recetaDTO);
    }

    // Actualizar un receta por ID
    @PutMapping("/{id}")
    public Receta actualizarReceta(@PathVariable Integer id, @RequestBody RecetaDTO recetaDTO) {
        return recetaServicio.actualizarReceta(id, recetaDTO);
    }

    //eliminar receta por id
    @DeleteMapping("/{id}")
    public void eliminarReceta(@PathVariable Integer id) {
        recetaServicio.eliminarReceta(id);
    }


}
