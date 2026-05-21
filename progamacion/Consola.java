package ui;

import java.util.Scanner;

public class Consola {

    private static final Scanner sc = new Scanner(System.in);

    public static String leerString(String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine().trim();
    }

    public static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("  Introduce un numero entero valido.");
            }
        }
    }

    public static int leerEnteroEnRango(String mensaje, int min, int max) {
        while (true) {
            int v = leerEntero(mensaje);
            if (v >= min && v <= max) return v;
            System.out.println("  Introduce un numero entre " + min + " y " + max + ".");
        }
    }

    public static double leerDouble(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Double.parseDouble(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("  Introduce un numero decimal valido.");
            }
        }
    }

    public static boolean leerSiNo(String mensaje) {
        while (true) {
            String r = leerString(mensaje + " (s/n): ").toLowerCase();
            if (r.equals("s")) return true;
            if (r.equals("n")) return false;
            System.out.println("  Escribe s o n.");
        }
    }

    public static void pausar() {
        System.out.print("\n  Pulsa ENTER para continuar...");
        sc.nextLine();
    }

    public static void linea() {
        System.out.println("----------------------------------------");
    }
}
