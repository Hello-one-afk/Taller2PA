package org.example;

import java.util.Scanner;

public class Main {
    static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
        int x = 0;
        System.out.println("=====================Bienvenido=====================");
        Login login = new Login();
        Biblioteca biblioteca = new Biblioteca();
        Administrador administrador = new Administrador(biblioteca);

        do {
            try {
                System.out.println("╔════════════════════════════════════════╗");
                System.out.println("║           [1] INICIAR SESION           ║");
                System.out.println("║           [2] CREAR CUENTA             ║");
                System.out.println("║           [3] ADMINISTRADOR            ║");
                System.out.println("║           [4] SALIR                    ║");
                System.out.println("║           ELIJA UNA OPCION             ║");
                System.out.println("╚════════════════════════════════════════╝");
                x = validar(4);

                switch (x) {
                    case 1 -> {
                        if (login.autenticacion()) {
                            Usuario usuarioLogeado = login.buscarUsuarioLogeado(login.getNombre(), login.getContra());
                            menu(usuarioLogeado,false,biblioteca);
                        }
                    }
                    case 2 -> login.validacionRegistroUsuario();
                    case 3 -> menu(new Usuario("Admin","","admin123"), true,biblioteca);
                }
            } catch (Exception e) {
                System.out.println("ERROR vuelva a intentar");
            }

        } while (x != 4);
        System.out.println("Finalizando...");

    }

    public static void menu(Usuario usuarioLogeado, boolean esAdmin, Biblioteca biblioteca) {
        int opcion = 0;
        Administrador administrador = new Administrador();
        do {
            try {
                System.out.println("╔═════════════════════════════════════════╗");
                System.out.println("║           Menú Principal                ║");
                System.out.println("╠═════════════════════════════════════════╣");
                System.out.println("║ [1] Solicitar Préstamo de Libro         ║");
                System.out.println("║ [2] Ver Disponibilidad del Libro        ║");
                System.out.println("║ [3] Renovar Préstamo                    ║");
                System.out.println("║ [4] Devolver Libro                      ║");
                System.out.println("║ [5] Ver Historial Libros Prestados      ║");
                System.out.println("║ [6] Buscar Libro por Título             ║");
                System.out.println("║ [7] Buscar Libro por Autor              ║");
                System.out.println("║ [8] Buscar Libro por Categoría          ║");
                System.out.println("║ [9] Buscar Libro por mejor Valoración   ║");
                System.out.println("║ [10] Buscar Libro por peor Valoración   ║");
                System.out.println("║ [11] Buscar Libro por valoracion exacta ║");
                System.out.println("║ [12] Buscar Libro por fecha             ║");

                if (esAdmin){
                    System.out.println("║ [13] Agregar Libro                      ║");
                    System.out.println("║ [14] Eliminar Libro                     ║");
                    System.out.println("║ [15] Actualizar Libro                   ║");
                }
                System.out.println("║ [16] Salir                              ║");
                System.out.println("╚═════════════════════════════════════════╝");
                System.out.print("Ingrese su opcion: ");
                opcion = teclado.nextInt();

                switch (opcion) {
                    case 1 -> usuarioLogeado.solicitarPrestamoLibro(biblioteca);
                    case 2 -> usuarioLogeado.verDisponibilidadLibro(biblioteca);
                    case 3 -> usuarioLogeado.renovarPrestamo();
                    case 4 -> usuarioLogeado.devolverLibro(biblioteca);
                    case 5 -> usuarioLogeado.verHistorialDePrestamos();
                    case 6 -> usuarioLogeado.buscarLibroPorTitulo(biblioteca);
                    case 7 -> usuarioLogeado.buscarLibroPorAutor(biblioteca);
                    case 8 -> usuarioLogeado.buscarLibroPorCategoria(biblioteca);
                    case 9 ->usuarioLogeado.buscarLibroPorMejorValoracion(biblioteca);
                    case 10 -> usuarioLogeado.buscarLibroPorPeorValoracion(biblioteca);
                    case 11 -> usuarioLogeado.buscarLibroPorValoracion(biblioteca);
                    case 12 -> usuarioLogeado.buscarLibrosPorFecha(biblioteca);
                    case 13 -> {
                        if (esAdmin) {
                            administrador.agregarLibro(biblioteca);
                        } else {
                            System.out.println("Opción no válida para este usuario.");
                        }
                    }
                    case 14 -> {
                        if (esAdmin) {
                            administrador.eliminarLibro(biblioteca);
                        } else {
                            System.out.println("Opción no válida para este usuario.");
                        }
                    }
                    case 15 -> {
                        if (esAdmin) {

                        } else {
                        }
                    }

                    default -> System.out.println("Opcion invalida. Por favor, ingrese una opciÃ³n vÃ¡lida.");
                }
            } catch (Exception e) {
                System.out.println("ERROR vuelva a intentar");
                teclado.nextLine();
            }
        } while (opcion != 16);
    }
    public static int validar(int x) {
        int n = -1;
        do {
            Scanner teclado = new Scanner(System.in);
            n = teclado.nextInt();
            if (n <= 0 || n > x) {
                System.out.println("ingrese un numero valido");
            }
        } while (n <= 0 || n > x);
        return n;
    }





}


/*

//parte del profe

Sistema de GestiÃ³n de Biblioteca

En una biblioteca universitaria, se necesita un sistema de gestiÃ³n para administrar el prÃ©stamo y
la devoluciÃ³n de libros. Los USUARIO pueden ser estudiantes, profesores o personal de la biblioteca.
Cada libro tiene un tÃ­tulo, un autor, una categorÃ­a y un nÃºmero de ejemplares disponibles para prÃ©stamo.
Los usuarios pueden buscar libros por tÃ­tulo, autor o categorÃ­a, y pueden solicitar el prÃ©stamo de un libro
si estÃ¡ disponible.

El sistema debe permitir a los usuarios registrarse y autenticarse para acceder a sus cuentas.(Iniciar Sesion/registrarse)


Una vez autenticados, los usuarios pueden realizar diferentes acciones, como buscar libros, solicitar prÃ©stamos,
renovar prÃ©stamos y devolver libros. Los administradores de la biblioteca tienen acceso adicional para agregar
nuevos libros, eliminar libros, modificar informaciÃ³n de libros y administrar usuarios.




1- Que existan secciones como filtros de bÃºsqueda por valoraciÃ³n(mejores valorados y peores valorados),
los mÃ¡s llevados, los mÃ¡s recientes y antiguos.

2- Establecer un lÃ­mite de libros prestados por usuario registrado, por ejemplo que un usuario
puede solicitar un mÃ¡ximo de 7 libros.

3- Cuando un usuario solicita un prÃ©stamo de x libro tenga un tiempo lÃ­mite de devoluciÃ³n 30 dÃ­as y cuando sea
el dÃ­a de devolver el libro, se le envÃ­e una notificaciÃ³n al usuario como recordatorio.

4- Que un usuario ya registrado al devolver el libro o al solicitar un prÃ©stamo, tenga la opciÃ³n de agregar una reseÃ±a
sobre este, con una valoraciÃ³n y su respectiva opiniÃ³n.

5- En el rol de administrador, realizar sanciones a los usuarios que no devuelvan los
libros en la fecha correspondida o superen el lÃ­mite de entrega.

6- En el rol de administrador, sancionar y censurar reseÃ±as no aptas en los libros.

7- Agregar un contador de sanciones al usuario si este pasa mÃ¡s de 3
sanciones, serÃ¡ bloqueado y eliminado de la biblioteca.



*/
