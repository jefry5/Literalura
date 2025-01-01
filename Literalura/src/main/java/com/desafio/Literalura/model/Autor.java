package com.desafio.Literalura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Integer anio_nacimiento;
    private Integer anio_fallecimiento;
    @ManyToMany(mappedBy = "autores")
    private List<Libro> libro;

    public Autor(){}

    public Autor(DatosAutor datos){
        this.nombre = datos.nombre();
        this.anio_fallecimiento = datos.anio_fallecimiento();
        this.anio_nacimiento = datos.anio_nacimiento();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Libro> getLibro() {
        return libro;
    }

    public void setLibro(List<Libro> libro) {
        this.libro = libro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getAnio_nacimiento() {
        return anio_nacimiento;
    }

    public void setAnio_nacimiento(Integer anio_nacimiento) {
        this.anio_nacimiento = anio_nacimiento;
    }

    public Integer getAnio_fallecimiento() {
        return anio_fallecimiento;
    }

    public void setAnio_fallecimiento(Integer anio_fallecimiento) {
        this.anio_fallecimiento = anio_fallecimiento;
    }

    @Override
    public String toString() {
        return  "Nombre: " + nombre + '\'' +
                ", Año nacimiento: " + anio_nacimiento +
                ", Año fallecimiento: " + anio_fallecimiento;
    }
}
