package com.example.coffe.Entidades;

import jakarta.persistence.*;


import java.time.LocalDateTime;

@Entity
@Table(name = "MovimientosMateriaPrima")
public class MovimientosMateriasPrima {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "materiaprima_id", nullable = false)
    private MateriaPrima materiaPrima;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoMovimiento tipoMovimiento;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(nullable = false)
    private LocalDateTime fechaEntrada;

    @Column
    private LocalDateTime fechaVencimiento;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuarios usuario;

    public enum TipoMovimiento {
        ENTRADA, SALIDA
    }

}
