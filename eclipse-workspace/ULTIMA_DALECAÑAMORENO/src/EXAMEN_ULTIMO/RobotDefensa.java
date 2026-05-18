package EXAMEN_ULTIMO;

public class RobotDefensa extends Robot {
	
	private int blindaje;
	
	public RobotDefensa(int codigo, String nombre, TipoModulo especialidad, int energiaActual, Modulo[] modulos,
			int[][] activacion) {
		
		this.blindaje=blindaje;
		
		super(codigo, nombre, especialidad, energiaActual, modulos, activacion);
	}
	@override
	public double calcularIndiceCombate(){
		return calcularPotenciaMediaActiva() + blindaje*1.5;
		
	}
	public boolean necesitaRevision() {
		return contarModulosActivos() == 0 || energiaActual < 30;
	}

}
