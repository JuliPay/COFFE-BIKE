package com.example.coffe.DTO;

public class EliminarUsuarioDTO {
    private String  nombreUsuarioAEliminar;
    private String contraseñaJefe;

    // Getters y setters
    public String getNombreUsuarioAEliminar() {
        return nombreUsuarioAEliminar;
    }

    public void setNombreUsuarioAEliminar(String nombreUsuarioAEliminar) {
        this.nombreUsuarioAEliminar = nombreUsuarioAEliminar;
    }

    public String getContraseñaJefe() {
        return contraseñaJefe;
    }

    public void setContraseñaJefe(String contraseñaJefe) {
        this.contraseñaJefe = contraseñaJefe;
    }
}
