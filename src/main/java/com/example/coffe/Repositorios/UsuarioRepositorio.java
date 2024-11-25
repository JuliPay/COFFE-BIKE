package com.example.coffe.Repositorios;

import com.example.coffe.Entidades.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface UsuarioRepositorio extends JpaRepository<Usuarios, Integer> {
    List<Usuarios> findAll();

    Optional<Usuarios> findById(Integer id);

    Usuarios save(Usuarios usuario);

    void deleteById(Integer id);

    void delete(Usuarios usuarioAEliminar);

    Optional<Usuarios> findByNombre(String nombre);

    boolean existsByRol(Usuarios.Rol rol);

    Optional<Object> findByRol(Usuarios.Rol rol);
}
