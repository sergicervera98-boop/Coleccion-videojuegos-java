package app;

import modelo.Videojuego;
import persistencia.VideojuegoDAO;
import persistencia.VideojuegoDAOFichero;

public class Main {
    public static void main(String[] args) {
        VideojuegoDAO dao = new VideojuegoDAOFichero("videojuegos.csv");

        for (Videojuego juego : dao.listar()) {
            System.out.println(juego);
        }
    }
}