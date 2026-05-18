package EXAMEN_ULTIMO;

public abstract class Robot implements Revisable {
	
	protected int codigo;
	protected String nombre;
	protected TipoModulo especialidad;
	protected int energiaActual;
	protected Modulo[] modulos;
	protected int[][] activacion;
	
	
	public Robot(int codigo, String nombre, TipoModulo especialidad, int energiaActual, Modulo[] modulos, int[][] activacion)
	{
		this.codigo=codigo;
		this.nombre=nombre;
		this.especialidad=especialidad;
		this.energiaActual=energiaActual;
		this.modulos=modulos;
		this.activacion=activacion;
		
	}
	
	public int contarModulosActivos() {
		int cuenta = 0;
		for (int i = 0; i < modulos.length;i++) {
			if(energiaActual >= activacion[i][0]) {
				cuenta++;
			}
			
		}
		return cuenta;
	}
	public double calcularPotenciaMediaActiva() {
		int activos = contarModulosActivos();
		if (activos == 0) return 0;
		
		double sumaPotencia =0;
		for(int i = 0; i < modulos.length;i++){
			if(energiaActual >= activacion[i][0]) {
				sumaPotencia += activacion[i][1];
			}
		}
		return sumaPotencia / activos;
		
	}
	
	public void mostrarModulosActivos() {
		
		for(int i = 0; i < modulos.length;i++){
			if(energiaActual >= activacion[i][0]) {
				System.out.println();
			}
		}
	}
}
