package arraiys;

public class Alumno implements Comparable<Alumno> {
	
	private String  nombre;
	private double nota;
	
	public Alumno(String nombre,double nota) {
		
		this.nombre=nombre;
		this.nota=nota;
		
	}
	 public String getNombre() {
	        return nombre;
	    }

	    public double getNota() {
	        return nota;
	    }
	    
	 public void mostrarDatos() {
		 
		 System.out.println("Nombre: " + nombre + " | Nota: " + nota);
	 }

	 public void setNota(double nota) {
		 
		 this.nota=nota;
	 }
	 @Override
	 public int compareTo(Alumno o) {
		//orden descendente 
		 return Double.compare(this.nota, o.nota);
	 }
	 
	 
}
