package app;

import modelo.Estado;          // NUEVO
import modelo.Plataforma;      // NUEVO
import modelo.Videojuego;
import persistencia.VideojuegoDAO;
import persistencia.VideojuegoDAOFichero;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner teclado = new Scanner(System.in);
    private static final VideojuegoDAO dao = new VideojuegoDAOFichero("videojuegos.csv");

    public static void main(String[] args) {
        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero("Elige una opción: ");

            switch (opcion) {
                case 1 -> anadirJuego();     // NUEVO
                case 2 -> listarJuegos();
                case 3 -> buscarJuego();     // NUEVO
                case 4 -> System.out.println("(Modificar: lo haremos en la parte B2)");
                case 5 -> System.out.println("(Borrar: lo haremos en la parte B2)");
                case 0 -> System.out.println("¡Hasta pronto!");
                default -> System.out.println("Opción no válida.");
            }

        } while (opcion != 0);
    }

    private static void mostrarMenu() {
        System.out.println();
        System.out.println("===== MI COLECCIÓN DE VIDEOJUEGOS =====");
        System.out.println("1. Añadir juego");
        System.out.println("2. Listar juegos");
        System.out.println("3. Buscar juego por id");
        System.out.println("4. Modificar juego");
        System.out.println("5. Borrar juego");
        System.out.println("0. Salir");
    }

    // ===== OPCIONES DEL MENÚ =====

    // NUEVO: opción 1
    private static void anadirJuego() {
        System.out.println("\n--- Añadir juego ---");

        String titulo = leerTexto("Título: ");
        Plataforma plataforma = elegirPlataforma();
        String genero = leerTexto("Género: ");
        int anio = leerEnteroEntre("Año de lanzamiento: ", 1970, 2030);
        Estado estado = elegirEstado();
        int valoracion = leerEnteroEntre("Valoración (1-10, o 0 si aún no lo has valorado): ", 0, 10);
        int horas = leerEnteroEntre("Horas jugadas: ", 0, 100000);

        Videojuego juego = new Videojuego(titulo, plataforma, genero,
                anio, estado, valoracion, horas);
        dao.insertar(juego);

        System.out.println("Juego añadido con id " + juego.getId());
    }

    private static void listarJuegos() {
        List<Videojuego> juegos = dao.listar();

        if (juegos.isEmpty()) {
            System.out.println("Tu colección está vacía.");
            return;
        }

        for (Videojuego juego : juegos) {
            System.out.println(juego);
        }
    }

    // NUEVO: opción 3
    private static void buscarJuego() {
        int id = leerEntero("Id del juego: ");
        Videojuego juego = dao.buscarPorId(id);

        if (juego == null) {
            System.out.println("No existe ningún juego con id " + id);
        } else {
            System.out.println(juego);
        }
    }

    // ===== MÉTODOS AYUDANTES PARA LEER DATOS =====

    private static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = teclado.nextLine();

            try {
                return Integer.parseInt(texto.trim());
            } catch (NumberFormatException e) {
                System.out.println("Eso no es un número. Inténtalo de nuevo.");
            }
        }
    }

    // NUEVO: un número, pero dentro de unos límites
    private static int leerEnteroEntre(String mensaje, int min, int max) {
        while (true) {
            int numero = leerEntero(mensaje);

            if (numero >= min && numero <= max) {
                return numero;
            }
            System.out.println("Debe estar entre " + min + " y " + max + ".");
        }
    }

    // NUEVO: un texto que no puede estar vacío ni contener ;
    private static String leerTexto(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = teclado.nextLine().trim();

            if (texto.isEmpty()) {
                System.out.println("No puede estar vacío.");
            } else if (texto.contains(";")) {
                System.out.println("No puede contener el carácter ;");
            } else {
                return texto;
            }
        }
    }

    // NUEVO: elegir una plataforma de una lista numerada
    private static Plataforma elegirPlataforma() {
        Plataforma[] plataformas = Plataforma.values();

        System.out.println("Plataformas:");
        for (int i = 0; i < plataformas.length; i++) {
            System.out.println("  " + (i + 1) + ". " + plataformas[i]);
        }

        int eleccion = leerEnteroEntre("Elige plataforma: ", 1, plataformas.length);
        return plataformas[eleccion - 1];
    }

    // NUEVO: elegir un estado de una lista numerada
    private static Estado elegirEstado() {
        Estado[] estados = Estado.values();

        System.out.println("Estados:");
        for (int i = 0; i < estados.length; i++) {
            System.out.println("  " + (i + 1) + ". " + estados[i]);
        }

        int eleccion = leerEnteroEntre("Elige estado: ", 1, estados.length);
        return estados[eleccion - 1];
    }
}