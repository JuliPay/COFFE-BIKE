package com.example.coffe.DTO;

import com.example.coffe.Entidades.Productos;
import io.micrometer.common.lang.NonNull;
import io.micrometer.common.lang.Nullable;
import org.antlr.v4.runtime.misc.NotNull;

import java.math.BigDecimal;

public class MateriaPrimaDTO {
    private int id;
    private String nombre;
    private String unidadMedida;

    private BigDecimal cantidadDeUnidades;

    private Integer precioUnitario;

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
