package com.example.coffe.Controladores;

import com.example.coffe.DTO.MateriaPrimaDTO;
import com.example.coffe.Entidades.MateriaPrima;
import com.example.coffe.Servicios.MateriaPrimaServicio;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/materia")
public class MateriaPrimaControlador {
    private final MateriaPrimaServicio materiaServicio;

    public MateriaPrimaControlador(MateriaPrimaServicio materiaServicio) {
        this.materiaServicio = materiaServicio;
    }

    // Obtener todos los matrias primas
    @GetMapping
    public List<MateriaPrima> obtenerTodos() {
        return materiaServicio .obtenerTodos();
    }

    // Obtener un producto por ID
    @GetMapping("/{id}")
    public MateriaPrima obtenerPorId(@PathVariable Integer id) {
        return materiaServicio.obtenerPorId(id);
    }

    // Agregar un nuevo producto
    @PostMapping
    public MateriaPrima agregarUsuario(@RequestBody MateriaPrimaDTO materiaDTO) {
        return materiaServicio.agregarMateria(materiaDTO);
    }

    // Actualizar un producto por ID
    @PutMapping("/{id}")
    public MateriaPrima actualizarUsuario(@PathVariable Integer id, @RequestBody MateriaPrimaDTO materiaDTO) {
        return materiaServicio.actualizarMateria(id, materiaDTO);
    }

    @DeleteMapping("/{id}")
    public void eliminarMateria(@PathVariable Integer id) {
        materiaServicio.eliminarMateria(id);
    }

}
