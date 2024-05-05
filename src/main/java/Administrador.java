import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Administrador {

    static Scanner teclado = new Scanner(System.in);

    private Biblioteca biblioteca;

    public Administrador(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }


    public void agregarLibro(Biblioteca biblioteca) {
        try {
            String titulo = leerEntrada("Ingrese el título del libro:", teclado);
            String autor = leerEntrada("Ingrese el autor del libro:", teclado);
            String genero = leerEntrada("Ingrese el género del libro:", teclado);
            int cantidadDisponible = leerEntero("Ingrese la cantidad disponible del libro:", teclado);
            String fechaPublicacion = leerEntrada("Ingrese la fecha de publicación del libro (YY-MM-DD):", teclado);

            if (verificarDatos(titulo, autor, genero, cantidadDisponible, fechaPublicacion)) {
                Libro nuevoLibro = new Libro(titulo, autor, genero, cantidadDisponible, fechaPublicacion, Collections.singletonList(0));
                biblioteca.addLibros(nuevoLibro);
                System.out.println("Libro agregado exitosamente.");
                teclado.nextLine();
            } else {
                System.out.println("Ingrese datos validos");
            }
        } catch (Exception e) {
            System.out.println("Ingrese datos validos");
            teclado.nextLine();
            agregarLibro(biblioteca);
        }

    }

    private String leerEntrada(String mensaje, Scanner scanner) {
        System.out.println(mensaje);
        return scanner.nextLine();
    }

    private int leerEntero(String mensaje, Scanner scanner) {
        System.out.println(mensaje);
        return Integer.parseInt(scanner.nextLine());
    }

    public boolean verificarDatos(String nombre, String autor, String genero, int cantDisponible, String fechaPublicacion) {
        if (nombre.equals("") || autor.equals("") || genero.equals("") || cantDisponible == 0 || fechaPublicacion.equals("")) {
            System.out.println("Ingrese datos validos");
            return false;
        } else {
            return true;
        }
    }

    public void eliminarLibro(Biblioteca biblioteca) {
        System.out.println("Lista de libros disponibles:");
        List<Libro> libros = biblioteca.getLibros();
        for (int i = 0; i < libros.size(); i++) {
            Libro libro = libros.get(i);
            System.out.println((i + 1) + ". " + libro.getTitulo());
        }

        int opcion = leerEntero("Seleccione el número del libro que desea eliminar: ", teclado);
        teclado.nextLine();

        if (opcion < 1 || opcion > libros.size()) {
            System.out.println("Opción inválida.");
            return;
        }
        int indiceLibro = opcion - 1;

        libros.get(indiceLibro);
        libros.remove(indiceLibro);

        System.out.println("=====Libro eliminado=====");
    }


    public void modificarInformacionDelLibro(Biblioteca biblioteca) {
        String titulo = leerEntrada("Ingrese Titulo del libro a modificar", teclado);
        for (Libro libro : biblioteca.getLibros()) {

            if (titulo.equals(libro.getTitulo())) {
                libro.setTitulo(leerEntrada("Ingrese Titulo nuevo", teclado));
                libro.setAutor(leerEntrada("Ingrese Autor nuevo", teclado));
                libro.setCategoria(leerEntrada("Ingrese Genero nuevo", teclado));
                libro.setEjemplaresDisponibles(leerEntero("Ingrese Cantidad disponible nuevo", teclado));
                libro.setDate(leerEntrada("Ingrese fecha de publicacion nuevo", teclado));
            }
        }


    }

    public void administrarUsuarios(Login usuario) {
        int opcion;
        do {

            System.out.println("╔══════════════════════════╗");
            System.out.println("║ [1] Ver lista usuario    ║");
            System.out.println("║ [2] Eliminar Usuario     ║");
            System.out.println("║ [3] Salir                ║");
            System.out.println("╚══════════════════════════╝");
            opcion = leerEntero("Ingrese opcion:", teclado);

            menuManejoAdmin(opcion, usuario);
        } while (opcion != 3);
    }

    public void verListaUsuario(Login usuarios) {
        for (Usuario usuario : usuarios.getUsuarios()) {
            System.out.println(usuario);
        }
    }

    public void eliminarUsuario(Login usuarios) {
        String buscarNombre = leerEntrada("Ingrese nombre del usuario", teclado);
        usuarios.getUsuarios().removeIf(usuario -> usuario.getNombre().equals(buscarNombre));
    }

    public void menuManejoAdmin(int opcion, Login usuarios) {
        switch (opcion) {
            case 1 -> verListaUsuario(usuarios);
            case 2 -> eliminarUsuario(usuarios);
        }

    }

}

