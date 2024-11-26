package com.example.coffe.Servicios;

import com.example.coffe.DTO.UsuarioDTO;
import com.example.coffe.Entidades.Usuarios;
import com.example.coffe.Repositorios.UsuarioRepositorio;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UsuariosServicio {
    private final UsuarioRepositorio usuariosRepositorio;

    public UsuariosServicio(UsuarioRepositorio usuariosRepository) {
        this.usuariosRepositorio = usuariosRepository;

    }

    public List<Usuarios> obtenerTodos() {
        return usuariosRepositorio.findAll();
    }

    public Usuarios obtenerPorId(Integer id) {
        return usuariosRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con el ID: " + id));
    }

    public Usuarios agregarUsuario(UsuarioDTO usuarioDTO) {
        //validar que solo haya un administrador y un jefe
        if (usuarioDTO.getRol() == Usuarios.Rol.JEFE || usuarioDTO.getRol() == Usuarios.Rol.ADMINISTRADOR) {
            boolean rolExistente = usuariosRepositorio.existsByRol(usuarioDTO.getRol());
            if (rolExistente) {
                throw new ResponseStatusException(HttpStatus. NOT_FOUND, "Ya existe un usuario con el rol: " + usuarioDTO.getRol());
            }
        }

        // Validar que el rol especificado exista
        try {
            Usuarios.Rol.valueOf(usuarioDTO.getRol().name());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("El rol especificado no existe: " + usuarioDTO.getRol());
        }

        // Validar que el subrol especificado exista (si se proporciona)
        if (usuarioDTO.getSubrol() != null) {
            try {
                Usuarios.SubRol.valueOf(usuarioDTO.getSubrol().name());
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("El subrol especificado no existe: " + usuarioDTO.getSubrol());
            }
        }

        Usuarios usuario = new Usuarios();
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setDocumento(usuarioDTO.getDocumento());
        usuario.setRol(usuarioDTO.getRol());
        usuario.setSubrol(usuarioDTO.getSubrol());
        usuario.setCorreo(usuarioDTO.getCorreo());
        usuario.setContraseña(usuarioDTO.getContraseña());;

        return usuariosRepositorio.save(usuario);
    }


    public Usuarios actualizar(Integer id, UsuarioDTO usuario) {
        //validar que solo haya un administrador y un jefe
        if (usuario.getRol() == Usuarios.Rol.JEFE || usuario.getRol() == Usuarios.Rol.ADMINISTRADOR) {
            boolean rolExistente = usuariosRepositorio.existsByRol(usuario.getRol());
            if (rolExistente) {
                throw new ResponseStatusException(HttpStatus. NOT_FOUND, "Ya existe un usuario con el rol: " + usuario.getRol());
            }
        }

        // Validar que el rol especificado exista
        try {
            Usuarios.Rol.valueOf(usuario.getRol().name());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("El rol especificado no existe: " + usuario.getRol());
        }

        // Validar que el subrol especificado exista (si se proporciona)
        if (usuario.getSubrol() != null) {
            try {
                Usuarios.SubRol.valueOf(usuario.getSubrol().name());
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("El subrol especificado no existe: " + usuario.getSubrol());
            }
        }
        return usuariosRepositorio.findById(id).map(u -> {
            u.setNombre(usuario.getNombre());
            u.setRol(usuario.getRol());
            u.setSubrol(usuario.getSubrol());
            u.setCorreo(usuario.getCorreo());
            u.setContraseña(usuario.getContraseña());
            return usuariosRepositorio.save(u);
        }).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public void eliminarUsuarioPorNombre(String nombreUsuarioAEliminar, String contraseñaProporcionada) {
        // Buscar al único usuario con el rol Jefe
        Usuarios jefe = (Usuarios) usuariosRepositorio.findByRol(Usuarios.Rol.JEFE)
                .orElseThrow(() -> new RuntimeException("No se encontró ningún usuario con el rol Jefe."));

        // Validar que la contraseña proporcionada coincide con la del Jefe
        if (!jefe.getContraseña().equals(contraseñaProporcionada)) {
            throw new RuntimeException("Acceso denegado: Solo el jefe puede eliminar usuarios.");
        }

        // Buscar al usuario que se quiere eliminar
        Usuarios usuarioAEliminar = usuariosRepositorio.findByNombre(nombreUsuarioAEliminar)
                .orElseThrow(() -> new RuntimeException("No se encontró un usuario con el nombre especificado."));
        // Eliminar el usuario
        usuariosRepositorio.delete(usuarioAEliminar);
    }

    //metodo para autenticar el usuario
    public Usuarios autenticarUsuario(String nombre, String contraseña) {
        Usuarios usuario = usuariosRepositorio.findByNombre(nombre)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        // Verificar que la contraseña coincida
        if (!usuario.getContraseña().equals(contraseña)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Contraseña incorrecta");
        }

        return usuario; // Devuelve el usuario autenticado si las credenciales son correctas
    }



}
