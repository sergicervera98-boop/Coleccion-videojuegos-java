package persistencia;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionBD {

    // ===== ZONA 1: ATRIBUTOS =====
    private static final String FICHERO_CONFIGURACION = "bd.properties";

    // ===== ZONA 2: DAR UNA CONEXIÓN =====
    public static Connection obtenerConexion() throws SQLException {
        Properties config = leerConfiguracion();

        String url = config.getProperty("url");
        String usuario = config.getProperty("usuario");
        String contrasena = config.getProperty("contrasena");

        return DriverManager.getConnection(url, usuario, contrasena);
    }

    // ===== ZONA 3: LEER bd.properties =====
    private static Properties leerConfiguracion() {
        Properties config = new Properties();

        try (FileReader lector = new FileReader(FICHERO_CONFIGURACION, StandardCharsets.UTF_8)) {
            config.load(lector);
        } catch (IOException e) {
            throw new RuntimeException("No se encontró el fichero " + FICHERO_CONFIGURACION
                    + ". Cópialo de bd.properties.ejemplo y pon tus datos.", e);
        }

        return config;
    }
}
