package cuentaBancariaGod;

public class CuentaMain {
	public static void main(String[] args) {
	   
		
	    cuntaBancaria cuenta = new cuntaBancaria("Juan Pérez", 500);
	    System.out.println("Cuenta creada: " + cuenta);

	   
	    try {
	        System.out.println("\nIntentando ingresar dinero:");
	        cuenta.ingresar();
	    } catch (Exception e) {
	        System.out.println("Error al ingresar: " + e.getMessage());
	    } finally {
	        System.out.println("Saldo actual tras ingreso: " + cuenta.getSaldo());
	    }


	    try {
	        System.out.println("\nIntentando retirar dinero:");
	        cuenta.retirar();
	    } catch (Exception e) {
	        System.out.println("Error al retirar: " + e.getMessage());
	    } finally {
	        System.out.println("Saldo actual tras retiro: " + cuenta.getSaldo());
	    }

	    
	    try {
	        System.out.println("\nIntentando retirar más de lo disponible:");
	        cuenta.retirar();
	    } catch (Exception e) {
	        System.out.println("Error al retirar: " + e.getMessage());
	    } finally {
	        System.out.println("Saldo final: " + cuenta.getSaldo());
	    }

	  
	    System.out.println("\nEstado final de la cuenta: " + cuenta);
	}

}
