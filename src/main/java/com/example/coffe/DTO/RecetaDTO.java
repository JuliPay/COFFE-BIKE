package com.example.coffe.DTO;



public class RecetaDTO {
    private Integer id;
    private Integer producto;
    private Integer materiaPrima;
    private Integer cantidadNecesaria;
    private String procesoReceta;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProducto() {
        return producto;
    }

    public void setProducto(Integer producto) {
        this.producto = producto;
    }

    public Integer getMateriaPrima() {
        return materiaPrima;
    }

    public void setMateriaPrima(Integer materiaPrima) {
        this.materiaPrima = materiaPrima;
    }

    public Integer getCantidadNecesaria() {
        return cantidadNecesaria;
    }

    public void setCantidadNecesaria(Integer cantidadNecesaria) {
        this.cantidadNecesaria = cantidadNecesaria;
    }

    public String getProcesoReceta() {
        return procesoReceta;
    }

    public void setProcesoReceta(String procesoReceta) {
        this.procesoReceta = procesoReceta;
    }
}
