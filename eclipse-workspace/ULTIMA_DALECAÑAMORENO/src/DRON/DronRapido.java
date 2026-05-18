package DRON;

public class DronRapido extends Dron {

    private int velocidadExtra;

    public DronRapido(int id, String modelo, TipoDron tipo,
                      int nivelSistema, ModoVuelo[] modosVuelo,
                      int[][] rendimiento, int velocidadExtra) {
        super(id, modelo, tipo, nivelSistema, modosVuelo, rendimiento);
        this.velocidadExtra = velocidadExtra;
    }

    @Override
    public double calcularIndiceRendimiento() {
        return eficienciaMedia() + velocidadExtra * 2;
    }

    @Override
    public boolean necesitaRevision() {
        return contarModosDisponibles() < 2 ||
               eficienciaMedia() < 60;
    }
}