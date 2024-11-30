package com.example.coffe.Entidades;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "Materia_Prima_Recetas")
public class MateriaPrimaRecetas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "receta_id", nullable = false)
    private Receta receta;

    @ManyToOne
    @JoinColumn(name = "materiaprima_id", nullable = false)
    private MateriaPrima materiaPrima;

    @Column(nullable = false)
    private BigDecimal cantidadIngrediente;

    public MateriaPrimaRecetas(Receta receta, MateriaPrima materiaPrima, BigDecimal cantidadIngrediente) {
        this.receta = receta;
        this.materiaPrima = materiaPrima;
        this.cantidadIngrediente = cantidadIngrediente;
    }

    public Receta getReceta() {
        return receta;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }

    public MateriaPrima getMateriaPrima() {
        return materiaPrima;
    }

    public void setMateriaPrima(MateriaPrima materiaPrima) {
        this.materiaPrima = materiaPrima;
    }

    public BigDecimal getCantidadIngrediente() {
        return cantidadIngrediente;
    }

    public void setCantidadIngrediente(BigDecimal cantidadIngrediente) {
        this.cantidadIngrediente = cantidadIngrediente;
    }

    // Getters, setters, y constructores vacíos
}
