package com.example.coffe.DTO;

import com.example.coffe.Entidades.Usuarios;

public class UsuarioDTO {
    private String nombre;
    private int Documento;
    private Usuarios.Rol rol;
    private Usuarios.SubRol subrol;
    private String correo;
    private String contraseña;

    public int getDocumento() {
        return Documento;
    }

    public void setDocumento(int documento) {
        Documento = documento;
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Usuarios.Rol getRol() {
        return rol;
    }

    public void setRol(Usuarios.Rol rol) {
        this.rol = rol;
    }

    public Usuarios.SubRol getSubrol() {
        return subrol;
    }

    public void setSubrol(Usuarios.SubRol subrol) {
        this.subrol = subrol;
    }
}
