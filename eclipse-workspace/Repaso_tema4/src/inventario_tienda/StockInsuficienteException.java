package inventario_tienda;

public class StockInsuficienteException extends Exception  {

	public StockInsuficienteException(String mensaje) {
		
		super(mensaje);
	}
	
}
