package com.example.coffe.Entidades;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "Materia_Prima")
public class MateriaPrima {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", nullable = false,  unique = true, length = 100)
    private String nombre;

    @Column(name = "unidad_medida",nullable = false, length = 50)
    private String unidadMedida;


    @Column(name = "cantidad_de_unidades",nullable = false, precision = 10, scale = 2)
    private BigDecimal cantidadDeUnidades;


    @Column(name = "precio_unitario",nullable = false)
    private Integer precioUnitario;


    public MateriaPrima(){}

    public MateriaPrima(String nombre, String unidadMedida, BigDecimal cantidadDeUnidades, Integer precioUnitario) {
        this.nombre = nombre;
        this.unidadMedida = unidadMedida;
        this.cantidadDeUnidades = cantidadDeUnidades;
        this.precioUnitario = precioUnitario;

    }



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

    public BigDecimal getCantidadDeUnidades() {
        return cantidadDeUnidades;
    }

    public void setCantidadDeUnidades(BigDecimal cantidadDeUnidades) {
        this.cantidadDeUnidades = cantidadDeUnidades;
    }

    public Integer getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Integer precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
}
