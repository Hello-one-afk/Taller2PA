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
        this.contraseña = contraseña;
        this.historialPrestamos = new ArrayList<>();
        this.librosReservados = new ArrayList<>();
        this.calificaciones = new ArrayList<>();
    }

    private String leerEntrada(String mensaje, Scanner scanner) {
        System.out.println(mensaje);
        return scanner.nextLine();
    }

    private int leerEntero(String mensaje, Scanner scanner) {
        System.out.println(mensaje);
        return Integer.parseInt(scanner.nextLine());
    }

    public void agregarPrestamo(Biblioteca biblioteca) {
        Libro libro = buscarLibroPorTitulo(biblioteca);

        if (libro.getEjemplaresDisponibles() > 0) {
            realizarReserva(libro);
            libro.setEjemplaresDisponibles(libro.getEjemplaresDisponibles() - 1);
            historialPrestamos.add(libro);
            System.out.println("=========Prestamo solicitado con exito==========");
        } else {
            System.out.println("Libro no disponible [" + libro + "]");
        }
    }

    public void realizarReserva(Libro libro) {
        librosReservados.add(libro);
    }

    public void agregarComentario(Biblioteca biblioteca) {
        Libro libroComentario = buscarLibroPorTitulo(biblioteca);
        String comentario = leerEntrada("Agrega un comentario: ",teclado);
        for (Libro libro : biblioteca.getLibros()){
            if (libro.getTitulo().equals(libroComentario.getTitulo())){

                libro.getComentarios().add(comentario);
            }
        }
    }
    public void verComentarios(Biblioteca biblioteca){
        Libro libroComentario = buscarLibroPorTitulo(biblioteca);
        for (Libro libro : biblioteca.getLibros()){
            if (libro.getTitulo().equals(libroComentario.getTitulo())){
                System.out.println(libro.getComentarios().toString());
            }
        }
    }

    public Libro buscarLibroPorTitulo(Biblioteca biblioteca) {
        try {
            String tituloLibro = leerEntrada("Ingrese el nombre del título que desea buscar: ", teclado);
            for (Libro libro : biblioteca.getLibros()) {
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

    public void buscarLibroPorAutor(Biblioteca biblioteca) {
        try {
            String nombreAutor = leerEntrada("Ingrese el nombre del autor que desea buscar: ", teclado);
            boolean encontrado = false;
            for (Libro libro : biblioteca.getLibros()) {
                if (libro.getAutor().equals(nombreAutor)) {
                    System.out.println(libro);
                    encontrado = true;
                }
            }
            if (!encontrado) {
                System.out.println("No se encontraron libros por este Autor");
            }
        } catch (Exception e) {
            System.out.println("Ocurrió un error al buscar libros: " + e.getMessage());
        }
    }

    public void buscarLibroPorCategoria(Biblioteca biblioteca) {
        try {
            String nombreLibroCategoria = leerEntrada("Ingrese el nombre por categoria que desea buscar: ", teclado);
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
        } catch (Exception e) {
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


    public void buscarLibroPorPeorValoracion(Biblioteca biblioteca) {
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

    public void buscarLibroPorValoracion(Biblioteca biblioteca) {
        try {
            int valoracion = leerEntero("Ingrese valoración del libro: (rango 1-10)",teclado);
            boolean encontrado = false;
            for (Libro libro : biblioteca.getLibros()) {
                if (libro.getCalificaciones().contains(valoracion)) {
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
            String date = leerEntrada("Ingrese la fecha del libro: (YY-MM-DD)", teclado);
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


    public void buscarLibroPorMasLLevado() {
        //no se

    }


    public void verDisponibilidadLibro(Biblioteca biblioteca) {
        Libro libro = buscarLibroPorTitulo(biblioteca);
        if (libro.getEjemplaresDisponibles() > 0) {
            System.out.println("Se encuentra disponible el libro [" + libro + "]");
        }
    }

    public void renovarPrestamo() {
        if (!librosReservados.isEmpty()) {
            System.out.println("Préstamos actuales:");
            for (int i = 0; i < librosReservados.size(); i++) {
                System.out.println((i + 1) + ". " + librosReservados.get(i).getTitulo());
            }
            int prestamoNumero = leerEntero("Ingrese el número del préstamo que desea renovar: ", teclado);
            if (prestamoNumero >= 1 && prestamoNumero <= librosReservados.size()) {
                Libro libroARenovar = librosReservados.get(prestamoNumero - 1);
                System.out.println("=========Libro (" + libroARenovar.getTitulo() + ") renovado con exito==========");
            } else {
                System.out.println("Opción inválida. Por favor, seleccione un préstamo válido.");
            }
        } else {
            System.out.println("No tienes préstamos activos que puedas renovar.");
        }
    }

    public void devolverLibro(Biblioteca biblioteca) {
        try {
            String nombreLibro = leerEntrada("Ingrese el nombre del libro", teclado);
            for (Libro libro : librosReservados) {
                if (libro.getTitulo().equals(nombreLibro)) {
                    for (Libro libroBiblioteca : biblioteca.getLibros()) {
                        if (libroBiblioteca.getTitulo().equals(nombreLibro)) {
                            libroBiblioteca.setEjemplaresDisponibles(libro.getEjemplaresDisponibles() + 1);
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
        return "Nombre= " + nombre + " | Tipo= " + tipo + " | Contraseña= " + contraseña;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContraseña() {
        return contraseña;
    }
}


