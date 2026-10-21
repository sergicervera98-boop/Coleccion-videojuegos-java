package app;

import modelo.Estado;
import modelo.Plataforma;
import modelo.Videojuego;
import persistencia.VideojuegoDAO;
import persistencia.VideojuegoDAOFichero;

public class Main {
    public static void main(String[] args) {
        VideojuegoDAO dao = new VideojuegoDAOFichero("videojuegos.csv");

        // INSERTAR un juego nuevo
        Videojuego nuevo = new Videojuego("Celeste", Plataforma.SWITCH, "Plataformas",
                2018, Estado.PENDIENTE, 0, 0);
        dao.insertar(nuevo);
        System.out.println("Insertado con id: " + nuevo.getId());

        // LISTAR todos
        System.out.println("\n--- Mi colección ---");
        for (Videojuego juego : dao.listar()) {
            System.out.println(juego);
        }

        // BUSCAR uno que existe y otro que no
        System.out.println("\n--- Búsquedas ---");
        System.out.println("Juego con id 2: " + dao.buscarPorId(2));
        System.out.println("Juego con id 99: " + dao.buscarPorId(99));
    }
}