package com.example.coffe.Entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "Usuarios")
public class Usuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true,length = 100)
    private String nombre;

    @Column(nullable = false, unique = true )
    private Integer documento;

    @Column(nullable = false, unique = true, length = 100)
    private String correo;

    @Column(nullable = false, unique = true )
    private String contraseña;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rol rol;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private SubRol Subrol;


    public enum Rol {
        JEFE, ADMINISTRADOR, EMPLEADO
    }

    public enum SubRol {
        COSINERO, MESERO
    }

    @Column(nullable = true, length = 255)
    private String rutaImagen; // Ruta o URL de la imagen del usuario

    public Integer getDocumento() {
        return documento;
    }

    public void setDocumento(Integer documento) {
        this.documento = documento;
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

    public SubRol getSubrol() {
        return Subrol;
    }

    public void setSubrol(SubRol subrol) {
        Subrol = subrol;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }
}
