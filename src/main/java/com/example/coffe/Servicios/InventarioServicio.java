package com.example.coffe.Servicios;


import com.example.coffe.Entidades.*;
import com.example.coffe.Repositorios.*;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class InventarioServicio {
    private final MateriaPrimaRepositorio materiaPrimaRepositorio;
    private final RecetaRepositorio recetaRepositorio;
    private final ProductoRepositorio productoRepositorio;
    private final MateriaPrimaRecetaRepositorio materiaPrimaRecetaRepositorio;
    private final FacturaRepositorio facturaRepositorio;

    public InventarioServicio(MateriaPrimaRepositorio materiaPrimaRepositorio, RecetaRepositorio recetaRepositorio, ProductoRepositorio productoRepositorio, MateriaPrimaRecetaRepositorio materiaPrimaRecetaRepositorio,FacturaRepositorio facturaRepositorio ) {
        this.materiaPrimaRepositorio = materiaPrimaRepositorio;
        this.recetaRepositorio = recetaRepositorio;
        this.productoRepositorio = productoRepositorio;
        this.materiaPrimaRecetaRepositorio = materiaPrimaRecetaRepositorio;
        this.facturaRepositorio = facturaRepositorio;
    }

    // Inicializar datos predeterminados
    @PostConstruct
    public void inicializarDatos() {
        // Materia Prima inicial
        MateriaPrima mora = materiaPrimaRepositorio.save(new MateriaPrima("Mora","gramos",new BigDecimal("4535.92"),10000));
        MateriaPrima fresa = materiaPrimaRepositorio.save(new MateriaPrima("Fresa","gramos",new BigDecimal("4535.92"),10000));
        MateriaPrima yerbabuena = materiaPrimaRepositorio.save(new MateriaPrima("Yerbabuena","gramos",new BigDecimal("2267.96"),5000));
        MateriaPrima limon = materiaPrimaRepositorio.save(new MateriaPrima("Limón", "gramos", new BigDecimal("1000"), 3000));


        // Producto inicial
        Productos aromaticas = productoRepositorio.save(new Productos("Aromática de frutos rojos", "Mezcla de mora, fresa y yerbabuena.", 10, Productos.Categoria.BEBIDA, new BigDecimal("5500")));
        Productos limonada = productoRepositorio.save(new Productos("Limonada natural", "Bebida refrescante de limón con yerbabuena.", 15, Productos.Categoria.BEBIDA, new BigDecimal("4000")));

        // Crear la receta para el producto
        Receta recetaDeProducto = recetaRepositorio.save(new Receta(aromaticas, "Preparación de aromática de frutos rojos"));

        // Receta para Limonada
        Receta recetaLimonada = recetaRepositorio.save(new Receta(limonada, "Preparación de limonada natural"));

        // Asociar materias primas a la receta
        materiaPrimaRecetaRepositorio.save(new MateriaPrimaRecetas(recetaDeProducto, mora, new BigDecimal("10")));
        materiaPrimaRecetaRepositorio.save(new MateriaPrimaRecetas(recetaDeProducto, fresa, new BigDecimal("10")));
        materiaPrimaRecetaRepositorio.save(new MateriaPrimaRecetas(recetaDeProducto, yerbabuena, new BigDecimal("5")));

        // Materias primas para Limonada
        materiaPrimaRecetaRepositorio.save(new MateriaPrimaRecetas(recetaLimonada, limon, new BigDecimal("50")));
        materiaPrimaRecetaRepositorio.save(new MateriaPrimaRecetas(recetaLimonada, yerbabuena, new BigDecimal("3")));

    }

    @Transactional
    public Factura realizarCompra(Map<Integer, Integer> productosCantidad) {
        BigDecimal precioTotal = BigDecimal.ZERO; // Precio total de la compra
        List<DetalleFactura> detallesFactura = new ArrayList<>(); // Lista para guardar los detalles

        // Iterar sobre cada producto y su cantidad
        for (Map.Entry<Integer, Integer> entrada : productosCantidad.entrySet()) {
            Integer productoId = entrada.getKey();
            Integer cantidadComprada = entrada.getValue();

            // 1. Obtener el producto
            Productos producto = productoRepositorio.findById(productoId)
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + productoId));

            if (producto.getCantidad() < cantidadComprada) {
                throw new RuntimeException("No hay suficiente inventario del producto: " + producto.getNombre());
            }

            // 2. Obtener la receta del producto
            Receta receta = recetaRepositorio.findByProducto_Id(productoId)
                    .orElseThrow(() -> new RuntimeException("No se encontró receta para el producto: " + producto.getNombre()));

            // 3. Verificar disponibilidad de materias primas
            for (MateriaPrimaRecetas detalle : receta.getMateriaPrimaRecetas()) {
                MateriaPrima materiaPrima = detalle.getMateriaPrima();
                BigDecimal cantidadRequerida = detalle.getCantidadIngrediente().multiply(new BigDecimal(cantidadComprada));

                if (materiaPrima.getCantidadDeUnidades().compareTo(cantidadRequerida) < 0) {
                    throw new RuntimeException("No hay suficiente " + materiaPrima.getNombre() + " en inventario.");
                }
            }

            // 4. Actualizar inventario de materias primas
            for (MateriaPrimaRecetas detalle : receta.getMateriaPrimaRecetas()) {
                MateriaPrima materiaPrima = detalle.getMateriaPrima();
                BigDecimal cantidadRequerida = detalle.getCantidadIngrediente().multiply(new BigDecimal(cantidadComprada));
                materiaPrima.setCantidadDeUnidades(materiaPrima.getCantidadDeUnidades().subtract(cantidadRequerida));
                materiaPrimaRepositorio.save(materiaPrima);
            }

            // 5. Actualizar inventario del producto
            producto.setCantidad(producto.getCantidad() - cantidadComprada);
            productoRepositorio.save(producto);

            // 6. Crear un detalle de la factura
            DetalleFactura detalleFactura = new DetalleFactura();
            detalleFactura.setProducto(producto);
            detalleFactura.setCantidad(cantidadComprada);
            detalleFactura.setPrecioUnitario(producto.getPrecioUnitario());
            detalleFactura.setSubtotal(producto.getPrecioUnitario().multiply(new BigDecimal(cantidadComprada)));
            detallesFactura.add(detalleFactura);

            // Acumular el precio total
            precioTotal = precioTotal.add(detalleFactura.getSubtotal());
        }

        // 7. Generar la factura
        Factura factura = new Factura();
        factura.setDetallesFactura(detallesFactura);
        factura.setPrecioTotal(precioTotal);
        factura.setFechaCompra(LocalDateTime.now());

        return facturaRepositorio.save(factura);
    }






}
