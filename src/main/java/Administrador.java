package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Administrador {

    static Scanner sc = new Scanner(System.in);

    private Biblioteca biblioteca;
    private final List<Libro> libros = new ArrayList<>();
    public Administrador(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    public void menuAdmin(){
        //....

    }


    public void eliminarLibro(Biblioteca biblioteca, String tituloLibro) {
        boolean encontrado = false;
        for (int i = 0; i < biblioteca.getLibros().size(); i++) {
            Libro libro = (Libro) biblioteca.getLibros().get(i);
            if (libro.getTitulo().equalsIgnoreCase(tituloLibro)) {
                biblioteca.getLibros().remove(i);
                System.out.println("Libro eliminado exitosamente.");
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("El libro no fue encontrado en la biblioteca.");
        }
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

    public void menuAdiministrador(){

    }
}

