package POKEMON;

public class EntrenadorEstratega extends Entrenador{
	
	private int medallasConseguidas;
	
	public EntrenadorEstratega(String id, String nombre, Rango rango, int[][] registroVictorias, int medallasConseguidas) {
		super(id, nombre, rango, registroVictorias);
		this.medallasConseguidas=medallasConseguidas;
	}
	
	@Override
	public String toString() {
		return super.toString()+" | Medallas: "+medallasConseguidas;
	}
	
	public double calcularPuntuacion() {
		return (calcularVictoriasSemanales()*1.5)+(medallasConseguidas*10);
	}
	
	public boolean esAptoParaLiga() {
		return calcularPuntuacion()>=100;
	}
	
}
