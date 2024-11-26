package com.example.coffe.Controladores;

import com.example.coffe.DTO.EliminarUsuarioDTO;
import com.example.coffe.DTO.LoginDTO;
import com.example.coffe.DTO.UsuarioDTO;
import com.example.coffe.Entidades.Usuarios;
import com.example.coffe.Servicios.UsuariosServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuariosControlador {

    private final UsuariosServicio usuariosServicio;

    public UsuariosControlador(UsuariosServicio usuariosService) {
        this.usuariosServicio = usuariosService;
    }

    // Obtener todos los usuarios
    @GetMapping
    public List<Usuarios> obtenerTodos() {
        return usuariosServicio.obtenerTodos();
    }

    // Obtener un usuario por ID
    @GetMapping("/{id}")
    public Usuarios obtenerPorId(@PathVariable Integer id) {
        return usuariosServicio.obtenerPorId(id);
    }

    // Agregar un nuevo usuario
    @PostMapping
    public Usuarios agregarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        return usuariosServicio.agregarUsuario(usuarioDTO);
    }

    // Actualizar un usuario por ID
    @PutMapping("/{id}")
    public Usuarios actualizarUsuario(@PathVariable Integer id, @RequestBody UsuarioDTO usuarioDTO) {
        return usuariosServicio.actualizar(id, usuarioDTO);
    }

    @DeleteMapping("/eliminar")
    public void eliminarUsuario(@RequestBody EliminarUsuarioDTO eliminarUsuarioDTO) {
        usuariosServicio.eliminarUsuarioPorNombre(
                eliminarUsuarioDTO.getNombreUsuarioAEliminar(),
                eliminarUsuarioDTO.getContraseñaJefe()
        );
    }


    // Endpoint para el login
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO login) {
        Usuarios usuario = usuariosServicio.autenticarUsuario(login.getNombre(), login.getContraseña());


        // Devuelve el nombre y rol del usuario autenticado
        return ResponseEntity.ok("Bienvenido " + usuario.getNombre() + ". Tu rol es: " + usuario.getRol());
    }

    
}
