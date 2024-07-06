package com.alura.desafio.literatura.principal;

import com.alura.desafio.literatura.model.Autores;
import com.alura.desafio.literatura.model.DatosLibros;
import com.alura.desafio.literatura.model.Libros;
import com.alura.desafio.literatura.model.ListaDeLibros;
import com.alura.desafio.literatura.repository.AutoresRepository;
import com.alura.desafio.literatura.repository.LibrosRepository;
import com.alura.desafio.literatura.service.ConsumoAPI;
import com.alura.desafio.literatura.service.ConvierteDatos;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import java.util.List;
import java.util.Scanner;

public class Principal {

    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "http://gutendex.com/books/";
    private ConvierteDatos conversor = new ConvierteDatos();
    private LibrosRepository repositorio;
    private AutoresRepository autoresRepository;

    public Principal(LibrosRepository repository,AutoresRepository autoresRepository) {
        this.repositorio=repository;
        this.autoresRepository=autoresRepository;
    }


    public void muestraElMenu() {

        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Buscar Libro Por Titulo
                    2 - Listar Libros Registrados
                    3 - Listar Autores Registrados
                    4 - Listar Autores Vivos En Un Determinado Año
                    5 - Listar Libros Por Idioma
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroEnLaWeb();
                    break;
                case 2:
                    listarLibros();
                    break;
                case 3:
                    listarAutores();
                    break;
                case 4:
                    listarAutoresVivos();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }


    private Libros getDatosLibro(){
        System.out.println("Ingrese el nombre del libro: ");
        var nombreLibro = teclado.nextLine().toLowerCase();
        var json = consumoApi.obtenerDatos(URL_BASE +"?search="+ nombreLibro.replace(" ", "%20"));

        ListaDeLibros datos = conversor.obtenerDatos(json, ListaDeLibros.class);

        if (datos != null && datos.listaDeLibros() != null && !datos.listaDeLibros().isEmpty()) {
            DatosLibros primerLibro = datos.listaDeLibros().get(0); // Obtener el primer libro de la lista
            return new Libros(primerLibro);
        } else {
            System.out.println("No se encontraron resultados.");
            return null;
        }
    }

    private void buscarLibroEnLaWeb() {
        Libros libro = getDatosLibro();

        if (libro == null){
            System.out.println("Libro no encontrado. el valor es null");
            return;
        }

        try{
            boolean libroExists = repositorio.existsByTitulo(libro.getTitulo());
            if (libroExists){
                System.out.println("El libro ya existe en la base de datos!");
            }else {
                repositorio.save(libro);
                System.out.println(libro);
            }
        }catch (InvalidDataAccessApiUsageException e){
            System.out.println("No se puede persistir el libro buscado!");
        }
    }

    private void listarLibros() {
        List<Libros> libros = repositorio.findAll();
        if (libros.isEmpty()) {
            System.out.println("No se encontraron Libros en la base de datos. \n");
        } else {
            libros.forEach(System.out::println);
        }
    }

    private void listarAutores() {
        List<Autores> autores = autoresRepository.findAll();
        if (autores.isEmpty()) {
            System.out.println("No se encontraron Autores en la base de datos. \n");
        } else {
            List<Libros> libros;
            for (Autores autor : autores) {
                System.out.println(autor);
                System.out.println("------------Libros-------------");
                libros = repositorio.findByIdAutor(autor.getId());
                libros.forEach(l -> System.out.println(l.getTitulo()));
                System.out.println("------------------------------");
            }
        }
    }

    private void listarLibrosPorIdioma() {
        System.out.println("Ingrese el idioma de los libros:");
        String idioma = teclado.nextLine();
        List<Libros> libros = repositorio.findPorIdioma(idioma);
        if (libros.isEmpty()) {
            System.out.println("No se encontraron Libros en la base de datos. \n");
        } else {
            libros.forEach(System.out::println);
        }
    }

    private void listarAutoresVivos() {
        System.out.println("Ingrese año de Fallecimiento:");
        int anoFallecimiento= teclado.nextInt();
        teclado.nextLine();
        List<Autores> autores = autoresRepository.findByanoFallecimientoLessThanEqual(anoFallecimiento);
        if (autores.isEmpty()) {
            System.out.println("No se encontraron Autores en la base de datos. \n");
        } else {
            List<Libros> libros;
            for (Autores autor : autores) {
                System.out.println(autor);
                System.out.println("------------Libros-------------");
                libros = repositorio.findByIdAutor(autor.getId());
                libros.forEach(l -> System.out.println(l.getTitulo()));
                System.out.println("------------------------------");
            }
        }
    }

}
