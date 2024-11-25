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

}
