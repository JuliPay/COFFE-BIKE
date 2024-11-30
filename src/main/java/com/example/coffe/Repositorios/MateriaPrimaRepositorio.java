package com.example.coffe.Repositorios;

import com.example.coffe.Entidades.MateriaPrima;
import com.example.coffe.Entidades.Productos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MateriaPrimaRepositorio extends JpaRepository<MateriaPrima, Integer> {
    List<MateriaPrima> findAll();

    Optional<MateriaPrima> findById(Integer id);

    MateriaPrima save( MateriaPrima materia);


}
