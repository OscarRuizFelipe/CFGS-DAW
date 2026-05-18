package SISTEMAS_VIDEOJUEGOS;

public class Consola extends Plataforma {

    private int ventas;

    public Consola(String nombre, Juego[] juegos, int ventas) {
        super(nombre, juegos);
        this.ventas = ventas;
    }

    @Override
    public double calcularExito() {
        return puntuacionMedia() * ventas;
    }

    @Override
    public boolean esPopular() {
        return ventas > 1000;
    }
}