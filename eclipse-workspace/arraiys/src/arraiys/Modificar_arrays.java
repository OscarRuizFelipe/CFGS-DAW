package arraiys;

import java.util.Arrays;

public class Modificar_arrays {
	public static void main(String[] args) {
		
		int[] numeros = {1,2,3,4,5};
		
		
		
	    multiplicarPorDos(numeros);	
	}
		public static void multiplicarPorDos(int[] numeros) {
		
		
		 for(int a=0; a<numeros.length;a++) {
			 
			numeros[a]=numeros[a]*2;
			 
			 
		 }
		 System.out.println(Arrays.toString(numeros));		
		}
	


}