package com.example.coffe.Servicios;

import com.example.coffe.Entidades.Factura;
import com.example.coffe.Entidades.Productos;
import com.example.coffe.Repositorios.FacturaRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class FacturaServicio {
    private final InventarioServicio inventarioServicio;
    private final FacturaRepositorio facturaRepositorio;

    public FacturaServicio(InventarioServicio inventarioServicio, FacturaRepositorio facturaRepositorio) {
        this.inventarioServicio = inventarioServicio;
        this.facturaRepositorio = facturaRepositorio;
    }

    @Transactional
    public Factura realizarCompra(Integer productoId, Integer cantidad) {
        // Verificar stock
        boolean disponible = inventarioServicio.verificarStock(productoId, cantidad);
        if (!disponible) {
            throw new IllegalStateException("No hay stock suficiente para realizar la compra.");
        }

        // Consumir materias primas
        inventarioServicio.consumirMateriaPrima(productoId, cantidad);

        // Calcular total
        Productos producto = inventarioServicio.obtenerProductoPorId(productoId);
        // Convertir el precioUnitario y la cantidad a BigDecimal antes de multiplicar
        BigDecimal precioUnitarioDecimal = BigDecimal.valueOf(producto.getPrecioUnitario());
        BigDecimal cantidadDecimal = BigDecimal.valueOf(cantidad);
        BigDecimal total = precioUnitarioDecimal.multiply(cantidadDecimal);

        // Generar factura
        Factura factura = new Factura();
        factura.setFecha(LocalDateTime.now());
        factura.setProducto(producto);
        factura.setCantidad(cantidad);
        factura.setTotal(total);

        return facturaRepositorio.save(factura);
    }
}
