package DRON;

public class DronResistente extends Dron {

    private int autonomia;

    public DronResistente(int id, String modelo, TipoDron tipo,
                          int nivelSistema, ModoVuelo[] modosVuelo,
                          int[][] rendimiento, int autonomia) {
        super(id, modelo, tipo, nivelSistema, modosVuelo, rendimiento);
        this.autonomia = autonomia;
    }

    @Override
    public double calcularIndiceRendimiento() {
        return eficienciaMedia() + autonomia * 1.5;
    }

    @Override
    public boolean necesitaRevision() {
        return contarModosDisponibles() == 0 ||
               nivelSistema < 15;
    }
}