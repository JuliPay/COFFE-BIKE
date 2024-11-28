package com.example.coffe.DTO;

import com.example.coffe.Entidades.Productos;

public class ProductoDTO {
    private int id;
    private String nombre;
    private String descripcion;
    private Integer cantidad;
    private Productos.Categoria categoria;
    private Integer precioUnitario;
    private Integer id_usuario;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Productos.Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Productos.Categoria categoria) {
        this.categoria = categoria;
    }

    public int getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(int precioUnitario) {
        this.precioUnitario = precioUnitario;
    }


    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }
}
