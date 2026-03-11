package MiEntradaSalida;

import java.util.Scanner;
import java.util.InputMismatchException;

public class MiEntradaSalida {
	
	public static Scanner teclado=new Scanner(System.in);
	
	int entero=solicitarEntero("introduce un numero entero",teclado);
	
	
	
	public static int solicitarEntero(String mensaje,Scanner teclado) {
		
		int enteroSolicitado = 0;
		
		boolean correcto = false;
		
		while(!correcto) {
			
		
		try {
		System.out.println(mensaje);
		enteroSolicitado=teclado.nextInt();
		correcto=true;}
		
		catch(InputMismatchException e) {
			
			System.out.println("Error: debes introducir un numero entero.");
	        teclado.nextLine();
			
		}
		}	
		return enteroSolicitado;
	}
	
	public static int solicitarEnteroPositivo  (String mensaje,Scanner teclado)throws tieneQueSerPositivo() {
		
		int EnteroPositivo = 0;
		
		boolean correcto = false;
		
		while(!correcto) {
			
			try {
				System.out.println(mensaje);
				EnteroPositivo=teclado.nextInt();
				
				if(EnteroPositivo<0) {
 				 throw new tieneQueSerPositivo();     }
					
				correcto=true;}
			catch(InputMismatchException er) {
				
				
				
			}
			
			
			
			
		}
	}
	

}
