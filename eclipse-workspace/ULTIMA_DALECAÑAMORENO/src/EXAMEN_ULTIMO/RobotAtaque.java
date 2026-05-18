package EXAMEN_ULTIMO;

public class RobotAtaque extends Robot{
	
	private int armasPesadas;
	
	
	
	
	
	public RobotAtaque(int codigo, String nombre, TipoModulo especialidad, int energiaActual, Modulo[] modulos,
			int[][] activacion) {
		
		
		super(codigo, nombre, especialidad, energiaActual, modulos, activacion);
		this.armasPesadas=armasPesadas;
	}
	private int calcularArmasPesadas() {
		
		int count=0;
		for (Modulo m: modulos) {
			if(m.getTipo()== TipoModulo.ARMA && m.getPotencia() > 70) count++;
		}
		return count;
	}

 	public double calcularIndiceCombate() {
 		return calcularPotenciaMediaActiva() + (armasPesadas*2.5);
 	}


	public boolean necesitaRevision() {
				return contarModulosActivos() <2|| calcularPotenciaMediaActiva() < 50;
	}


	}


