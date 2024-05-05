package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Administrador {

    static Scanner sc = new Scanner(System.in);

    private Biblioteca biblioteca;
    private final List<Libro> libros = new ArrayList<>();
    public Administrador(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    public Administrador(){

    }

    public void agregarLibro(Biblioteca biblioteca) {

        System.out.println("¿Cuántos libros desea agregar?");
        int cantidadLibros = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < cantidadLibros; i++) {
            System.out.println("Ingrese el título del libro:");
            String titulo = sc.nextLine();
            System.out.println("Ingrese el autor del libro:");
            String autor = sc.nextLine();
            System.out.println("Ingrese el género del libro:");
            String genero = sc.nextLine();
            System.out.println("Ingrese la cantidad disponible del libro:");
            int cantidadDisponible = sc.nextInt();
            System.out.println("Ingrese la fecha de publicación del libro (YY-MM-DD):");
            String fechaPublicacion = sc.next();

            Libro nuevoLibro = new Libro(titulo, autor, genero, cantidadDisponible, fechaPublicacion, Collections.singletonList(0), Collections.singletonList(""));

            biblioteca.addLibros(nuevoLibro);

            System.out.println("Libro agregado exitosamente.");
            sc.nextLine();
        }
    }


    public void eliminarLibro(Biblioteca biblioteca) {
        System.out.println("Lista de libros disponibles:");
        List<Libro> libros = biblioteca.getLibros();
        for (int i = 0; i < libros.size(); i++) {
            Libro libro = libros.get(i);
            System.out.println((i + 1) + ". " + libro.getTitulo());
        }
        System.out.print("Seleccione el número del libro que desea eliminar: ");
        int opcion = sc.nextInt();
        sc.nextLine();

        if (opcion < 1 || opcion > libros.size()) {
            System.out.println("Opción inválida.");
            return;
        }
        int indiceLibro = opcion - 1;

        libros.get(indiceLibro);
        libros.remove(indiceLibro);

        System.out.println("=====libro eliminado=====");
    }



    public void modificarInformacionDelLibro() {

    }

    public void administrarUsuarios() {
    }

    public void sancionPorDevolucion() {
    }

    public void sancionComentario() {
    }

    public void eliminarUsuario() {
    }


}


