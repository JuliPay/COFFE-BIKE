package com.example.coffe.Repositorios;

import com.example.coffe.Entidades.Productos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepositorio extends JpaRepository<Productos, Integer> {

    List<Productos> findAll();

    Optional<Productos> findById(Integer id);

    Productos save(Productos producto);

    void delete(Productos productosAEliminar);

}
