package persistencia;

import modelo.Estado;
import modelo.Plataforma;
import modelo.Videojuego;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VideojuegoDAOPostgres implements VideojuegoDAO {

    // ===== ZONA 1: MÉTODOS DEL CONTRATO =====
    @Override
    public Videojuego insertar(Videojuego juego) {
        String sql = "INSERT INTO videojuego "
                + "(titulo, plataforma, genero, anio, estado, valoracion, horas_jugadas) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Rellenar los huecos ? (¡empiezan en 1, no en 0!)
            sentencia.setString(1, juego.getTitulo());
            sentencia.setString(2, juego.getPlataforma().name());
            sentencia.setString(3, juego.getGenero());
            sentencia.setInt(4, juego.getAnio());
            sentencia.setString(5, juego.getEstado().name());
            sentencia.setInt(6, juego.getValoracion());
            sentencia.setInt(7, juego.getHorasJugadas());

            sentencia.executeUpdate();

            // Preguntar a PostgreSQL qué id le ha puesto
            try (ResultSet claves = sentencia.getGeneratedKeys()) {
                if (claves.next()) {
                    juego.setId(claves.getInt("id"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("No se pudo guardar el juego en la base de datos", e);
        }

        return juego;
    }

    @Override
    public List<Videojuego> listar() {
        List<Videojuego> juegos = new ArrayList<>();
        String sql = "SELECT * FROM videojuego ORDER BY id";

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {
                juegos.add(filaAVideojuego(resultado));
            }

        } catch (SQLException e) {
            throw new RuntimeException("No se pudieron leer los juegos de la base de datos", e);
        }

        return juegos;
    }

    @Override
    public Videojuego buscarPorId(int id) {
        String sql = "SELECT * FROM videojuego WHERE id = ?";

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, id);

            try (ResultSet resultado = sentencia.executeQuery()) {
                if (resultado.next()) {
                    return filaAVideojuego(resultado);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("No se pudo buscar el juego con id " + id, e);
        }

        return null;
    }

    @Override
    public boolean modificar(Videojuego juego) {
        String sql = "UPDATE videojuego SET titulo = ?, plataforma = ?, genero = ?, anio = ?, "
                + "estado = ?, valoracion = ?, horas_jugadas = ? "
                + "WHERE id = ?";

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, juego.getTitulo());
            sentencia.setString(2, juego.getPlataforma().name());
            sentencia.setString(3, juego.getGenero());
            sentencia.setInt(4, juego.getAnio());
            sentencia.setString(5, juego.getEstado().name());
            sentencia.setInt(6, juego.getValoracion());
            sentencia.setInt(7, juego.getHorasJugadas());
            sentencia.setInt(8, juego.getId());

            int filasCambiadas = sentencia.executeUpdate();
            return filasCambiadas > 0;

        } catch (SQLException e) {
            throw new RuntimeException("No se pudo modificar el juego con id " + juego.getId(), e);
        }
    }

    @Override
    public boolean borrar(int id) {
        String sql = "DELETE FROM videojuego WHERE id = ?";

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, id);

            int filasBorradas = sentencia.executeUpdate();
            return filasBorradas > 0;

        } catch (SQLException e) {
            throw new RuntimeException("No se pudo borrar el juego con id " + id, e);
        }
    }

    // ===== ZONA 2: TRADUCTOR (fila de la tabla → objeto Java) =====
    private Videojuego filaAVideojuego(ResultSet resultado) throws SQLException {
        return new Videojuego(
                resultado.getInt("id"),
                resultado.getString("titulo"),
                Plataforma.valueOf(resultado.getString("plataforma")),
                resultado.getString("genero"),
                resultado.getInt("anio"),
                Estado.valueOf(resultado.getString("estado")),
                resultado.getInt("valoracion"),
                resultado.getInt("horas_jugadas")
        );
    }
}
