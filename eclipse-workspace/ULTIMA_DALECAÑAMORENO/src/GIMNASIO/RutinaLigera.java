package GIMNASIO;

public class RutinaLigera extends Rutina {

    private int descansos;

    public RutinaLigera(String nombre, Ejercicio[] ejercicios, int descansos) {
        super(nombre, ejercicios);
        this.descansos = descansos;
    }

    @Override
    public double calcularRendimiento() {
        return totalCalorias() - descansos * 5;
    }

    @Override
    public boolean esExigente() {
        return totalCalorias() > 300;
    }
}