package EXAMEN_5;

public class PokemonDefensivo extends Pokemon {

    private int resistencia;

    public PokemonDefensivo(int numeroPokedex, String nombre, Tipo tipo, int nivelActual,
                            Movimiento[] movimientos, int[][] aprendizaje, int resistencia) {
        super(numeroPokedex, nombre, tipo, nivelActual, movimientos, aprendizaje);
        this.resistencia = resistencia;
    }

    public int getResistencia() {
        return resistencia;
    }

    @Override
    public double calcularIndiceCombate() {
        return calcularPotenciaMediaDisponible() + resistencia * 1.5;
    }

    @Override
    public boolean necesitaMejorar() {
        return contarMovimientosDisponibles() == 0 || getNivelActual() < 20;
    }

    @Override
    public String toString() {
        return super.toString() + " | Clase: PokemonDefensivo | Resistencia: " + resistencia;
    }
}