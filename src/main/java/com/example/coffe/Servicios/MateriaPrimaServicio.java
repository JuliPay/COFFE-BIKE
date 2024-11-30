package com.example.coffe.Servicios;

import com.example.coffe.DTO.MateriaPrimaDTO;
import com.example.coffe.Entidades.MateriaPrima;
import com.example.coffe.Repositorios.MateriaPrimaRepositorio;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class MateriaPrimaServicio {
    private final MateriaPrimaRepositorio materiaRepositorio;

    public MateriaPrimaServicio( MateriaPrimaRepositorio materiaRepository) {
        this.materiaRepositorio = materiaRepository;

    }

    public List<MateriaPrima> obtenerTodos() {
        return materiaRepositorio.findAll();
    }

    public MateriaPrima obtenerPorId(Integer id) {
        return materiaRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Materia no encontrado con el ID: " + id));
    }


    public MateriaPrima agregarMateria(MateriaPrimaDTO materiaDTO) {
        MateriaPrima materia = new MateriaPrima();
        materia.setNombre(materiaDTO.getNombre());
        materia.setUnidadMedida(materiaDTO.getUnidadMedida());
        materia.setCantidadDeUnidades(materiaDTO.getCantidadDeUnidades());
        materia.setPrecioUnitario(materiaDTO.getPrecioUnitario());

        return materiaRepositorio.save(materia);
    }



    public MateriaPrima actualizarMateria(Integer id, MateriaPrimaDTO materiaDTO) {
        return materiaRepositorio.findById(id).map(m -> {
            m.setNombre(materiaDTO.getNombre());
            m.setUnidadMedida(materiaDTO.getUnidadMedida());
            m.setCantidadDeUnidades(materiaDTO.getCantidadDeUnidades());
            m.setPrecioUnitario(materiaDTO.getPrecioUnitario());
            return materiaRepositorio.save(m);
        }).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public void eliminarMateria(Integer id) {
        if (!materiaRepositorio.existsById(id)) {
            throw new RuntimeException("Materia no encontrada con el ID: " + id);
        }
        materiaRepositorio.deleteById(id);
    }






}
