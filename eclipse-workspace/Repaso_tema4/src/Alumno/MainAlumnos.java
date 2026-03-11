package Alumno;

import java.util.Arrays;
import java.util.Scanner;

public class MainAlumnos {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);
        Alumno[] alumnos = new Alumno[5];

        // 1️⃣ Pedir datos
        for (int i = 0; i < alumnos.length; i++) {

            System.out.println("Alumno " + (i + 1));

            System.out.print("Nombre: ");
            String nombre = teclado.nextLine();

            boolean notaCorrecta = false;

            while (!notaCorrecta) {
                try {
                    System.out.print("Nota: ");
                    double nota = Double.parseDouble(teclado.nextLine());

                    alumnos[i] = new Alumno(nombre, nota);
                    notaCorrecta = true;

                } catch (NotaInvalidaException e) {
                    System.out.println("Error: " + e.getMessage());
                } catch (NumberFormatException e) {
                    System.out.println("Debes introducir un número válido.");
                }
            }
        }

        // 2️⃣ Mostrar lista original
        System.out.println("\nLista original:");
        for (Alumno a : alumnos) {
            System.out.println(a);
        }

        // 3️⃣ Ordenar por nota
        Arrays.sort(alumnos);

        // 4️⃣ Mostrar mejor alumno
        System.out.println("\nMejor alumno:");
        System.out.println(alumnos[alumnos.length - 1]);

        // 5️⃣ Subir 1 punto a todos
        for (Alumno a : alumnos) {
            a.subirNota();
        }

        // 6️⃣ Mostrar lista final
        System.out.println("\nLista final (con +1 punto):");
        for (Alumno a : alumnos) {
            System.out.println(a);
        }

    }
}