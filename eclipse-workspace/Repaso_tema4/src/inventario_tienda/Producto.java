package inventario_tienda;

import java.util.*;

// Clase Producto representa un producto de la tienda
// Implementa Comparable para poder ordenar productos por precio
public class Producto implements Comparable<Producto> {

    // =========================
    // Atributos privados
    // =========================
    private String nombre;   // Nombre del producto
    private double precio;   // Precio del producto
    private int stock;       // Cantidad disponible en inventario

    // =========================
    // Constructor de Producto
    // Valida que el precio y el stock sean correctos
    // =========================
    public Producto(String nombre, double precio, int stock) throws ProductoInvalidoException {

        // Validación del precio
        if (precio <= 0) {
            throw new ProductoInvalidoException("Precio no válido");
        }

        // Validación del stock
        if (stock < 0) {
            throw new ProductoInvalidoException("Stock no válido");
        }

        // Asignamos los valores a los atributos
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    // =========================
    // Getters
    // =========================
    public double getPrecio() {
        return precio; // Devuelve el precio del producto
    }

    public String getNombre() {
        return nombre; // Devuelve el nombre del producto
    }

    public int getStock() {
        return stock; // Devuelve la cantidad en stock
    }

    // =========================
    // Setters
    // =========================

    // Cambia el precio del producto con validación
    public void setPrecio(double precio) throws ProductoInvalidoException {
        if (precio <= 0) {
            throw new ProductoInvalidoException("El precio debe ser mayor que 0");
        }
        this.precio = precio;
    }

    // Cambia el stock del producto con validación
    public void setStock(int stock) throws ProductoInvalidoException {
        if (stock < 0) {
            throw new ProductoInvalidoException("El stock no puede ser negativo");
        }
        this.stock = stock;
    }

    // Cambia el nombre del producto (sin validación por ahora)
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // =========================
    // toString()
    // Devuelve una representación legible del producto
    // =========================
    @Override
    public String toString() {
        return nombre + " - $" + precio + " - Stock: " + stock;
    }

    // =========================
    // compareTo()
    // Permite ordenar productos por precio de menor a mayor
    // =========================
    @Override
    public int compareTo(Producto otro) {
        if (this.precio < otro.getPrecio()) {
            return -1; // este producto es más barato
        } else if (this.precio > otro.getPrecio()) {
            return 1;  // este producto es más caro
        } else {
            return 0;  // precios iguales
        }
    }
}