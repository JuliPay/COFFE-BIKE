package com.example.coffe.Entidades;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;


@Entity
@Table(name = "Productos")
public class Productos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 100)
    private String nombre;

    @Column(nullable = false, unique = true, length = 200)
    private String descripcion;

    @Column(nullable = false)
    private Integer  cantidad;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Categoria categoria;

    @Column(nullable = false)
    private BigDecimal precioUnitario;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuarios id_usuario;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Receta> recetas;


    public enum Categoria {
        PLATO, BEBIDA
    }

    public Productos() {}

    public Productos(String nombre, String descripcion, Integer cantidad, Categoria categoria, BigDecimal  precioUnitario) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.categoria = categoria;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Usuarios getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Usuarios id_usuario) {
        this.id_usuario = id_usuario;
    }


}
