package Alumno;

import java.util.Scanner;

public class Alumno {
	
	private String  nombre;
	private double nota;
	
	 Scanner teclado = new Scanner(System.in);
	public Alumno(String nombre,double nota) throws NotaInvalidaException {
		
		this.nombre=nombre;
		if (nota <0 || nota>10) {
			throw new NotaInvalidaException();
		}
		else {
			this.nota=nota;
		}
			
			
		}
		 public String getNombre() {
		        return nombre;
		    }
		 
		  public double getNota() {
		        return nota;
		    }
		  public String toString() {
			  
			  return "nombre: " +nombre+ " nota : "+nota;
			  
			  
		  }
		  public String pedirNombre(){
			  
			  nombre="";
			  
			  System.out.print("nombre del alumno");
			  teclado.next();
			  return nombre;
			  
			  
		  }
		  public double pedirNota() {
			  int n=0;
			  
			  System.out.print("nota del alumno");
			  teclado.nextInt();
			  return n;
		  }
	        
}