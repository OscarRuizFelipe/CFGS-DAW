package arraiys;

public class contarParesImpares {
	public static void main(String[] args) {
		
		 int contarPar=0;
		 int contarImpar=0;
		 int[] numeros = {4, -3, 7, 9, -2, 8,9,-1,66,5};	
		 
		 for(int a=0; a<numeros.length;a++) {
			 
			 if(numeros[a] % 2==0) {
				 
				 contarPar++;
			 }
			 else {
				 contarImpar++;
			 }
			 
		 }
		System.out.println("hay "+contarPar+ " numeros pares y "+contarImpar+ " numeros impares");
		
	}

}
