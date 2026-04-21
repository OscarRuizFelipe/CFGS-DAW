package cinco_Simulacro_DatosYExcepciones;

import java.util.Scanner;

// Clase principal del programa
public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Array polimórfico de centros Pokémon
        CentroPokemon[] centros = new CentroPokemon[6];

        try {

            // Creamos 3 hospitales y 3 emergencias
            for (int i = 0; i < centros.length; i++) {

                System.out.println("\n=== CREANDO CENTRO " + (i + 1) + " ===");

                // =========================
                // DATOS ENFERMERO
                // =========================
                System.out.print("ID enfermero: ");
                String id = sc.nextLine();

                System.out.print("Nombre enfermero: ");
                String nombre = sc.nextLine();

                System.out.print("Experiencia enfermero: ");
                int exp = Integer.parseInt(sc.nextLine());

                EnfermeroPokemon enfermero = new EnfermeroPokemon(id, nombre, exp);

                // =========================
                // DATOS CENTRO
                // =========================
                System.out.print("Código del centro: ");
                String codigo = sc.nextLine();

                // CONTROL DE ENUM
                Region region = null;
                while (region == null) {
                    try {
                        System.out.print("Región (KANTO, JOHTO, HOENN, SINNOH, UNOVA): ");
                        region = Region.valueOf(sc.nextLine().toUpperCase());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Región inválida, inténtalo de nuevo.");
                    }
                }

                // =========================
                // MATRICES
                // =========================
                int[][] pokemons = new int[5][2];
                int[][] emergencias = new int[5][2];

                System.out.println("Introduce datos de Pokémons y Emergencias:");

                for (int dia = 0; dia < 5; dia++) {

                    System.out.println("Día " + (dia + 1));

                    for (int turno = 0; turno < 2; turno++) {

                        int p = -1;
                        int e = -1;

                        // CONTROL DE ENTEROS
                        try {
                            System.out.print("Pokémons: ");
                            p = Integer.parseInt(sc.nextLine());

                            System.out.print("Emergencias: ");
                            e = Integer.parseInt(sc.nextLine());

                            // VALIDACIÓN NEGATIVOS
                            if (p < 0 || e < 0) {
                                throw new DatosInvalidosException("No se permiten valores negativos");
                            }

                        } catch (NumberFormatException ex) {
                            System.out.println("Error: debes introducir números");
                            turno--; // repetir turno
                            continue;
                        }

                        pokemons[dia][turno] = p;
                        emergencias[dia][turno] = e;
                    }
                }

                // =========================
                // CREACIÓN DE CENTROS
                // =========================
                try {

                    if (i < 3) {

                        System.out.print("Camas disponibles: ");
                        int camas = Integer.parseInt(sc.nextLine());

                        centros[i] = new CentroPokemonHospital(
                                codigo, region, enfermero, pokemons, emergencias, camas
                        );

                    } else {

                        System.out.print("Rescates especiales: ");
                        int rescates = Integer.parseInt(sc.nextLine());

                        centros[i] = new CentroPokemonEmergencias(
                                codigo, region, enfermero, pokemons, emergencias, rescates
                        );
                    }

                } catch (NumberFormatException ex) {
                    System.out.println("Error en datos del centro, se asigna valor 0");
                }
            }

            // =========================
            // MOSTRAR RESULTADOS
            // =========================
            System.out.println("\n========= RESULTADOS =========");

            for (CentroPokemon c : centros) {

                System.out.println("\n----------------------------");
                System.out.println(c);

                c.mostrarResumenSemanal();

                System.out.println("Total Pokémons: " + c.totalPokemonsAtendidos());
                System.out.println("Total Emergencias: " + c.totalEmergencias());
                System.out.println("Tasa emergencias: " + c.tasaEmergencias());
                System.out.println("Índice salud: " + c.calcularIndiceSalud());
                System.out.println("¿Auditoría?: " + c.requiereAuditoria());
            }

        } catch (DatosInvalidosException e) {
            System.out.println("ERROR DE DATOS: " + e.getMessage());

        } catch (Exception e) {
            System.out.println("ERROR GENERAL: " + e.getMessage());

        } finally {
            sc.close(); // SIEMPRE se cierra aquí
        }
    }
}