package GIMNASIO;

public abstract class Rutina implements Entrenable {

    protected String nombre;
    protected Ejercicio[] ejercicios;

    public Rutina(String nombre, Ejercicio[] ejercicios) {
        this.nombre = nombre;
        this.ejercicios = ejercicios;
    }

    public String getNombre() {
        return nombre;
    }

    public Ejercicio[] getEjercicios() {
        return ejercicios;
    }

    // total calorias
    public int totalCalorias() {
        int suma = 0;

        for (Ejercicio e : ejercicios) {
            suma += e.getCalorias();
        }

        return suma;
    }

    // dificultad media
    public double dificultadMedia() {
        int suma = 0;

        for (Ejercicio e : ejercicios) {
            suma += e.getDificultad();
        }

        return (double) suma / ejercicios.length;
    }

    // mostrar ejercicios
    public void mostrarEjercicios() {
        for (Ejercicio e : ejercicios) {
            System.out.println(e);
        }
    }

    public abstract double calcularRendimiento();

    @Override
    public String toString() {
        return "Rutina: " + nombre;
    }
}