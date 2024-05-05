import java.util.Scanner;

public class Main {
    static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
        int x = 0;
        System.out.println("=====================Bienvenido=====================");
        Login login = new Login();
        Biblioteca biblioteca = new Biblioteca();

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
                opcionesMenuInicio(login, biblioteca, x);
            } catch (Exception e) {
                System.out.println("ERROR vuelva a intentar");
            }
        } while (x != 4);
        System.out.println("Finalizando...");
    }

    public static void opcionesMenuInicio(Login login, Biblioteca biblioteca, int opcion) {
        switch (opcion) {
            case 1 -> {
                if (login.autenticacion()) {
                    Usuario usuarioLogeado = login.buscarUsuarioLogeado(login.getNombre(), login.getContra());
                    menu(login, usuarioLogeado, false, biblioteca);
                }
            }
            case 2 -> login.validacionRegistroUsuario();
            case 3 -> menu(login, new Usuario("Admin", "", "admin123"), true, biblioteca);
        }
    }

    public static void printMenuPrincipal(boolean esAdmin) {
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
        System.out.println("║ [13] Agregar Comentario a un libro      ║");
        System.out.println("║ [14] Ver Comentarios de un libro        ║");

        if (esAdmin) {
            System.out.println("║ [15] Agregar Libro                      ║");
            System.out.println("║ [16] Eliminar Libro                     ║");
            System.out.println("║ [17] Actualizar Libro                   ║");
            System.out.println("║ [18] Administrar Usuario                ║");

        }
        System.out.println("║ [19] Salir                              ║");
        System.out.println("╚═════════════════════════════════════════╝");
        System.out.print("Ingrese su opcion: ");
    }

    public static void opcionesMenuPrincipal(Login usuarios, int opcion, Usuario usuarioLogeado, boolean esAdmin, Administrador administrador, Biblioteca biblioteca) {

        switch (opcion) {
            case 1 -> usuarioLogeado.agregarPrestamo(biblioteca);
            case 2 -> usuarioLogeado.verDisponibilidadLibro(biblioteca);
            case 3 -> usuarioLogeado.renovarPrestamo();
            case 4 -> usuarioLogeado.devolverLibro(biblioteca);
            case 5 -> usuarioLogeado.verHistorialDePrestamos();
            case 6 -> usuarioLogeado.buscarLibroPorTitulo(biblioteca);
            case 7 -> usuarioLogeado.buscarLibroPorAutor(biblioteca);
            case 8 -> usuarioLogeado.buscarLibroPorCategoria(biblioteca);
            case 9 -> usuarioLogeado.buscarLibroPorMejorValoracion(biblioteca);
            case 10 -> usuarioLogeado.buscarLibroPorPeorValoracion(biblioteca);
            case 11 -> usuarioLogeado.buscarLibroPorValoracion(biblioteca);
            case 12 -> usuarioLogeado.buscarLibrosPorFecha(biblioteca);
            case 13 -> usuarioLogeado.agregarComentario(biblioteca);
            case 14 -> usuarioLogeado.verComentarios(biblioteca);
            case 15 -> administradorOpcion13(esAdmin, administrador, biblioteca);
            case 16 -> administradorOpcion14(esAdmin, administrador, biblioteca);
            case 17 -> administradorOpcion15(esAdmin, administrador, biblioteca);
            case 18 -> administrador.administrarUsuarios(usuarios);
            default -> System.out.println("Opcion invalida. Por favor, ingrese una opcion valida.");
        }
    }

    public static void menu(Login usuarios, Usuario usuarioLogeado, boolean esAdmin, Biblioteca biblioteca) {
        int opcion = 0;
        Administrador administrador = new Administrador(biblioteca);
        do {
            try {
                printMenuPrincipal(esAdmin);
                opcion = teclado.nextInt();
                opcionesMenuPrincipal(usuarios, opcion, usuarioLogeado, esAdmin, administrador, biblioteca);
            } catch (Exception e) {
                System.out.println("ERROR vuelva a intentar"+ e.getMessage() +" | "+e.getCause()+ " |  "+e.getLocalizedMessage());
                teclado.nextLine();
            }
        } while (opcion != 19);
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


    public static void administradorOpcion13(boolean esAdmin, Administrador administrador, Biblioteca biblioteca) {
        if (esAdmin) {
            administrador.agregarLibro(biblioteca);
        } else {
            opcionInvalida();
        }
    }

    public static void administradorOpcion14(boolean esAdmin, Administrador administrador, Biblioteca biblioteca) {
        if (esAdmin) {
            administrador.eliminarLibro(biblioteca);
        } else {
            opcionInvalida();
        }
    }

    public static void administradorOpcion15(boolean esAdmin, Administrador administrador, Biblioteca biblioteca) {
        if (esAdmin) {
            administrador.modificarInformacionDelLibro(biblioteca);
        } else {
            opcionInvalida();
        }
    }

    public static void opcionInvalida() {
        System.out.println("Opción no válida para este usuario.");
    }

}
