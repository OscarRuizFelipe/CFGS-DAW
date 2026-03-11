package Producto;//excepcion personalizada para cuando el producto sea invalido

public class ProductoInvalidoException extends Exception{
	
	public ProductoInvalidoException(String mensaje) {
		super(mensaje);
	}

}
//creamos la excepcion dandole como parametros el string mensaje para despues en el super poder darle el mensaje que queramos por pantalla