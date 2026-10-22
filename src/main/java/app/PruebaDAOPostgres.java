package app;

import modelo.Estado;
import modelo.Plataforma;
import modelo.Videojuego;
import persistencia.VideojuegoDAO;
import persistencia.VideojuegoDAOPostgres;

public class PruebaDAOPostgres {

    public static void main(String[] args) {
        VideojuegoDAO dao = new VideojuegoDAOPostgres();

        System.out.println("--- Prueba de modificar ---");
        Videojuego juego = dao.buscarPorId(3);
        juego.setEstado(Estado.JUGANDO);
        juego.setHorasJugadas(5);
        System.out.println("¿Modificado? " + dao.modificar(juego));
        System.out.println("Ahora: " + dao.buscarPorId(3));

        System.out.println("--- Prueba de borrar ---");
        Videojuego temporal = dao.insertar(new Videojuego("Juego de prueba", Plataforma.PC,
                "Test", 2024, Estado.PENDIENTE, 0, 0));
        System.out.println("Insertado temporal con id " + temporal.getId());
        System.out.println("¿Borrado? " + dao.borrar(temporal.getId()));
        System.out.println("Buscándolo otra vez: " + dao.buscarPorId(temporal.getId()));
        System.out.println("¿Borrar el 99? " + dao.borrar(99));
    }
}