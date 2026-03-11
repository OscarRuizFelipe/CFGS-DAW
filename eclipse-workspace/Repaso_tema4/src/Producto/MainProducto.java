package Producto;

import java.util.Arrays;
import java.util.Scanner;

import Alumno.Alumno;



public class MainProducto {
	 public static void main(String[] args) {
		 
		   Scanner teclado = new Scanner(System.in);
	        Producto[] producto = new Producto[5];//creamos arrys con 5 productos
	        
	        for(int i =0;i < producto.length;i++) {//for  para asignarle todos a cada producto
	        	
	        	System.out.println("Producto " + (i + 1));//mostar el numero del producto 
	        	
	        	System.out.print("Nombre: ");//poner cada nombre
	        	String nombre = teclado.nextLine();
	        	
	        	boolean  PrecioCorrecto = false;//boleano para que nos valide el precie en el while
	        	
	        	while(!PrecioCorrecto) {//aqui el while que negando el boolean accedemos a el 
	        		
	        		try {//try para el precio para el posible error
	        			System.out.print("precio: ");
	        			double precio = Double.parseDouble(teclado.nextLine());//asigamos el precio
	        			
	        			producto[i] = new Producto(nombre,precio, i);//le asgnamos el los valores a cada producto
	        			PrecioCorrecto = true;//ya echo eso salimos del bucle
	        		}
	        		catch(ProductoInvalidoException e) {//capturamos la excepcion y enbiamos el mensaje y gracias al bulce se vuelve a pedir elprecio
	        			System.out.println("error: "+ e.getMessage());	//enseñamos el mensaje por pantalla del error        			
	        		}
	        	 catch (NumberFormatException e) {//aqui por otra posible exepcion
                    System.out.println("Debes introducir un número válido.");
                }
	        	}
	        	boolean stockCorrecto = false;//aqui basicamente hacemos lo mismo que arriba pero con el stock
	        	
	        	while(!stockCorrecto) {
	        		try {
	        			System.out.print("stock: ");
	        			int stock = teclado.nextInt();
	        			
	        			producto[i] = new Producto(nombre,stock, i);
	        			stockCorrecto = true;
	        		}
	        		catch(ProductoInvalidoException e) {
	        			System.out.println("error: "+ e.getMessage());	        			
	        		}
	        		
	        		
	        	}
	        }
	     //  Mostrar lista original
	        System.out.println("\nLista original:");
	        for (Producto a : producto) {//enseñamos la lista con el for each 
	            System.out.println(a);
	        }
	        // Ordenar por precio
	        Arrays.sort(producto);//aqui lo ordenamos con el sort gracias al compare hecho anteriormente 
	        //quitar 2 stock
	  
	        for (Producto a : producto) {
	            a.ReducirStock();//llamamos el metodo
	        }//enseñamos el de mayor precio
	        System.out.println("\nMayor precio:");
	        System.out.println(producto[producto.length - 1]);
	        //lista final de los productos
	        
	        for (Producto a : producto) {
	        	 System.out.println(a);
	        }
	 }

}
