package cuentaBancariaGod;

import java.util.*;


public class cuntaBancaria {
	
	public static Scanner teclado=new Scanner(System.in);
	
	private  String titular;
	private  double saldo;
	
	
	public cuntaBancaria(String titular,double saldo) {
		
		try {
					
		this.saldo=saldo;
		if(saldo<0) {
			throw new IllegalArgumentException();	
		}}
		catch(IllegalArgumentException exception) {
			
			
			saldo=0;	
		}
		this.titular=titular;
	}
	
	public  double ingresar() {
		
		double cantidad = 0;
		
		
		System.out.println("cantidad que desea ingresar:");
	    
		try {
		cantidad=teclado.nextDouble();
		if(cantidad >0) {
			
			throw new  IllegalArgumentException();
		}}
		catch(IllegalArgumentException a) {
			
			cantidad = 0;
		}
		finally {
			this.saldo+=cantidad;
		}
		return this.saldo;
		}
	 public double retirar() {
		    System.out.println("Cantidad a retirar:");
		    double cantidad=0;

		    try {
		    	cantidad = teclado.nextDouble();
		        if (cantidad > this.saldo) {
		            throw new SaldoInsuficienteException();
		        }
		     
		    } catch (SaldoInsuficienteException e) {	
		        System.out.println("Saldo insuficiente. No se pudo retirar.");
		    }
         finally {
        	 this.saldo -= cantidad;
         }
		    try {
		    if(cantidad <=0) {
				
				throw new  IllegalArgumentException();
			}}
			catch(IllegalArgumentException a) {
				
				cantidad = 0;
			}
		    return this.saldo;
		}
	 public double getSaldo() {
			return this.saldo;
		}

		
		public String toString() {
			return "Titular: " + titular + ", Saldo: " + this.saldo;
		}
		
				 
			 
		 }
		 
	 
	
	
	
	
		
		
	

