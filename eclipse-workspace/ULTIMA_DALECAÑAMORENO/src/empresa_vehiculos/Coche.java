package empresa_vehiculos;

/*
 * CLASE Coche
 * -----------
 * Hereda de Vehiculo (extends)
 * Representa un tipo concreto de vehículo
 */
public class Coche extends Vehiculo {

    // Atributo propio de coche
    private int viajes;

    /*
     * Constructor
     * Llama al constructor de la clase padre (super)
     */
    public Coche(String matricula, String modelo, double[][] kilometros, int viajes) {
        super(matricula, modelo, TipoVehiculo.COCHE, kilometros);
        this.viajes = viajes;
    }

    /*
     * Calcula el uso del coche
     * Fórmula: km totales + viajes * 5
     */
    @Override
    public double calcularUso() {
        return calcularKmTotales() + viajes * 5;
    }

    /*
     * Indica si necesita revisión
     * Si el uso es mayor o igual a 300
     */
    @Override
    public boolean necesitaRevision() {
        return calcularUso() >= 300;
    }

    /*
     * Muestra información completa del coche
     */
    @Override
    public String toString() {
        return super.toString() + ", Viajes: " + viajes;
    }
}