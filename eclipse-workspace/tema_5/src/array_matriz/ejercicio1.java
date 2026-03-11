package array_matriz;

import java.util.*;

public class ejercicio1 {
	public static void main(String[] args) {

	
	

	
	
	double[][] notas = new double [30][5];
	
	String[] Asignaturas = {"programacion","BD","LMSGI","SISTEMAS","ENTORNOS"};
	
	for(int i=0;i<notas.length;i++){

	    for(int j=0;j<notas[i].length;j++){

	        notas[i][j] = Math.random()*10;

	    }

	}
	int susp0=0; 
	int susp1=0;
	int susp2=0; 
	int susp3=0;
	int susp4=0; 
	int susp5=0;
	
	for (int i = 0; i < notas.length; i++) {
		int suspensos = 0;
		for(int j = 0; j < notas[i].length; j++) {
			if (notas[i][j]  < 5) {
				suspensos++;
			}
			
		}
	
	switch(suspensos) {
	
	case 0:
		susp0++;
	break;
	
	case 1:
		susp1++;
		break;
	
	case 2:
		susp2++;
		break;
	
	case 3:
		susp3++;
		break;
		
	case 4:
		susp4++;
		break;
		
	case 5:
		susp5++;
		break;
	}
	}	
	System.out.println("Alumnos con 5 suspensos: " + susp5);
	System.out.println("Alumnos con 4 suspensos: " + susp4);
	System.out.println("Alumnos con 3 suspensos: " + susp3);
	System.out.println("Alumnos con 2 suspensos: " + susp2);
	System.out.println("Alumnos con 1 suspenso: " + susp1);
	System.out.println("Alumnos con 0 suspensos: " + susp0);
	
	for(int j=0;j<notas[0].length;j++){
	    double suma = 0;
	    for(int i=0;i<notas.length;i++){
	        suma += notas[i][j];
	    }
	    double media = suma / notas.length;
	    System.out.printf("La nota media de %s es %.2f%n", Asignaturas[j], media);
	}
     }
	}																																																									
