package arraiys;

public class DecirMayor {
	 public static void main(String[] args) {
		 
		 
		 int numeroGrande=0;
		 int[] numeros = {4, -3, 7, 0, -2, 8,9,-1,66,5};	
		 
		 for(int a=0; a<numeros.length;a++) {
 
			if(numeroGrande<numeros[a]) {
				
				numeroGrande=numeros[a];
			}
			
			 
			 
	 }
		 System.out.println(numeroGrande+" es el numero mas grande");
	
	 }
}
