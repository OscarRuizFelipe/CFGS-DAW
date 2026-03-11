package arraiys;

import java.util.Arrays;

public class Alumno_main {
	
	public static void main(String[] args) {
		
		Alumno[] alumnos = new Alumno[3];
		
		alumnos[0]= new Alumno ("Oscar",9);
		alumnos[1]= new Alumno ("Raul",6.5);
		alumnos[2]= new Alumno("Pablo",7.7);
		
		Alumno mejorAlumno = alumnos[0];
		
		  for (int i = 0; i < alumnos.length; i++) {
	            alumnos[i].mostrarDatos();
	            
	            if(alumnos[i].getNota() > mejorAlumno.getNota()) {
	            	mejorAlumno = alumnos[i];
	            }
		  }	
		  

	        System.out.println("El alumno con mayor nota es:");
	        mejorAlumno.mostrarDatos();
	        
	        for(Alumno a : alumnos ) {
	        	
	        	a.setNota(a.getNota()+1);
	        	 a.mostrarDatos();
	        }
	        //sor es para descendentes
	        Arrays.sort(alumnos);
	        
	        System.out.println("Alumnos de menor a mayor nota:");
	        for(Alumno a : alumnos ) {
	        	a.mostrarDatos();
	        }
		  
	}

}
