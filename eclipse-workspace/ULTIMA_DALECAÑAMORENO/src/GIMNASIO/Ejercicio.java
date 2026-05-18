package GIMNASIO;

public class Ejercicio {

    private String nombre;
    private TipoEjercicio tipo;
    private int calorias;
    private int dificultad;

    public Ejercicio(String nombre, TipoEjercicio tipo, int calorias, int dificultad) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.calorias = calorias;
        this.dificultad = dificultad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCalorias() {
        return calorias;
    }

    public int getDificultad() {
        return dificultad;
    }

    @Override
    public String toString() {
        return nombre + " (" + tipo + ") | Calorias: " + calorias + " | Dificultad: " + dificultad;
    }
}