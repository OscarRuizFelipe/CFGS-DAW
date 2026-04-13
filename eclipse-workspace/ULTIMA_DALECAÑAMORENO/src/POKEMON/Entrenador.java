package POKEMON;

public abstract class Entrenador implements Evaluable{
	
	private String id;
	private String nombre;
	private Rango rango;
	private int registroVictorias[][];
	
	public Entrenador(String id, String nombre, Rango rango, int[][] registroVictorias) {
		this.id=id;
		this.nombre=nombre;
		this.rango=rango;
		this.registroVictorias=registroVictorias;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public Rango getRango() {
		return this.rango;
	}
	
	public int[][] getRegistroVictorias() {
		return this.registroVictorias;
	}
	
	@Override
	
	public String toString() {
		return "ID: "+id+ " | Nombre: " +nombre+ " | Rango: " +rango;
	}
	
	public int calcularVictoriasSemanales() {
		
		int suma=0;
		
		for(int i=0; i<registroVictorias.length; i++) {
			for(int j=0; j<registroVictorias[i].length; j++) {
				suma=suma+registroVictorias[i][j];
			}
		}
		return suma;
	}
	
	public int calcularVictoriasDia(int dia) {
		
		int suma=0;
		
		for(int i=0; i<registroVictorias.length; i++) {
			
			if(dia==i) {
				
				for(int j=0; j<registroVictorias[i].length; j++) {
					suma=suma+registroVictorias[i][j];
				}
			}

		}
		return suma;
	}
	
	public abstract double calcularPuntuacion();
	
	public void mostrarRegistroCombates() {
		
		for(int i=0; i<registroVictorias.length; i++) {
			for(int j=0; j<registroVictorias[i].length; j++) {
				if(j==0)
					System.out.println("Dia " +(i+1)+ " - Individuales: " +registroVictorias[i][j]+ ", Dobles: " +registroVictorias[i][j+1]);
				}
		}
		
	}
	
}
