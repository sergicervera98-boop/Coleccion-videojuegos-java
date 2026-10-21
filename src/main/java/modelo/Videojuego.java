package modelo;

public class Videojuego {

    // 1. ATRIBUTOS: los datos de cada juego
    private int id;
    private String titulo;
    private Plataforma plataforma;
    private String genero;
    private int anio;
    private Estado estado;
    private int valoracion;
    private int horasJugadas;

    // 2. CONSTRUCTOR para juegos NUEVOS (todavía sin id)
    public Videojuego(String titulo, Plataforma plataforma, String genero,
                      int anio, Estado estado, int valoracion, int horasJugadas) {
        this.titulo = titulo;
        this.plataforma = plataforma;
        this.genero = genero;
        this.anio = anio;
        this.estado = estado;
        this.valoracion = valoracion;
        this.horasJugadas = horasJugadas;
    }

    // 3. CONSTRUCTOR para juegos que YA ESTÁN GUARDADOS (con id)
    public Videojuego(int id, String titulo, Plataforma plataforma, String genero,
                      int anio, Estado estado, int valoracion, int horasJugadas) {
        this(titulo, plataforma, genero, anio, estado, valoracion, horasJugadas);
        this.id = id;
    }

    // 4. GETTERS Y SETTERS
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public Plataforma getPlataforma() { return plataforma; }
    public void setPlataforma(Plataforma plataforma) { this.plataforma = plataforma; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public int getAnio() { return anio; }
    public void setAnio(int anio) { this.anio = anio; }

    public Estado getEstado() { return estado; }
    public void setEstado(Estado estado) { this.estado = estado; }

    public int getValoracion() { return valoracion; }
    public void setValoracion(int valoracion) { this.valoracion = valoracion; }

    public int getHorasJugadas() { return horasJugadas; }
    public void setHorasJugadas(int horasJugadas) { this.horasJugadas = horasJugadas; }

    // 5. toString: cómo se ve un juego al imprimirlo
    @Override
    public String toString() {
        return "#" + id + " " + titulo + " (" + anio + ") - " + plataforma
                + " - " + genero + " - " + estado
                + " - " + valoracion + "/10 - " + horasJugadas + " h";
    }
}
