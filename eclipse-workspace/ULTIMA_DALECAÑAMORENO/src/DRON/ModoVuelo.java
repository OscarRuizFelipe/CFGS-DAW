package DRON;

public class ModoVuelo {

    private String nombre;
    private TipoDron tipo;
    private int eficiencia;

    public ModoVuelo(String nombre, TipoDron tipo, int eficiencia) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.eficiencia = eficiencia;
    }

    public String getNombre() {
        return nombre;
    }

    public TipoDron getTipo() {
        return tipo;
    }

    public int getEficiencia() {
        return eficiencia;
    }

    @Override
    public String toString() {
        return nombre + " (" + tipo + ") eficiencia: " + eficiencia;
    }
}