package arraiys;

import java.util.Scanner;

public class contarPositivos {
	 public static void main(String[] args) {
		 
			Scanner teclado=new Scanner(System.in);
		 boolean flag = false;
		 int numeroBuscar;
		 int numerosPositivos=0;
	int[] numeros = {4, -3, 7, 0, -2, 8};	

   System.out.println("¿que numero deseas buscar?");
   numeroBuscar=teclado.nextInt();
	
	
	for(int a=0; a<numeros.length;a++) {
		
	    if (numeros[a]>-1) {
	    	numerosPositivos++;	
	    	
	    }
	    if(numeros[a]==numeroBuscar) {
	    	System.out.println(numeroBuscar+" si esta en el array y esta en la posicion " +(a+1));	
	    	flag=true;
	    }
  
    	  
      }
    if(flag==false) {
  	  System.out.println("no esta el numero pedido");
		}
	
	
	System.out.println("hay "+ numerosPositivos+ " numeros positivos");
	
}
}