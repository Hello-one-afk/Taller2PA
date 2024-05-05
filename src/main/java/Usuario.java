package org.example;

import java.util.*;

public class Usuario {
    private String nombre;
    private String tipo; // Estudiante, Profesor, Personal de la Biblioteca
    private List<Libro> historialPrestamos;
    private List<Libro> librosReservados;
    private List<Integer> calificaciones;

    private String contraseña;

    Scanner teclado = new Scanner(System.in);
    public Usuario(String nombre, String tipo, String contraseña) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.contraseña= contraseña;
        this.historialPrestamos = new ArrayList<>();
        this.librosReservados = new ArrayList<>();
        this.calificaciones = new ArrayList<>();
    }

    public void agregarPrestamo(Libro libro) {
        historialPrestamos.add(libro);
    }

    public void realizarReserva(Libro libro) {
        librosReservados.add(libro);
    }

    public void agregarCalificacion(int calificacion) {
        calificaciones.add(calificacion);
    }


    public Libro buscarLibroPorTitulo(Biblioteca biblioteca){
        try {
            System.out.println("Ingrese el nombre del título que desea buscar: ");
            String tituloLibro = teclado.nextLine();
            for (Libro libro: biblioteca.getLibros()) {
                if (libro.getTitulo().equals(tituloLibro)) {
                    System.out.println(libro);
                    return libro;
                }
            }
        } catch (Exception e) {
            System.out.println("Ocurrió un error al buscar el libro por título: " + e.getMessage());
        }
        return null;
    }

    public void buscarLibroPorAutor(Biblioteca biblioteca){
        try {
            System.out.println("Ingrese el nombre del autor que desea buscar: ");
            String nombreAutor = teclado.nextLine();
            boolean encontrado = false;
            for (Libro libro : biblioteca.getLibros()) {
                if (libro.getAutor().equals(nombreAutor)) {
                    System.out.println(libro);
                    encontrado = true;
                }
            }
            if (!encontrado){
                System.out.println("No se encontraron libros por este Autor");
            }
        }catch (Exception e){
            System.out.println("Ocurrió un error al buscar libros: " + e.getMessage());
        }
    }
    public void buscarLibroPorCategoria(Biblioteca biblioteca){
        try {
            System.out.println("Ingrese el nombre por categoria que desea buscar: ");
            String nombreLibroCategoria = teclado.nextLine();
            boolean encontrado = false;
            for (Libro libro : biblioteca.getLibros()) {
                if (libro.getCategoria().equals(nombreLibroCategoria)) {
                    System.out.println(libro);
                    encontrado = true;
                }
            }
            if (!encontrado) {
                System.out.println("No se encontraron libros con esta categoria");
            }
        }catch (Exception e){
            System.out.println("Ocurrió un error al buscar libros: " + e.getMessage());
        }
    }

    public void buscarLibroPorMejorValoracion(Biblioteca biblioteca) {
        Libro mejorLibro = null;
        int mejorValoracion = Integer.MIN_VALUE;

        for (Libro libro : biblioteca.getLibros()) {
            int valoracionActual = libro.obtenerMejorValoracion();
            if (valoracionActual > mejorValoracion) {
                mejorLibro = libro;
                mejorValoracion = valoracionActual;
            }
        }
        if (mejorLibro != null) {
            System.out.println("El libro con la mejor valoración es: ");
            System.out.println(mejorLibro.toString());
        } else {
            System.out.println("No existen.");
        }
    }


    public void buscarLibroPorPeorValoracion(Biblioteca biblioteca){
        Libro peorLibro = null;
        int peorValoracion = Integer.MAX_VALUE;

        for (Libro libro : biblioteca.getLibros()) {
            int valoracionActual = libro.obtenerPeorValoracion();
            if (valoracionActual < peorValoracion) {
                peorLibro = libro;
                peorValoracion = valoracionActual;
            }
        }
        if (peorLibro != null) {
            System.out.println("El libro con la peor valoración es: ");
            System.out.println(peorLibro.toString());
        } else {
            System.out.println("No existen.");
        }
    }

    public void buscarLibroPorValoracion(Biblioteca biblioteca){
        try {
            System.out.println("Ingrese valoración del libro: (rango 1-10)");
            double valoracion = teclado.nextDouble();
            boolean encontrado = false;
            for (Libro libro: biblioteca.getLibros()) {
                if (libro.getCalificaciones().contains((int) valoracion)) {
                    System.out.println(libro);
                    encontrado = true;
                }
            }
            if (!encontrado) {
                System.out.println("No se encontraron libros con esta calificacion");
            }
        } catch (Exception e) {
            System.out.println("Ocurrió un error al buscar libros: " + e.getMessage());
        }
    }


    public void buscarLibrosPorFecha(Biblioteca biblioteca) {
        try {
            System.out.println("Ingrese la fecha del libro: (YY-MM-DD)");
            String date = teclado.nextLine();
            boolean encontrado = false;
            for (Libro libro : biblioteca.getLibros()) {
                if (libro.getDate().equals(date)) {
                    System.out.println(libro);
                    encontrado = true;
                }
            }
            if (!encontrado) {
                System.out.println("No se encontraron libros con la fecha indicada");
            }
        } catch (Exception e) {
            System.out.println("Error al buscar libros por fecha: " + e.getMessage());
        }
    }




    public void buscarLibroPorMasLLevado(){
        //no se
    }



    public void solicitarPrestamoLibro(Biblioteca biblioteca){
        Libro libro = buscarLibroPorTitulo(biblioteca);

        if (libro.getEjemplaresDisponibles()>0){
            librosReservados.add(libro);
            libro.setEjemplaresDisponibles(libro.getEjemplaresDisponibles()-1);
            historialPrestamos.add(libro);
            System.out.println("=========Prestamo solicitado con exito==========");
        }else {
            System.out.println("Libro no disponible ["+libro+"]");
        }

    }

    public void verDisponibilidadLibro(Biblioteca biblioteca){
        Libro libro = buscarLibroPorTitulo(biblioteca);
        if (libro.getEjemplaresDisponibles()>0){
            System.out.println("Se encuentra disponible el libro ["+libro+"]");
        }
    }
    public void renovarPrestamo() {
        if (!historialPrestamos.isEmpty()) {
            System.out.println("Préstamos actuales:");
            for (int i = 0; i < historialPrestamos.size(); i++) {
                System.out.println((i + 1) + ". " + historialPrestamos.get(i).getTitulo());
            }
            System.out.print("Ingrese el número del préstamo que desea renovar: ");
            int prestamoNumero = teclado.nextInt();
            if (prestamoNumero >= 1 && prestamoNumero <= historialPrestamos.size()) {
                Libro libroARenovar = historialPrestamos.get(prestamoNumero - 1);
                System.out.println("=========Libro (" + libroARenovar.getTitulo()+ ") renovado con exito==========");
            } else {
                System.out.println("Opción inválida. Por favor, seleccione un préstamo válido.");
            }
        } else {
            System.out.println("No tienes préstamos activos que puedas renovar.");
        }
    }

    public void devolverLibro(Biblioteca biblioteca){
        try {
            System.out.println("Ingrese el nombre del libro");
            String nombreLibro = teclado.nextLine();
            for (Libro libro: librosReservados){
                if (libro.getTitulo().equals(nombreLibro)){
                    for (Libro libroBiblioteca: biblioteca.getLibros()){
                        if (libroBiblioteca.getTitulo().equals(nombreLibro)){
                            libroBiblioteca.setEjemplaresDisponibles(libro.getEjemplaresDisponibles()+1);
                            librosReservados.remove(libro);
                            System.out.println("=========Devolucion existosa==========");
                            return;
                        }
                    }
                }
            }
            System.out.println("El libro ingresado no está reservado o no se encuentra en la biblioteca.");
        } catch (Exception e) {
            System.out.println("Ocurrió un error al devolver el libro: " + e.getMessage());
        }
    }

    public void verHistorialDePrestamos() {
        if (historialPrestamos.isEmpty()) {
            System.out.println("No tienes ningún historial de préstamos.");
        } else {
            historialPrestamos.forEach(System.out::println);
        }
    }


    @Override
    public String toString() {
        return "Nombre= " + nombre+ " | Tipo= " + tipo + " | ContraseÃ±a= " + contraseña;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContraseña() {
        return contraseña;
    }
}


