package MiEntradaSalida;

import java.util.*;

public class mainEntrada {
	 public static void main(String[] args) {
		
		 Scanner teclado=new Scanner(System.in);
		 
		 
 int numeroEntero = MiEntradaSalida.solicitarEntero("introduce un numero entero",teclado);
		
         System.out.println("numero introducido: " + numeroEntero);
	 }
}
