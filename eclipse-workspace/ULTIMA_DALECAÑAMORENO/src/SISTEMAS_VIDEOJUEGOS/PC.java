package SISTEMAS_VIDEOJUEGOS;

public class PC extends Plataforma {

    private int usuariosActivos;

    public PC(String nombre, Juego[] juegos, int usuariosActivos) {
        super(nombre, juegos);
        this.usuariosActivos = usuariosActivos;
    }

    @Override
    public double calcularExito() {
        return puntuacionMedia() + usuariosActivos * 0.3;
    }

    @Override
    public boolean esPopular() {
        return usuariosActivos > 500;
    }
}