package EXAMEN_5;

public abstract class Pokemon implements Entrenable {

    private int numeroPokedex;
    private String nombre;
    private Tipo tipo;
    private int nivelActual;
    private Movimiento[] movimientos;
    private int[][] aprendizaje; // columna 0: nivel, columna 1: potencia

    public Pokemon(int numeroPokedex, String nombre, Tipo tipo, int nivelActual,
                   Movimiento[] movimientos, int[][] aprendizaje) {
        this.numeroPokedex = numeroPokedex;
        this.nombre = nombre;
        this.tipo = tipo;
        this.nivelActual = nivelActual;
        this.movimientos = movimientos;
        this.aprendizaje = aprendizaje;
    }

    public int getNumeroPokedex() {
        return numeroPokedex;
    }

    public String getNombre() {
        return nombre;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public int getNivelActual() {
        return nivelActual;
    }

    public Movimiento[] getMovimientos() {
        return movimientos;
    }

    public int[][] getAprendizaje() {
        return aprendizaje;
    }

    public int contarMovimientosDisponibles() {
        int contador = 0;

        for (int i = 0; i < aprendizaje.length; i++) {
            if (aprendizaje[i][0] <= nivelActual) {
                contador++;
            }
        }

        return contador;
    }

    public double calcularPotenciaMediaDisponible() {
        int suma = 0;
        int contador = 0;

        for (int i = 0; i < aprendizaje.length; i++) {
            if (aprendizaje[i][0] <= nivelActual) {
                suma += aprendizaje[i][1];
                contador++;
            }
        }

        if (contador == 0) {
            return 0;
        }

        return (double) suma / contador;
    }

    public void mostrarMovimientosDisponibles() {
        for (int i = 0; i < aprendizaje.length; i++) {
            if (aprendizaje[i][0] <= nivelActual) {
                System.out.println(
                    movimientos[i].getNombre() +
                    " | Nivel aprendizaje: " + aprendizaje[i][0] +
                    " | Potencia: " + aprendizaje[i][1]
                );
            }
        }
    }

    public abstract double calcularIndiceCombate();

    @Override
    public String toString() {
        return "Pokédex: " + numeroPokedex +
               " | Nombre: " + nombre +
               " | Tipo: " + tipo +
               " | Nivel actual: " + nivelActual;
    }
}