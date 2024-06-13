package com.aluracursos.literaturaAluraProject.principal;

import com.aluracursos.literaturaAluraProject.model.Datos;
import com.aluracursos.literaturaAluraProject.model.DatosLibros;
import com.aluracursos.literaturaAluraProject.service.ConsumoAPI;
import com.aluracursos.literaturaAluraProject.service.ConvierteDatos;

import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private static final String URL_BASE = "https://gutendex.com/books/";
    int input = -1;
    Scanner scanner = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos convierteDatos = new ConvierteDatos();

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
    }

    private void listarAutoresPorFecha() {
    }

    private void listarAutoresRegistrados() {
    }

    private void listarLibrosRegistrados() {
    }

    private void buscarLibroPorTitulo() {
        System.out.println("Enter the titulo del libro que quieras search for");
        var tituloLibro = scanner.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + tituloLibro.replace(" ", "+"));
        var datos = convierteDatos.obtenerDatos(json, Datos.class);
        Optional<DatosLibros> librosBuscado = datos.resultados().stream()
                .filter(l->l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
                .findFirst();
        if(librosBuscado.isPresent()){
            System.out.println("Libro Econtrado");
            System.out.println(librosBuscado.get());
        }else {
            System.out.println("Book not found");
        }

    }
}
