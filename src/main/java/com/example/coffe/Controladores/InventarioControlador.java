package com.example.coffe.Controladores;

import com.example.coffe.Entidades.Factura;
import com.example.coffe.Servicios.InventarioServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/inventario")
public class InventarioControlador {
    private final InventarioServicio inventarioServicio;

    public InventarioControlador(InventarioServicio inventarioServicio) {
        this.inventarioServicio = inventarioServicio;
    }

    @PostMapping
    public ResponseEntity<Factura> realizarCompra(@RequestBody Map<Integer, Integer> productosCantidad) {
        Factura factura = inventarioServicio.realizarCompra(productosCantidad);
        return ResponseEntity.ok(factura);
    }



}
