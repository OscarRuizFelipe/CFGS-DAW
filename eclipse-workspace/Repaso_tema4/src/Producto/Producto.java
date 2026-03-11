package Producto;

public class Producto implements Comparable<Producto>{//implements para poder usa el compare to
	
	private String nombre;//iniciamos las variables en privado 
	private double precio;
	private int stock;
	
	public Producto(String nombre,double precio,int stock)throws ProductoInvalidoException{//hacemos nuestro molde y  le pasamos por parametros las variables iniciadas en privado
		
		if(precio <=0) {//si el precio es menor o = a 0 salta esto 
			throw new ProductoInvalidoException("El precio tiene que ser mayor que 0");//excepcion para el precio
			
		}
		if(stock<0) {//si es menor a 0 el stock salta la excepcion
			throw new ProductoInvalidoException("El stock no puede ser menor que 0");//otra para el stock
		}
		this.nombre=nombre;
		this.precio=precio;
		this.stock=stock;	//asignamos valores a los atributos
	}
	public double getPrecio() {//hacemos los getters para porder obtener los valores
		return precio;
	}
	public String getNombre() {
		return nombre;
	}
	public int stock() {
		return stock;
	}
	public void setPrecio(double precio)throws ProductoInvalidoException{//y los set para poder cambiar e asignar valores
		if(precio<=0) {
			throw new ProductoInvalidoException("El precio del producto tiene que ser mayor a 0");	//excepcion tambien por si coge los valores invalidos
		}
        this.precio=precio;		
		
	}
	public void setNombre(String nombre) {
		this.nombre=nombre;//el nombre no necesita validacion (al menos no la pide)
	}
	public void setStock(int stock)throws ProductoInvalidoException {//aqui igual
		if(stock<0) {
			throw new ProductoInvalidoException("El stock no puede ser negativo");
		}
	    this.stock=stock;
	}
	@Override
	public String toString() {//el to string para obtener por pantalla el arrys
        return nombre + " - $" + precio + " - Stock: " + stock;
    }
	public int compareTo(Producto otro) {// y el compare to
		
		if (this.precio < otro.getPrecio()) {
            return -1; // este producto es más barato
        } else if (this.precio > otro.getPrecio()) {
            return 1;  // este producto es más caro
        } else {
            return 0;  // precios iguales
        }
	}
	public void ReducirStock() {//aqui basicaente le quitamos 2 de stock a los que tienen 2 o mas stock
        if (stock > 1) {
            stock=stock-2;
            if (stock ==1) {
                stock=1;
            }
            if(stock==0) {
            	stock=0;
            }
        }
    }

}
