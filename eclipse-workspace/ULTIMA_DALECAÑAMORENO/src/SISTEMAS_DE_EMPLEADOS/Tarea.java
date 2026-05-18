package SISTEMAS_DE_EMPLEADOS;

public class Tarea {

    private String nombre;
    private int horas;
    private int dificultad;

    public Tarea(String nombre, int horas, int dificultad) {
        this.nombre = nombre;
        this.horas = horas;
        this.dificultad = dificultad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getHoras() {
        return horas;
    }

    public int getDificultad() {
        return dificultad;
    }

    @Override
    public String toString() {
        return nombre + " | Horas: " + horas + " | Dificultad: " + dificultad;
    }
}