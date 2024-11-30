package com.example.coffe.Servicios;


import com.example.coffe.Entidades.MateriaPrima;
import com.example.coffe.Entidades.MateriaPrimaRecetas;
import com.example.coffe.Entidades.Productos;
import com.example.coffe.Entidades.Receta;
import com.example.coffe.Repositorios.MateriaPrimaRecetaRepositorio;
import com.example.coffe.Repositorios.MateriaPrimaRepositorio;
import com.example.coffe.Repositorios.ProductoRepositorio;
import com.example.coffe.Repositorios.RecetaRepositorio;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class InventarioServicio {
    private final MateriaPrimaRepositorio materiaPrimaRepositorio;
    private final RecetaRepositorio recetaRepositorio;
    private final ProductoRepositorio productoRepositorio;
    private final MateriaPrimaRecetaRepositorio materiaPrimaRecetaRepositorio;

    public InventarioServicio(MateriaPrimaRepositorio materiaPrimaRepositorio, RecetaRepositorio recetaRepositorio, ProductoRepositorio productoRepositorio, MateriaPrimaRecetaRepositorio materiaPrimaRecetaRepositorio) {
        this.materiaPrimaRepositorio = materiaPrimaRepositorio;
        this.recetaRepositorio = recetaRepositorio;
        this.productoRepositorio = productoRepositorio;
        this.materiaPrimaRecetaRepositorio = materiaPrimaRecetaRepositorio;
    }

    public Productos obtenerProductoPorId(Integer productoId) {
        return productoRepositorio.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con el ID: " + productoId));
    }

    // Inicializar datos predeterminados
    @PostConstruct
    public void inicializarDatos() {
        // Materia Prima inicial
        MateriaPrima mora = materiaPrimaRepositorio.save(new MateriaPrima("Mora","gramos",new BigDecimal("4535.92"),10000));
        MateriaPrima fresa = materiaPrimaRepositorio.save(new MateriaPrima("Fresa","gramos",new BigDecimal("4535.92"),10000));
        MateriaPrima yerbabuena = materiaPrimaRepositorio.save(new MateriaPrima("Yerbabuena","gramos",new BigDecimal("2267.96"),5000));


        // Producto inicial
        Productos aromaticas = productoRepositorio.save(new Productos("Aromática de frutos rojos", "Mezcla de mora, fresa y yerbabuena.", 10, Productos.Categoria.BEBIDA, 5500));

        // Crear la receta para el producto
        Receta recetaDeProducto = recetaRepositorio.save(new Receta(aromaticas, "Preparación de aromática de frutos rojos"));

        // Asociar materias primas a la receta
        materiaPrimaRecetaRepositorio.save(new MateriaPrimaRecetas(recetaDeProducto, mora, new BigDecimal("10")));
        materiaPrimaRecetaRepositorio.save(new MateriaPrimaRecetas(recetaDeProducto, fresa, new BigDecimal("10")));
        materiaPrimaRecetaRepositorio.save(new MateriaPrimaRecetas(recetaDeProducto, yerbabuena, new BigDecimal("5")));

    }

    public boolean verificarStock(Integer productoId, Integer cantidad) {
        // Buscar la receta asociada al producto
        List<Receta> recetas = recetaRepositorio.findByProductoId(productoId);

        if (recetas == null || recetas.isEmpty()) {
            throw new IllegalArgumentException("No se encontró una receta para el producto con ID: " + productoId);
        }

        // Obtener la primera receta
        Receta receta = recetas.get(0); // Suponiendo que siempre debe haber solo una receta asociada al producto

        // Verificar cada ingrediente de la receta
        for (MateriaPrimaRecetas ingrediente : receta.getIngredientes()) {
            MateriaPrima materia = ingrediente.getMateriaPrima();

            if (materia.getCantidadDeUnidades() == null) {
                materia.setCantidadDeUnidades(BigDecimal.ZERO); // Valor predeterminado si es nulo
            }

            BigDecimal requerido = ingrediente.getCantidadIngrediente().multiply(new BigDecimal(cantidad));

            if (materia.getCantidadDeUnidades().compareTo(requerido) < 0) {
                return false; // No hay suficiente materia prima
            }
        }

        return true; // Hay suficiente materia prima para todos los ingredientes
    }



    // Actualizar stock tras una producción
    @Transactional
    public void consumirMateriaPrima(Integer productoId, Integer cantidad) {
        // Buscar la receta asociada al producto
        List<Receta> recetas = recetaRepositorio.findByProductoId(productoId);

        if (recetas == null || recetas.isEmpty()) {
            throw new IllegalArgumentException("No se encontró una receta para el producto con ID: " + productoId);
        }

        // Obtener la primera receta
        Receta receta = recetas.get(0); // Suponiendo que siempre debe haber solo una receta asociada al producto

        // Consumir los ingredientes necesarios
        for (MateriaPrimaRecetas ingrediente : receta.getIngredientes()) {
            MateriaPrima materia = ingrediente.getMateriaPrima();

            if (materia.getCantidadDeUnidades() == null) {
                materia.setCantidadDeUnidades(BigDecimal.ZERO); // Valor predeterminado si es nulo
            }

            BigDecimal requerido = ingrediente.getCantidadIngrediente().multiply(new BigDecimal(cantidad));

            if (materia.getCantidadDeUnidades().compareTo(requerido) < 0) {
                throw new IllegalStateException("No hay suficiente materia prima: " + materia.getNombre());
            }

            // Actualizar el stock
            materia.setCantidadDeUnidades(materia.getCantidadDeUnidades().subtract(requerido));
            materiaPrimaRepositorio.save(materia); // Persistir cambios
        }
    }



}
