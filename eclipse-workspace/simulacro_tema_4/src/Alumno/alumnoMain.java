package Alumno;

import java.util.*;


public class alumnoMain {
	public static void main(String[] args) {
		 Scanner teclado = new Scanner(System.in);
		
		Alumno[] alumnos = new Alumno[5];
		
		
		Alumno alumno1 = null; 
		alumno1.pedirNombre();
		alumno1.pedirNota();
		
		
		alumnos[0]= alumno1;
				
		
	}

}
