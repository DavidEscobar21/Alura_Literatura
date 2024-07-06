package com.alura.desafio.literatura.service;

import com.alura.desafio.literatura.model.DatosLibros;
import com.alura.desafio.literatura.model.Libros;
import com.alura.desafio.literatura.principal.Principal;
import com.alura.desafio.literatura.repository.LibrosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class LibrosService {

    @Autowired
    private LibrosRepository repository;


}
