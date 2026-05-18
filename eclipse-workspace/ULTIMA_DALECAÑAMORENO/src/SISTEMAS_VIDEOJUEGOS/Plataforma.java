package SISTEMAS_VIDEOJUEGOS;

public abstract class Plataforma implements Jugable {

    protected String nombre;
    protected Juego[] juegos;

    public Plataforma(String nombre, Juego[] juegos) {
        this.nombre = nombre;
        this.juegos = juegos;
    }

    public String getNombre() {
        return nombre;
    }

    public Juego[] getJuegos() {
        return juegos;
    }

    // total horas
    public int totalHorasJugadas() {
        int suma = 0;

        for (Juego j : juegos) {
            suma += j.getHorasJugadas();
        }

        return suma;
    }

    // puntuacion media
    public double puntuacionMedia() {
        double suma = 0;

        for (Juego j : juegos) {
            suma += j.getPuntuacion();
        }

        return suma / juegos.length;
    }

    // mostrar juegos
    public void mostrarJuegos() {
        for (Juego j : juegos) {
            System.out.println(j);
        }
    }

    public abstract double calcularExito();

    @Override
    public String toString() {
        return "Plataforma: " + nombre;
    }
}