package empresa_vehiculos;

/*
 * CLASE Moto
 * ----------
 * Hereda de Vehiculo
 */
public class Moto extends Vehiculo {

    // Atributo propio
    private int entregas;

    public Moto(String matricula, String modelo, double[][] kilometros, int entregas) {
        super(matricula, modelo, TipoVehiculo.MOTO, kilometros);
        this.entregas = entregas;
    }

    /*
     * Fórmula de uso:
     * km totales + entregas * 2
     */
    @Override
    public double calcularUso() {
        return calcularKmTotales() + entregas * 2;
    }

    /*
     * Necesita revisión si uso >= 200
     */
    @Override
    public boolean necesitaRevision() {
        return calcularUso() >= 200;
    }

    @Override
    public String toString() {
        return super.toString() + ", Entregas: " + entregas;
    }
}