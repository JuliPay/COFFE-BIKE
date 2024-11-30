package com.example.coffe.Repositorios;

import com.example.coffe.Entidades.MateriaPrimaRecetas;
import com.example.coffe.Entidades.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MateriaPrimaRecetaRepositorio  extends JpaRepository<MateriaPrimaRecetas, Integer> {
}
