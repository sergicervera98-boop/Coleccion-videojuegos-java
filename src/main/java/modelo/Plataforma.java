package modelo;

public enum Plataforma {
    PC("PC"),
    PS5("PlayStation 5"),
    PS4("PlayStation 4"),
    XBOX_SERIES("Xbox Series X|S"),
    SWITCH("Nintendo Switch"),
    MOVIL("Móvil");

    private final String nombre;

    Plataforma(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}