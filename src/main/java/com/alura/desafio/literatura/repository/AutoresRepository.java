package com.alura.desafio.literatura.repository;

import com.alura.desafio.literatura.model.Autores;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AutoresRepository extends JpaRepository<Autores, Long> {
    List<Autores> findByanoFallecimientoLessThanEqual(int anoFallecimiento);
}
