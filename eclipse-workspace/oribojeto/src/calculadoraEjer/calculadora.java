package calculadoraEjer;

import java.util.*;

public class calculadora {
	
	public static Scanner teclado=new Scanner(System.in);
	
	public static void main(String[]args) {
		
		int a=pedirEntero("introduce el primer numero entero", teclado);
		int b=pedirEntero("introduce el segudo numero entero", teclado);
		
		char op = leerOperacion(teclado);
		int resultado = calcular(a,b,op);
		
		System.out.println("tu resultado es: " + resultado);
		
		
	}
	public static int pedirEntero(String mensaje, Scanner teclado) {
		
		
		System.out.println(mensaje);
		
			while(!teclado.hasNextInt()) {
			
			System.out.println("introduzca un numero entero");
			teclado.nextLine();
			
		}
		
    return teclado.nextInt();
		
	}
	public static char leerOperacion(Scanner teclado) {
		
		char op;
		System.out.println("elija operacion: +,-,*,/.");
		op=teclado.next().charAt(0);
		
		while(op!='+' &&op!='*'&&op!='/'&&op!='-' ) {
			System.out.println("elija operacion correcta: +,-,*,/.");
			op=teclado.next().charAt(0);
			
		}
		
			
		
		return op;
		
	}
	public static int calcular(int a,int b,char op) {
	int	resultado = 0;
	switch(op) {
	
	case '/': 
		while (a==0 && op=='/') {
			a=pedirEntero("introduce el segudo numero entero", teclado);
	}
	resultado = a/b;
	break;
	
	case '+':
		resultado= a+b;
		break;
		
	case '-':
	resultado= a-b;
	break;
	
	case'*':
		resultado= a*b;
		break;
	}
	return resultado;
	}
	}

