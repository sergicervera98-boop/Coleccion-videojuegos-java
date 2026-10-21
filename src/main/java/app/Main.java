package app;

import modelo.Videojuego;
import persistencia.VideojuegoDAO;
import persistencia.VideojuegoDAOFichero;

import java.util.List;
import java.util.Scanner;

public class Main {

    // Herramientas que usa todo el programa
    private static final Scanner teclado = new Scanner(System.in);
    private static final VideojuegoDAO dao = new VideojuegoDAOFichero("videojuegos.csv");

    public static void main(String[] args) {
        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero("Elige una opción: ");

            switch (opcion) {
                case 1 -> System.out.println("(Añadir: lo haremos en la parte B)");
                case 2 -> listarJuegos();
                case 3 -> System.out.println("(Buscar: lo haremos en la parte B)");
                case 4 -> System.out.println("(Modificar: lo haremos en la parte B)");
                case 5 -> System.out.println("(Borrar: lo haremos en la parte B)");
                case 0 -> System.out.println("¡Hasta pronto!");
                default -> System.out.println("Opción no válida.");
            }

        } while (opcion != 0);
    }

    // Muestra las opciones por pantalla
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

    // Opción 2: listar todos los juegos
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

    // Pide un número por teclado y no para hasta que el usuario escriba uno válido
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
}