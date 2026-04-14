package digimon;

public class DigimonAtaque extends Digimon {

    private int enemigosDerrotados;

    public DigimonAtaque(String id, String nombre, int[][] combates, int enemigosDerrotados) {
        super(id, nombre, TipoDigimon.VIRUS, combates);
        this.enemigosDerrotados = enemigosDerrotados;
    }

    @Override
    public double calcularPoder() {
        return calcularCombatesTotales() + enemigosDerrotados * 3;
    }

    @Override
    public boolean esElite() {
        return calcularPoder() >= 50;
    }

    @Override
    public String toString() {
        return super.toString() + ", Enemigos derrotados: " + enemigosDerrotados;
    }
}