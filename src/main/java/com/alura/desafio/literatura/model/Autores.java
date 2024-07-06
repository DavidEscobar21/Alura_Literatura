package com.alura.desafio.literatura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autores")
public class Autores{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre;
    private Integer anoNacimiento;
    private Integer anoFallecimiento;

    @OneToMany(mappedBy = "autores", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //@Transient
    private List<Libros> libro;


    public Autores() {}

    public Autores(DatosAutores datosAutores) {
        this.nombre = datosAutores.nombre();
        this.anoNacimiento = datosAutores.anoNacimiento();
        this.anoFallecimiento = datosAutores.anoFallecimiento();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getAnoNacimiento() {
        return anoNacimiento;
    }

    public void setAnoNacimiento(Integer anoNacimiento) {
        this.anoNacimiento = anoNacimiento;
    }

    public Integer getAnoFallecimiento() {
        return anoFallecimiento;
    }

    public void setAnoFallecimiento(Integer anoFallecimiento) {
        this.anoFallecimiento = anoFallecimiento;
    }

    public List<Libros> getLibro() {
        return libro;
    }

    public void setLibro(List<Libros> libro) {
        this.libro = libro;
    }

    @Override
    public String toString() {
        return "\n---------------Autor---------------"+
                "\nid=" + id +
                "\nnombre='" + nombre + '\'' +
                "\nanoNacimiento=" + anoNacimiento +
                "\nanoFallecimiento=" + anoFallecimiento+
                "\n-----------------------------------\n";
    }
}
