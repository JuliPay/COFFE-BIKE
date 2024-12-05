package com.example.coffe.Repositorios;

import com.example.coffe.Entidades.Productos;
import com.example.coffe.Entidades.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecetaRepositorio extends JpaRepository<Receta, Integer> {
    List<Receta> findAll();

    Optional<Receta> findById(Integer id);

    Receta save(Receta receta);

    void delete(Receta recetasAEliminar);

    Optional<Receta> findByProducto_Id(Integer productoId);
}
