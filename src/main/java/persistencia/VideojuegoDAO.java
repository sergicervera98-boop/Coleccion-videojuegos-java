package persistencia;

import modelo.Videojuego;

import java.util.List;

public interface VideojuegoDAO {

    // Guarda un juego nuevo y lo devuelve con su id ya asignado
    Videojuego insertar(Videojuego juego);

    // Devuelve todos los juegos de la colección
    List<Videojuego> listar();

    // Devuelve el juego con ese id, o null si no existe
    Videojuego buscarPorId(int id);

    // Actualiza los datos de un juego existente. Devuelve true si lo encontró
    boolean modificar(Videojuego juego);

    // Borra el juego con ese id. Devuelve true si lo encontró
    boolean borrar(int id);
}