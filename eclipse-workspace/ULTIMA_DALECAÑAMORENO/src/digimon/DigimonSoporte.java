package digimon;

public class DigimonSoporte extends Digimon {

    private int aliadosCurados;

    public DigimonSoporte(String id, String nombre, int[][] combates, int aliadosCurados) {
        super(id, nombre, TipoDigimon.VACUNA, combates);
        this.aliadosCurados = aliadosCurados;
    }

    @Override
    public double calcularPoder() {
        return calcularCombatesTotales() + aliadosCurados * 1.5;
    }

    @Override
    public boolean esElite() {
        return calcularPoder() >= 40;
    }

    @Override
    public String toString() {
        return super.toString() + ", Aliados curados: " + aliadosCurados;
    }
}