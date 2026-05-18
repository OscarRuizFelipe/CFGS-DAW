package GIMNASIO;

public class RutinaIntensiva extends Rutina {

    private int duracion;

    public RutinaIntensiva(String nombre, Ejercicio[] ejercicios, int duracion) {
        super(nombre, ejercicios);
        this.duracion = duracion;
    }

    @Override
    public double calcularRendimiento() {
        return totalCalorias() * duracion;
    }

    @Override
    public boolean esExigente() {
        return dificultadMedia() > 7;
    }
}