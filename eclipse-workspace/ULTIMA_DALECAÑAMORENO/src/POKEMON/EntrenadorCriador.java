package POKEMON;

public class EntrenadorCriador extends Entrenador{
	
	private int huevosEclosionados;
	
	public EntrenadorCriador(String id, String nombre, Rango rango, int[][] registroVictorias, int huevosEclosionados) {
		super(id, nombre, rango, registroVictorias);
		this.huevosEclosionados=huevosEclosionados;
	}
	
	@Override
	public String toString () {
		return super.toString()+" | Huevos: "+huevosEclosionados;
	}
	
	public double calcularPuntuacion() {
		return (calcularVictoriasSemanales()*1.0)+(huevosEclosionados*5);
	}
	
	public boolean esAptoParaLiga() {
		return calcularPuntuacion()>=80;
	}

}
