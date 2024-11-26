package com.example.coffe.Controladores;

import com.example.coffe.DTO.EliminarProductoDTO;
import com.example.coffe.DTO.ProductoDTO;
import com.example.coffe.Entidades.Productos;
import com.example.coffe.Servicios.ProductosServicio;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductosControlador {

    private final ProductosServicio productosServicio;

    public ProductosControlador(ProductosServicio productosServicio) {

        this.productosServicio = productosServicio;
    }

    // Obtener todos los productos
    @GetMapping
    public List<Productos> obtenerTodos() {
        return productosServicio.obtenerTodos();
    }

    // Obtener un producto por ID
    @GetMapping("/{id}")
    public Productos obtenerPorId(@PathVariable Integer id) {
        return productosServicio.obtenerPorId(id);
    }

    // Agregar un nuevo producto
    @PostMapping
    public Productos agregarUsuario(@RequestBody ProductoDTO productosDTO) {
        return productosServicio.agregarProducto(productosDTO);
    }

    // Actualizar un producto por ID
    @PutMapping("/{id}")
    public Productos actualizarUsuario(@PathVariable Integer id, @RequestBody ProductoDTO productosDTO) {
        return productosServicio.actualizarProducto(id, productosDTO);
    }

    @DeleteMapping("/eliminar")
    public void eliminarUsuario(@RequestBody EliminarProductoDTO eliminarProductoDTO) {
        productosServicio.eliminarProductoPorId(
                eliminarProductoDTO.getIdproductoaEliminar(),
                eliminarProductoDTO.getContraseñaJefe()
        );
    }



}
