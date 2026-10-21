package app;

import modelo.Estado;
import modelo.Plataforma;
import modelo.Videojuego;

public class Main {
    public static void main(String[] args) {
        Videojuego juego = new Videojuego(
                "Elden Ring", Plataforma.PS5, "RPG",
                2022, Estado.COMPLETADO, 10, 120);

        System.out.println(juego);

        juego.setHorasJugadas(135);
        System.out.println("Ahora llevo " + juego.getHorasJugadas() + " horas");
    }
}