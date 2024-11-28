package com.example.coffe.Entidades;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "MateriaPrima")
public class MateriaPrima {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false,  unique = true, length = 100)
    private String nombre;

    @Column(nullable = false, length = 50)
    private String unidadMedida;

    @Column(nullable = false )
    private Integer stockActual;

    @Column(nullable = false)
    private Integer precioUnitario;

    @OneToMany(mappedBy = "materiaPrima", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Receta> recetas;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public Integer getStockActual() {
        return stockActual;
    }

    public void setStockActual(Integer stockActual) {
        this.stockActual = stockActual;
    }

    public Integer getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Integer precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
}
