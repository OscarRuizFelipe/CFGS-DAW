package SISTEMAS_VIDEOJUEGOS;

public class Juego {

    private String nombre;
    private Genero genero;
    private int horasJugadas;
    private double puntuacion;

    public Juego(String nombre, Genero genero, int horasJugadas, double puntuacion) {
        this.nombre = nombre;
        this.genero = genero;
        this.horasJugadas = horasJugadas;
        this.puntuacion = puntuacion;
    }

    public String getNombre() {
        return nombre;
    }

    public int getHorasJugadas() {
        return horasJugadas;
    }

    public double getPuntuacion() {
        return puntuacion;
    }

    @Override
    public String toString() {
        return nombre + " (" + genero + ") | Horas: " + horasJugadas + " | Puntuación: " + puntuacion;
    }
}