package persistencia;

import modelo.Estado;
import modelo.Plataforma;
import modelo.Videojuego;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class VideojuegoDAOFichero implements VideojuegoDAO {

    // ===== ZONA 1: ATRIBUTOS =====
    private static final String SEPARADOR = ";";
    private final File fichero;

    // ===== ZONA 2: CONSTRUCTOR =====
    public VideojuegoDAOFichero(String nombreFichero) {
        this.fichero = new File(nombreFichero);
    }

    // ===== ZONA 3: MÉTODOS DEL CONTRATO (todavía vacíos) =====
    @Override
    public Videojuego insertar(Videojuego juego) {
        List<Videojuego> juegos = leerTodos();

        // Calcular el siguiente id: el más alto + 1
        int nuevoId = 1;
        for (Videojuego j : juegos) {
            if (j.getId() >= nuevoId) {
                nuevoId = j.getId() + 1;
            }
        }

        juego.setId(nuevoId);
        juegos.add(juego);
        guardarTodos(juegos);

        return juego;
    }

    @Override
    public List<Videojuego> listar() {
        return leerTodos();
    }

    @Override
    public Videojuego buscarPorId(int id) {
        for (Videojuego juego : leerTodos()) {
            if (juego.getId() == id) {
                return juego;
            }
        }
        return null;
    }

    @Override
    public boolean modificar(Videojuego juego) {
        return false;
    }

    @Override
    public boolean borrar(int id) {
        return false;
    }

    // ===== ZONA 4: TRADUCTORES (métodos de ayuda) =====
    private String videojuegoALinea(Videojuego juego) {
        return juego.getId() + SEPARADOR
                + juego.getTitulo() + SEPARADOR
                + juego.getPlataforma().name() + SEPARADOR
                + juego.getGenero() + SEPARADOR
                + juego.getAnio() + SEPARADOR
                + juego.getEstado().name() + SEPARADOR
                + juego.getValoracion() + SEPARADOR
                + juego.getHorasJugadas();
    }

    private Videojuego lineaAVideojuego(String linea) {
        String[] partes = linea.split(SEPARADOR);

        return new Videojuego(
                Integer.parseInt(partes[0]),   // id
                partes[1],                     // título
                Plataforma.valueOf(partes[2]), // plataforma
                partes[3],                     // género
                Integer.parseInt(partes[4]),   // año
                Estado.valueOf(partes[5]),     // estado
                Integer.parseInt(partes[6]),   // valoración
                Integer.parseInt(partes[7])    // horas jugadas
        );
    }
    // ===== ZONA 5 (NUEVA): LEER Y ESCRIBIR EL FICHERO =====
    private List<Videojuego> leerTodos() {
        List<Videojuego> juegos = new ArrayList<>();

        // Si el fichero todavía no existe, la colección está vacía
        if (!fichero.exists()) {
            return juegos;
        }

        try (BufferedReader lector = new BufferedReader(
                new FileReader(fichero, StandardCharsets.UTF_8))) {

            String linea;
            while ((linea = lector.readLine()) != null) {
                if (!linea.isBlank()) {
                    juegos.add(lineaAVideojuego(linea));
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("No se pudo leer el fichero " + fichero.getName(), e);
        }

        return juegos;
    }

    private void guardarTodos(List<Videojuego> juegos) {
        try (BufferedWriter escritor = new BufferedWriter(
                new FileWriter(fichero, StandardCharsets.UTF_8))) {

            for (Videojuego juego : juegos) {
                escritor.write(videojuegoALinea(juego));
                escritor.newLine();
            }

        } catch (IOException e) {
            throw new RuntimeException("No se pudo escribir el fichero " + fichero.getName(), e);
        }
    }
}