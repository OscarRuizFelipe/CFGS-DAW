package arraiys;

import java.util.*;

public class comparar_array {
	public static void main(String[] args) {
		
		int[] array = {1,2,3,4,5};
		int[] array2 = {1,2,3,4,5};
		
		if(Arrays.equals(array,array2)) {
			
			System.out.println("son iguales con ARRAYS.EQUALS");//aqui si da iguales porque estamos comparando el contenido 
		}
		
		if(array.equals(array2)) {
			
			
			System.out.println("son iguales");//aqui estamos comparando la memoria por eso no va a funcionar
			
		}
			
		
		
		
	}

}
