package com.example.coffe.DTO;

public class EliminarProductoDTO {
    private Integer idproductoaEliminar;
    private String contraseñaJefe;

    public int getIdproductoaEliminar() {
        return idproductoaEliminar;
    }

    public void setIdproductoaEliminar(int idproductoaEliminar) {
        this.idproductoaEliminar = idproductoaEliminar;
    }

    public String getContraseñaJefe() {
        return contraseñaJefe;
    }

    public void setContraseñaJefe(String contraseñaJefe) {
        this.contraseñaJefe = contraseñaJefe;
    }
}
