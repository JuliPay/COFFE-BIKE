package com.example.coffe.Entidades;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "Recetas")
public class Receta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Productos producto;

    @Column(nullable = false, length = 250)
    private String procesoReceta;

    @OneToMany(mappedBy = "receta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MateriaPrimaRecetas> ingredientes = new ArrayList<>();

    public Receta(){
    }

    public Receta(Productos producto, String procesoReceta) {
        this.producto = producto;
        this.procesoReceta = procesoReceta;
    }



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

    public String getProcesoReceta() {
        return procesoReceta;
    }

    public void setProcesoReceta(String procesoReceta) {
        this.procesoReceta = procesoReceta;
    }


    public MateriaPrimaRecetas[] getIngredientes() {

        return new MateriaPrimaRecetas[0];
    }


}
