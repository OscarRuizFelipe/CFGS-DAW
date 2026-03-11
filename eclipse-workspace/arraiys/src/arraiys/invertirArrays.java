package arraiys;

import java.util.Arrays;

public class invertirArrays {
	 public static void main(String[] args) {
		 
	 
	
	int[] numeros = {1,2,3,4,5};
	int[] invertido;
	
	int j=0;
	invertido=new int[numeros.length];
	
	 for(int a=(numeros.length-1); a>=0;a--) {
		 
		 invertido[j]=numeros[a];
		 j++;
	 }
	 
	 System.out.println(Arrays.toString(invertido));

}
}