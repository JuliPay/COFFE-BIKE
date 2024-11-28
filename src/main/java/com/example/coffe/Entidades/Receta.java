package com.example.coffe.Entidades;

import jakarta.persistence.*;


@Entity
@Table(name = "Recetas")
public class Receta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Productos producto;

    @ManyToOne
    @JoinColumn(name = "materiaprima_id", nullable = false)
    private MateriaPrima materiaPrima;

    @Column(nullable = false)
    private Integer cantidadNecesaria;

    @Column(nullable = false, length = 250)
    private String procesoReceta;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }

    public MateriaPrima getMateriaPrima() {
        return materiaPrima;
    }

    public void setMateriaPrima(MateriaPrima materiaPrima) {
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
