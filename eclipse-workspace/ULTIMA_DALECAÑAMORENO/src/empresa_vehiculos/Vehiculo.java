package empresa_vehiculos;


/*
 * CLASE ABSTRACTA Vehiculo
 * ------------------------
 * No se pueden crear objetos directamente de esta clase.
 * Sirve como base para Coche y Moto.
 * 
 * Implementa la interfaz Revisable, por lo que las subclases
 * deberán implementar necesitaRevision().
 */
public abstract class Vehiculo implements Revisable {

    // Atributos comunes a todos los vehículos
    protected String matricula;
    protected String modelo;
    protected TipoVehiculo tipo;

    /*
     * Matriz de kilómetros:
     * 7 filas (días de la semana)
     * 1 columna (km por día)
     */
    protected double[][] kilometros;

    /*
     * Constructor
     * Inicializa todos los atributos del vehículo
     */
    public Vehiculo(String matricula, String modelo, TipoVehiculo tipo, double[][] kilometros) {
        this.matricula = matricula;
        this.modelo = modelo;
        this.tipo = tipo;
        this.kilometros = kilometros;
    }

    // Getters
    public String getMatricula() {
        return matricula;
    }

    public String getModelo() {
        return modelo;
    }

    public TipoVehiculo getTipo() {
        return tipo;
    }

    /*
     * Calcula los km totales de la semana
     * Recorre la matriz sumando todos los valores
     */
    public double calcularKmTotales() {
        double total = 0;

        for (int i = 0; i < kilometros.length; i++) {
            total += kilometros[i][0];
        }

        return total;
    }

    /*
     * Devuelve los km de un día concreto
     * dia = índice (0 = lunes, 6 = domingo)
     */
    public double calcularKmDia(int dia) {
        return kilometros[dia][0];
    }

    /*
     * Método abstracto:
     * cada tipo de vehículo calcula el uso de forma distinta
     */
    public abstract double calcularUso();

    /*
     * Muestra todos los kilómetros de la semana
     */
    public void mostrarKilometros() {
        System.out.println("Kilómetros por día:");

        for (int i = 0; i < kilometros.length; i++) {
            System.out.println("Día " + i + ": " + kilometros[i][0]);
        }
    }

    /*
     * Método toString:
     * muestra la información básica del vehículo
     */
    @Override
    public String toString() {
        return "Matricula: " + matricula +
               ", Modelo: " + modelo +
               ", Tipo: " + tipo;
    }
}