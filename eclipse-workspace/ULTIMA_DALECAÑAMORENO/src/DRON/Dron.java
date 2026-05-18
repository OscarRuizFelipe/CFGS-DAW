package DRON;

public abstract class Dron implements Mantenible {

    protected int id;
    protected String modelo;
    protected TipoDron tipo;
    protected int nivelSistema;
    protected ModoVuelo[] modosVuelo;
    protected int[][] rendimiento;

    public Dron(int id, String modelo, TipoDron tipo,
                int nivelSistema, ModoVuelo[] modosVuelo, int[][] rendimiento) {
        this.id = id;
        this.modelo = modelo;
        this.tipo = tipo;
        this.nivelSistema = nivelSistema;
        this.modosVuelo = modosVuelo;
        this.rendimiento = rendimiento;
    }

    public int getNivelSistema() {
        return nivelSistema;
    }

    public String getModelo() {
        return modelo;
    }

    public ModoVuelo[] getModosVuelo() {
        return modosVuelo;
    }

    public int[][] getRendimiento() {
        return rendimiento;
    }

    // a) modos disponibles
    public int contarModosDisponibles() {
        int cont = 0;

        for (int i = 0; i < modosVuelo.length; i++) {
            if (rendimiento[i][0] <= nivelSistema) {
                cont++;
            }
        }
        return cont;
    }

    // b) eficiencia media
    public double eficienciaMedia() {
        int suma = 0;
        int cont = 0;

        for (int i = 0; i < modosVuelo.length; i++) {
            if (rendimiento[i][0] <= nivelSistema) {
                suma += rendimiento[i][1];
                cont++;
            }
        }

        if (cont == 0) return 0;

        return (double) suma / cont;
    }

    // c) mostrar disponibles
    public void mostrarModosDisponibles() {
        for (int i = 0; i < modosVuelo.length; i++) {
            if (rendimiento[i][0] <= nivelSistema) {
                System.out.println(modosVuelo[i]);
            }
        }
    }

    // d)
    public abstract double calcularIndiceRendimiento();

    @Override
    public String toString() {
        return "Dron: " + modelo +
                " | Tipo: " + tipo +
                " | Nivel: " + nivelSistema;
    }
}