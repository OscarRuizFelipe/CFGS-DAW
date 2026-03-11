package CuentaBancariaTryCatch;

import java.util.Scanner;

public class MainCuentaBancaria {

    public static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {

        CuentaBancaria cuenta = new CuentaBancaria("Hugo", 200);

        int opcion = 0;

        while (opcion != 4) {

            mostrarMenu();
            System.out.print("Elige una opción: ");
            opcion = teclado.nextInt();

            switch (opcion) {

                case 1:
                    System.out.print("Cantidad a ingresar: ");
                    double ingreso = teclado.nextDouble();

                    try {
                        cuenta.ingresar(ingreso);
                    } catch (IllegalArgumentException e) {
                        System.out.println("ERROR: " + e);
                    }

                    System.out.println("Saldo actual: " + cuenta.getSaldo());
                    break;

                case 2:
                    System.out.print("Cantidad a retirar: ");
                    double retiro = teclado.nextDouble();

                    try {
                        cuenta.retirar(retiro);
                    } catch (IllegalArgumentException e) {
                        System.out.println("ERROR: " + e);
                    }
                    catch (SaldoInsuficienteException e) {
                        System.out.println("ERROR: " + e);
                    }

                    System.out.println("Saldo actual: " + cuenta.getSaldo());
                    break;

                case 3:
                    System.out.println(cuenta);
                    break;

                case 4:
                    System.out.println("Hasta otra.");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

            System.out.println();
        }
    }

    public static void mostrarMenu() {
        System.out.println("MENÚ BANCO");
        System.out.println("1. Ingresar dinero");
        System.out.println("2. Retirar dinero");
        System.out.println("3. Consultar saldo");
        System.out.println("4. Salir");
        System.out.println();
    }
}