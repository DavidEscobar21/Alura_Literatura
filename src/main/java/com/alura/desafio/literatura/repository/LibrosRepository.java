package com.alura.desafio.literatura.repository;

import com.alura.desafio.literatura.model.Libros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LibrosRepository extends JpaRepository<Libros, Long> {


    boolean existsByTitulo(String titulo);

    @Query(value = "SELECT * FROM libroS WHERE :idioma = ANY(idiomas)",nativeQuery = true)
    List<Libros> findPorIdioma(String idioma);


    @Query(value = "SELECT * FROM libros WHERE autor_id = :id",nativeQuery = true)
    List<Libros> findByIdAutor(long id);
}
