package com.example.coffe.Repositorios;

import com.example.coffe.Entidades.Productos;
import com.example.coffe.Entidades.Receta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RecetaRepositorio extends JpaRepository<Receta, Integer> {
    List<Receta> findAll();

    Optional<Receta> findById(Integer id);

    Receta save(Receta receta);

    void delete(Receta recetasAEliminar);

    List<Receta> findByProductoId(Integer productoId);
}
