package com.aluracursos.literaturaAluraProject.principal;

import com.aluracursos.literaturaAluraProject.model.Datos;
import com.aluracursos.literaturaAluraProject.model.DatosAutor;
import com.aluracursos.literaturaAluraProject.model.DatosLibros;
import com.aluracursos.literaturaAluraProject.repository.DatosAutorRepository;
import com.aluracursos.literaturaAluraProject.repository.DatosLibrosRepository;
import com.aluracursos.literaturaAluraProject.service.ConsumoAPI;
import com.aluracursos.literaturaAluraProject.service.ConvierteDatos;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private static final String URL_BASE = "https://gutendex.com/books/";
    int input = -1;
    Scanner scanner = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos convierteDatos = new ConvierteDatos();
    private DatosLibrosRepository librosRepository;
    private DatosAutorRepository autorRepository;

    public Principal(DatosLibrosRepository librosRepository, DatosAutorRepository autorRepository) {
        this.librosRepository = librosRepository;
        this.autorRepository = autorRepository;
    }

    public void muestraElMenu() {
//        var json = consumoAPI.obtenerDatos(URL_BASE);
//        System.out.println(json);
//        var datos = convierteDatos.obtenerDatos(json, Datos.class);
//        System.out.println(datos);
        while (input != 0) {
            System.out.println("""
                    **THIS PROGRAM IS IN SPANGLISH**
                    Choose the an option with 1 - 5 or 0 para salir/exit:
                    1 - buscar libro por título
                    2 - listar libros registrados
                    3 - listar autores registrados
                    4 - listar autores vivos en un determinado año
                    5 - listar libros por idiomas
                    0 - salir
                    """);
            input = scanner.nextInt();
            scanner.nextLine();
            switch (input) {
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresPorFecha();
                    break;
                case 5:
                    listarLibrosByIdioma();
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    System.out.println("Closing program...");
                    scanner.close();
                    break;
                default:
                    System.out.println("Not a valid choice, try again...");
            }

        }


    }

    private void listarLibrosByIdioma() {
        System.out.println("Ingrese el idioma para buscar los libros:");
        System.out.println("es - español");
        System.out.println("en - inglés");
        System.out.println("fr - francés");
        System.out.println("pt - portugués");

        String idioma = scanner.nextLine();

        var libros = librosRepository.findByIdiomasContains(idioma);

        if (libros.isEmpty()) {
            System.out.println("No se encontraron libros en el idioma especificado.");
        } else {
            libros.forEach(libro -> {
                System.out.println("----- LIBRO -----");
                System.out.println("Título: " + libro.getTitulo());
                System.out.println("Autor: ");
                libro.getAutor().forEach(autor -> System.out.println("  - " + autor.getNombre()));
                System.out.println("Idioma: " + idioma);
                System.out.println("Número de descargas: " + libro.getNumeroDeDescargas());
                System.out.println("-----------------");
            });
        }
    }

    private void listarAutoresPorFecha() {
        System.out.println("Enter the year you want to check for living authors:");
        String year = scanner.nextLine();

        List<DatosAutor> autores = autorRepository.findAuthorsAliveInYear(year);
        if (autores.isEmpty()) {
            System.out.println("No authors were alive in the year " + year);
        } else {
            System.out.println("Authors alive in the year " + year + ":");
            for (DatosAutor autor : autores) {
                System.out.println("ID: " + autor.getId());
                System.out.println("Nombre: " + autor.getNombre());
                System.out.println("Fecha de Nacimiento: " + autor.getFechaDeNacimiento());
                System.out.println("Fecha de Fallecimiento: " + (autor.getFechaDeFallecimiento() != null ? autor.getFechaDeFallecimiento() : "Still alive"));
                System.out.println("Libros escritos:");
                DatosLibros libro = autor.getLibro();
                if (libro != null) {
                    System.out.println("  - " + libro.getTitulo());
                } else {
                    System.out.println("  - No books found for this author.");
                }
                System.out.println("---------------------------");
            }
        }

    }

    private void listarAutoresRegistrados() {
        List<DatosAutor> autores = autorRepository.findAll();
        if (autores.isEmpty()) {
            System.out.println("No hay autores registrados.");
        } else {
            for (DatosAutor autor : autores) {
                System.out.println("ID: " + autor.getId());
                System.out.println("Nombre: " + autor.getNombre());
                System.out.println("Fecha de Nacimiento: " + autor.getFechaDeNacimiento());
                System.out.println("Fecha de Fallecimiento: " + autor.getFechaDeFallecimiento());
                System.out.println("Libros escritos:");
                DatosLibros libro = autor.getLibro();
                System.out.println("  - " + libro.getTitulo());
                System.out.println("---------------------------");
            }
        }
    }

    private void listarLibrosRegistrados() {
        // Retrieve all saved books from the repository
        List<DatosLibros> libros = librosRepository.findAll();

        // Check if there are any books saved
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados.");
        } else {
            // Print out each book
            for (DatosLibros libro : libros) {
                System.out.println("ID: " + libro.getId());
                System.out.println("Título: " + libro.getTitulo());
                System.out.println("Número de descargas: " + libro.getNumeroDeDescargas());

                // Print authors
                System.out.println("Autores:");
                libro.getAutor().forEach(autor -> System.out.println("  - " + autor.getNombre() + " (Nacimiento: " + autor.getFechaDeNacimiento() + ", Muerte: " + autor.getFechaDeFallecimiento() + ")"));

                // Print languages
                System.out.println("Idiomas: " + libro.getIdiomas());
                System.out.println("---------------------------");
            }
        }
    }

    private void buscarLibroPorTitulo() {
        System.out.println("Enter the titulo del libro que quieras search for");
        var tituloLibro = scanner.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + tituloLibro.replace(" ", "+"));
        var datos = convierteDatos.obtenerDatos(json, Datos.class);
        Optional<DatosLibros> librosBuscado = datos.resultados().stream()
                .filter(l -> l.getTitulo().toUpperCase().contains(tituloLibro.toUpperCase()))
                .findFirst();
        if (librosBuscado.isPresent()) {
            DatosLibros libro = librosBuscado.get();
            Optional<DatosLibros> existingLibro = librosRepository.findByTitulo(libro.getTitulo());
            if (existingLibro.isPresent()) {
                System.out.println("El libro ya está registrado en la base de datos.");
            } else {
                System.out.println("--------Libro Econtrado--------");
                System.out.println("Título: " + libro.getTitulo());
                System.out.println("Authors:");
                libro.getAutor().forEach(autor -> System.out.println("  - " + autor.getNombre() + " (Nacimiento: " + autor.getFechaDeNacimiento() + ", Muerte: " + autor.getFechaDeFallecimiento() + ")"));
                System.out.println("Idiomas: " + libro.getIdiomas());
                System.out.println("Número de descargas: " + libro.getNumeroDeDescargas());
                System.out.println("------------------------");

                // Save the found book to the repository
                librosRepository.save(libro);
                System.out.println("Libro guardado en la base de datos.");
            }


        } else {
            System.out.println("Book not found");
        }

    }
}
