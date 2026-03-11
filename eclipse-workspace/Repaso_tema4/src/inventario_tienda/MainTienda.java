package inventario_tienda;

import java.util.Scanner;

public class MainTienda {

    public static void main(String[] args) {

        // Scanner para leer la entrada del usuario desde el teclado
        Scanner teclado = new Scanner(System.in);

        // Creamos el inventario de la tienda
        Inventario inventario = new Inventario();

        // Variable para controlar el bucle del menú
        boolean salir = false;

        // Bucle principal del menú
        while (!salir) {

            // Mostramos las opciones del menú
            System.out.println("\n--- Menú Tienda ---");
            System.out.println("1. Añadir producto");
            System.out.println("2. Listar productos");
            System.out.println("3. Ordenar por precio");
            System.out.println("4. Mostrar producto más caro");
            System.out.println("5. Vender producto");
            System.out.println("6. Salir");
            System.out.print("Elige una opción: ");

            // Leemos la opción elegida por el usuario
            String opcion = teclado.nextLine();

            // Usamos switch para decidir qué hacer según la opción
            switch (opcion) {

                // -----------------------------
                case "1": // Añadir producto
                    boolean creado = false; // bandera para repetir si hay error
                    while (!creado) {
                        try {
                            // Pedimos los datos del producto
                            System.out.print("Nombre del producto: ");
                            String nombre = teclado.nextLine();

                            System.out.print("Precio: ");
                            double precio = Double.parseDouble(teclado.nextLine());

                            System.out.print("Stock: ");
                            int stock = Integer.parseInt(teclado.nextLine());

                            // Creamos el producto (lanzará excepción si es inválido)
                            Producto p = new Producto(nombre, precio, stock);

                            // Lo agregamos al inventario
                            inventario.agregarProducto(p);

                            // Marcamos como creado correctamente
                            creado = true;

                        } catch (ProductoInvalidoException e) {
                            // Si el producto no es válido, avisamos y repetimos
                            System.out.println("Error: " + e.getMessage());
                            System.out.println("Inténtalo otra vez.\n");
                        } catch (NumberFormatException e) {
                            // Si el usuario no puso números válidos en precio/stock
                            System.out.println("Error: Debes introducir números válidos para precio y stock.\n");
                        }
                    }
                    break;

                // -----------------------------
                case "2": // Listar productos
                    inventario.listarProductos(); // muestra todos los productos
                    break;

                // -----------------------------
                case "3": // Ordenar por precio
                    inventario.ordenarPorPrecio(); // ordena y muestra productos
                    break;

                // -----------------------------
                case "4": // Mostrar producto más caro
                    Producto caro = inventario.productoMasCaro();
                    if (caro != null) {
                        System.out.println("Producto más caro:");
                        System.out.println(caro); // usa toString() de Producto
                    } else {
                        System.out.println("El inventario está vacío.");
                    }
                    break;

                // -----------------------------
                case "5": // Vender producto
                    try {
                        // Pedimos el nombre y cantidad a vender
                        System.out.print("Nombre del producto a vender: ");
                        String nombreV = teclado.nextLine();

                        System.out.print("Cantidad a vender: ");
                        int cantidad = Integer.parseInt(teclado.nextLine());

                        // Intentamos vender el producto
                        inventario.venderProducto(nombreV, cantidad);

                    } catch (StockInsuficienteException e) {
                        // Si no hay suficiente stock
                        System.out.println("Error: " + e.getMessage());
                    } catch (ProductoInvalidoException e) {
                        // Error inesperado (por si el setter lanza excepción)
                        System.out.println("Error inesperado: " + e.getMessage());
                    } catch (NumberFormatException e) {
                        // Si la cantidad no es un número válido
                        System.out.println("Error: Debes introducir un número válido para la cantidad.");
                    }
                    break;

                // -----------------------------
                case "6": // Salir
                    salir = true; // cambia la bandera para salir del bucle
                    System.out.println("Saliendo del programa...");
                    break;

                // -----------------------------
                default: // opción no válida
                    System.out.println("Opción no válida, intenta de nuevo.");
                    break;
            }
        }

   
    }
}