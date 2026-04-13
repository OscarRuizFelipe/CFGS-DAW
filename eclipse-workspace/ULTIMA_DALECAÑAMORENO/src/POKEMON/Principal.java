package POKEMON;
import java.util.*;

public class Principal {
	
	public static Scanner teclado=new Scanner(System.in);
	
	public static void main(String[] args) {
		
		Entrenador entrenador[]= new Entrenador[4];
		int[][] victoriasAsh= {
			{3,1},
			{4,0},
			{2,3},
			{1,1},
			{3,4},
		};
		int[][] victoriasRed= {
				{5,2},
				{1,3},
				{2,2},
				{5,5},
				{4,2},
			};
		int[][] victoriasGary= {
				{2,1},
				{3,3},
				{2,1},
				{1,5},
				{6,2},
			};
		int[][] victoriasOak= {
				{3,3},
				{4,0},
				{5,3},
				{1,0},
				{2,0},
			};
		entrenador[0] = new EntrenadorEstratega("001", "Ash", Rango.NOVATO, victoriasAsh, 3);
		entrenador[1] = new EntrenadorEstratega("002", "Red", Rango.CAMPEON, victoriasRed, 8);
		entrenador[2] = new EntrenadorEstratega("003", "Gary", Rango.ALTO_MANDO, victoriasGary, 7);
		entrenador[3] = new EntrenadorCriador("004", "Oak", Rango.LIDER_GIMNASIO, victoriasOak, 10);
		
		for(int i=0; i<entrenador.length; i++) {
			System.out.println(entrenador[i]);
			entrenador[i].mostrarRegistroCombates();
			System.out.println("Victorias Semanales: "+entrenador[i].calcularVictoriasSemanales());
			System.out.println("Puntuación Final: "+entrenador[i].calcularPuntuacion());
			System.out.println("¿Es apto para la Liga Pokémon?: "+entrenador[i].esAptoParaLiga());
			System.out.println();
		}
		
		double mayor=entrenador[0].calcularPuntuacion();
		int indice=0;
		
		for(int i=0; i<entrenador.length; i++) {
			if(mayor<entrenador[i].calcularPuntuacion()) {
				mayor=entrenador[i].calcularPuntuacion();
				indice=i;
			}
		}
		
		System.out.println("ENTRENADOR CON MAYOR PUNTUACIÓN: " +entrenador[indice].getNombre());
		
		int contEstratega=0;
		int contCriador=0;
		
		for(int i=0; i<entrenador.length; i++) {
			if(entrenador[i] instanceof EntrenadorCriador) {
				contCriador++;
			} else if(entrenador[i] instanceof EntrenadorEstratega) {
				contEstratega++;
			}
		}
		
		System.out.println("Nº DE ESTRATEGAS: " +contEstratega);
		System.out.println("Nº DE CRIADORES: "+contCriador);
		
		double mediaInd=0;
		double mediaDob=0;
		
		for(int i=0; i<entrenador.length; i++) {
			for(int j=0; j<entrenador[i].getRegistroVictorias().length; j++) {
				mediaInd+=entrenador[i].getRegistroVictorias()[j][0];
				mediaDob+=entrenador[i].getRegistroVictorias()[j][1];
			}
		}
		
		System.out.println("Media de Victorias en Individual: " +(mediaInd/20));
		System.out.println("Media de Victorias en Doble: " +(mediaDob/20));
		
		double mayorDia=entrenador[0].calcularVictoriasDia(0);
		int indiceMayor=0;
		
		for(int i=0; i<entrenador.length; i++) {
			for(int j=0; j<entrenador[i].getRegistroVictorias().length; j++) {
				if(mayorDia<entrenador[i].calcularVictoriasDia(j)) {
					mayorDia=entrenador[i].calcularVictoriasDia(j);
					indiceMayor=i;
				}
			}
		}
		
		System.out.println("ENTRENADOR CON MÁS VICTORIAS EN UN DÍA: " +entrenador[indiceMayor].getNombre()+ " CON "+mayorDia+ " VICTORIAS");
		
	}

}
