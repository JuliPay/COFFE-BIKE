package com.example.coffe.Controladores;

import com.example.coffe.DTO.FacturaDTO;
import com.example.coffe.Entidades.Factura;
import com.example.coffe.Servicios.FacturaServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/compras")
public class FacturaControlador {
    private final FacturaServicio facturaServicio;

    public FacturaControlador(FacturaServicio facturaServicio) {
        this.facturaServicio = facturaServicio;
    }

    @PostMapping("/realizar")
    public ResponseEntity<Map<String, Object>> realizarCompra(@RequestBody FacturaDTO compraDTO) {
        try {
            // Llamar al servicio pasando el productoId y la cantidad del DTO
            Factura factura = facturaServicio.realizarCompra(compraDTO.getProductoId(), compraDTO.getCantidad());

            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Compra realizada exitosamente.");
            response.put("factura", factura);
            return ResponseEntity.ok(response);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", e.getMessage()));
        }
    }
}
