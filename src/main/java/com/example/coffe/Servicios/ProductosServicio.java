package com.example.coffe.Servicios;

import com.example.coffe.DTO.ProductoDTO;
import com.example.coffe.Entidades.Productos;
import com.example.coffe.Entidades.Usuarios;
import com.example.coffe.Repositorios.ProductoRepositorio;
import com.example.coffe.Repositorios.UsuarioRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductosServicio {
    private final ProductoRepositorio productosRepositorio;
    private final UsuarioRepositorio usuariosRepositorio;


    public ProductosServicio(ProductoRepositorio productosRepository, UsuarioRepositorio usuariosRepository ) {
        this.productosRepositorio = productosRepository;
        this.usuariosRepositorio = usuariosRepository;

    }

    public List<Productos> obtenerTodos() {
        return productosRepositorio.findAll();
    }

    public Productos obtenerPorId(Integer id) {
        return productosRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con el ID: " + id));
    }

    public Productos agregarProducto(ProductoDTO productoDTO) {
        Productos producto = new Productos();
        producto.setNombre(productoDTO.getNombre());
        producto.setDescripcion(productoDTO.getDescripcion());
        producto.setCategoria(productoDTO.getCategoria());
        producto.setPrecioUnitario(productoDTO.getPrecioUnitario());
        // Buscar el usuario por ID
        Usuarios usuario = usuariosRepositorio.findById(productoDTO.getId_usuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con el ID: " + productoDTO.getId_usuario()));

        producto.setId_usuario(usuario); // Asignar el usuario encontrado

        return productosRepositorio.save(producto);
    }

    public Productos actualizarProducto(Integer id, ProductoDTO productoDTO) {
        return productosRepositorio.findById(id).map(p -> {
            p.setNombre(productoDTO.getNombre());
            p.setDescripcion(productoDTO.getDescripcion());
            p.setCategoria(productoDTO.getCategoria());
            p.setPrecioUnitario(productoDTO.getPrecioUnitario());
            // Buscar el usuario por ID
            Usuarios usuario = usuariosRepositorio.findById(productoDTO.getId_usuario())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado con el ID: " + productoDTO.getId_usuario()));
            p.setId_usuario(usuario);

            return productosRepositorio.save(p);
        }).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    public void eliminarProductoPorId(Integer idProductoaEliminar, String contraseñaProporcionada) {
        // Buscar al único usuario con el rol Jefe
        Usuarios jefe = (Usuarios) usuariosRepositorio.findByRol(Usuarios.Rol.JEFE)
                .orElseThrow(() -> new RuntimeException("No se encontró ningún usuario con el rol Jefe."));

        // Validar que la contraseña proporcionada coincide con la del Jefe
        if (!jefe.getContraseña().equals(contraseñaProporcionada)) {
            throw new RuntimeException("Acceso denegado: Solo el jefe puede eliminar usuarios.");
        }

        // Buscar al usuario que se quiere eliminar
        Productos productoAEliminar = productosRepositorio.findById(idProductoaEliminar)
                .orElseThrow(() -> new RuntimeException("No se encontró un producto con el id especificado."));
        // Eliminar el usuario
        productosRepositorio.delete(productoAEliminar);
    }









}
