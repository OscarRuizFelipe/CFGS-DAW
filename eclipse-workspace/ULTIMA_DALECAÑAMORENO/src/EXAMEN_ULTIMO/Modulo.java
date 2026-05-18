package EXAMEN_ULTIMO;


public class Modulo {
	
	private String nombre;
	private TipoModulo tipo;
	private int potencia;
	
	public Modulo() {
		
		this.nombre=nombre
		this.tipo=tipo;
		this.potencia=potencia;
		
	}
	
	  public String getnombre() {
		  return nombre;
	  }
	  public TipoModulo getTipo() {
		  return tipo;
	  }
	  public int getPotencia() {
		  return potencia;
	  }
	 
	    public String toString() {
	        return "nombre: "+nombre+" tipo: "+tipo +" potencia: "+potencia;
	    }	
	
	
}