package modelo;

public class Incidencia {

    private static int contadorId = 1;

    private int id;
    private String tipo;      
    private String descripcion;
    private String idEquipo;
    private String idJugador; 

    public Incidencia(String tipo, String descripcion, String idEquipo, String idJugador) {
        this.id = contadorId++;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.idEquipo = idEquipo;
        this.idJugador = idJugador;
    }

    public int getId() { return id; }
    public String getTipo() { return tipo; }
    public String getDescripcion() { return descripcion; }
    public String getIdEquipo() { return idEquipo; }
    public String getIdJugador() { return idJugador; }

    @Override
    public String toString() {
        return "Incidencia #" + id + " [" + tipo + "] " + descripcion
                + " | Equipo: " + idEquipo
                + (idJugador != null ? " | Jugador: " + idJugador : "");
    }
}
