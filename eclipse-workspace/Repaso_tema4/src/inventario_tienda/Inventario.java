package inventario_tienda;

import java.util.Arrays;

public class Inventario {

    // Array estático para almacenar hasta 10 productos
    private Producto[] productos = new Producto[10]; 

    // Contador de cuántos productos hay realmente en el inventario
    private int contador = 0; 

    // =========================
    // Método para agregar producto al inventario
    // =========================
    public void agregarProducto(Producto p) {
        // Verifica si ya está lleno el array
        if (contador >= productos.length) {
            System.out.println("Inventario lleno, no se puede agregar el producto.");
            return; // sale del método si no hay espacio
        }
        // Si hay espacio, agrega el producto en la posición contador
        productos[contador] = p;
        // Incrementa el contador para la próxima posición
        contador++;
        System.out.println("Producto agregado correctamente.");
    }

    // =========================
    // Método para listar todos los productos
    // =========================
    public void listarProductos() {
        // Si no hay productos, avisa al usuario
        if (contador == 0) {
            System.out.println("No hay productos en el inventario.");
            return;
        }
        // Recorre solo hasta el contador (productos válidos)
        for (int i = 0; i < contador; i++) {
            // Imprime cada producto usando su toString()
            System.out.println(productos[i]);
        }
    }

    // =========================
    // Método para ordenar los productos por precio
    // =========================
    public void ordenarPorPrecio() {
        // Si hay 0 o 1 producto, no hace falta ordenar
        if (contador <= 1) return;
        // Ordena el array usando compareTo de Producto (por precio)
        Arrays.sort(productos, 0, contador);
        System.out.println("Productos ordenados por precio.");
    }

    // =========================
    // Método para obtener el producto más caro
    // =========================
    public Producto productoMasCaro() {
        // Si no hay productos, devuelve null
        if (contador == 0) return null;

        // Empieza considerando el primer producto como el más caro
        Producto masCaro = productos[0];

        // Recorre el array comparando precios
        for (int i = 1; i < contador; i++) {
            if (productos[i].getPrecio() > masCaro.getPrecio()) {
                masCaro = productos[i]; // actualiza si encuentra uno más caro
            }
        }

        return masCaro; // devuelve el producto más caro
    }

    // =========================
    // Método para vender un producto
    // =========================
    public void venderProducto(String nombre, int cantidad) 
            throws StockInsuficienteException, ProductoInvalidoException {

        boolean encontrado = false; // bandera para saber si encontramos el producto

        // Recorremos el inventario
        for (int i = 0; i < contador; i++) {
            // Compara el nombre ignorando mayúsculas/minúsculas
            if (productos[i].getNombre().equalsIgnoreCase(nombre)) {
                encontrado = true;

                // Verifica si hay suficiente stock
                if (cantidad > productos[i].getStock()) {
                    throw new StockInsuficienteException(
                        "No hay suficiente stock de " + nombre
                    );
                }

                // Reduce el stock usando el setter
                productos[i].setStock(productos[i].getStock() - cantidad);
                System.out.println("Se vendieron " + cantidad + " unidades de " + nombre);
                break; // ya encontramos y vendimos, salimos del bucle
            }
        }

        // Si no se encontró el producto
        if (!encontrado) {
            System.out.println("Producto no encontrado: " + nombre);
        }
    }

}