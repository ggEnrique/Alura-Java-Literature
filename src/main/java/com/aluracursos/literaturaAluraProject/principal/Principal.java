package com.aluracursos.literaturaAluraProject.principal;

import com.aluracursos.literaturaAluraProject.service.ConsumoAPI;
import com.aluracursos.literaturaAluraProject.service.ConvierteDatos;

import java.util.Scanner;

public class Principal {
    private static final String URL_BASE = "https://gutendex.com/books/";
    int input = 0;
    Scanner scanner = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos convierteDatos = new ConvierteDatos();

    public void muestraElMenu() {
//        var json = consumoAPI.obtenerDatos(URL_BASE);
//        System.out.println(json);
//        var datos = convierteDatos.obtenerDatos(json, Datos.class);
//        System.out.println(datos);
        while (true) {
            System.out.println("""
                    Choose the an option with 1 - 7:
                    1 - buscar libro por título
                    2 - listar libros registrados
                    3 - listar autores registrados
                    4 - listar autores vivos en un determinado año
                    5 - listar libros por idiomas
                    0 - salir
                    """);
            input = scanner.nextInt();
            switch (input) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 0:
                    System.out.println("Saliendo...");
                    scanner.close();
                    break;
                default:
                    System.out.println("Not a valid choice, try again.");
            }

        }


    }
}
