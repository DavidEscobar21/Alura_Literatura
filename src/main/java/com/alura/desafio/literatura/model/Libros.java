package com.alura.desafio.literatura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "libros")
public class Libros {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String titulo;
    private List<String> idiomas;
    private Integer totalDescargas;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL) // Asegura que el autor se guarde automáticamente
    @JoinColumn(name = "autor_id")
    //@Transient
    private Autores autores;

    public Libros() {}
    public Libros(DatosLibros datosLibros) {
        this.titulo = datosLibros.titulo();
        this.idiomas = datosLibros.idiomas();
        this.totalDescargas = datosLibros.totalDescargas();
       // this.autores = datosLibros.autores().stream().map(a -> new Autores(a)).collect(Collectors.toList());
        if (datosLibros.autores()!= null && !datosLibros.autores().isEmpty()) {
            this.autores = new Autores(datosLibros.autores().get(0)); // Toma el primer autor de la lista
        } else {
            this.autores = null; // o maneja el caso de que no haya autor
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public Integer getTotalDescargas() {
        return totalDescargas;
    }

    public void setTotalDescargas(Integer totalDescargas) {
        this.totalDescargas = totalDescargas;
    }

    public Autores getAutores() {
        return autores;
    }

    public void setAutores(Autores autores) {
        this.autores = autores;
    }

    @Override
    public String toString() {
        return "\n---------------Libro---------------"+
                "\nid=" + id +
                "\ntitulo='" + titulo + '\'' +
                "\nidiomas=" + idiomas +
                "\ntotalDescargas=" + totalDescargas +
                "\nautores=" + autores.getNombre()+
                "\n-----------------------------------\n";
    }
}
