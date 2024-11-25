package com.example.coffe.Entidades;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "MateriaPrima")
public class MateriaPrima {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 50)
    private String unidadMedida;

    @Column(nullable = false )
    private Integer stockActual;

    @Column(nullable = false)
    private Integer precioUnitario;

    @OneToMany(mappedBy = "materiaPrima", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Receta> recetas;

    @OneToMany(mappedBy = "materiaPrima", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MovimientosMateriasPrima> movimientos;
}
