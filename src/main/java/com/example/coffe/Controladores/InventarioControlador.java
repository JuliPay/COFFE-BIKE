package com.example.coffe.Controladores;

import com.example.coffe.Servicios.InventarioServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/inventario")
public class InventarioControlador {
    private final InventarioServicio inventarioServicio;

    public InventarioControlador(InventarioServicio inventarioServicio) {
        this.inventarioServicio = inventarioServicio;
    }

    @PostMapping("/verificar-stock")
    public ResponseEntity<String> verificarStock(@RequestParam Integer productoId, @RequestParam Integer cantidad) {
        boolean disponible = inventarioServicio.verificarStock(productoId, cantidad);
        Map<String, Object> response = new HashMap<>();
        response.put("productoId", productoId);
        response.put("cantidadSolicitada", cantidad);
        response.put("disponible", disponible);
        if (disponible) {
            response.put("mensaje", "Stock suficiente.");
            return ResponseEntity.ok(response.toString());
        }
        response.put("mensaje", "Stock insuficiente.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.toString());
    }

    @PostMapping("/producir")
    public ResponseEntity<String> producirProducto(@RequestParam Integer productoId, @RequestParam Integer cantidad) {
        if (!inventarioServicio.verificarStock(productoId, cantidad)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Stock insuficiente.");
        }
        inventarioServicio.consumirMateriaPrima(productoId, cantidad);
        return ResponseEntity.ok("Producción registrada exitosamente.");
    }

}
