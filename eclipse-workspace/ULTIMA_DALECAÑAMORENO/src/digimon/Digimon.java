package digimon;

/*
 * CLASE ABSTRACTA
 */
public abstract class Digimon implements Evaluable {

    protected String id;
    protected String nombre;
    protected TipoDigimon tipo;
    protected int[][] combates; // 5x2

    /*
     * Constructor CORRECTO (uso de this)
     */
    public Digimon(String id, String nombre, TipoDigimon tipo, int[][] combates) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.combates = combates;
    }

    /*
     * Calcula combates totales
     */
    public int calcularCombatesTotales() {
        int total = 0;

        for (int i = 0; i < combates.length; i++) {
            for (int j = 0; j < combates[i].length; j++) {
                total += combates[i][j];
            }
        }

        return total;
    }

    /*
     * Combates de un día
     */
    public int calcularCombatesDia(int dia) {
        return combates[dia][0] + combates[dia][1];
    }

    /*
     * Método abstracto obligatorio
     */
    public abstract double calcularPoder();

    /*
     * Mostrar matriz
     */
    public void mostrarCombates() {
        for (int i = 0; i < combates.length; i++) {
            System.out.println("Día " + i + ": " + combates[i][0] + " | " + combates[i][1]);
        }
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Nombre: " + nombre + ", Tipo: " + tipo;
    }
}