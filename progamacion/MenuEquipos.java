package ui;

import excepciones.EquipoDuplicadoException;
import excepciones.RolNoDisponibleException;
import modelo.*;

import java.util.ArrayList;

public class MenuEquipos {

    private Liga liga;

    public MenuEquipos(Liga liga) {
        this.liga = liga;
    }

    public void mostrar() {
        int op;
        do {
            System.out.println("\n=== EQUIPOS ===");
            System.out.println("1. Crear equipo");
            System.out.println("2. Asignar entrenador");
            System.out.println("3. Listar equipos");
            System.out.println("4. Mostrar plantilla");
            System.out.println("5. Mostrar coste total");
            System.out.println("0. Volver");
            op = Consola.leerEnteroEnRango("Opcion: ", 0, 5);
            switch (op) {
                case 1: crearEquipo();       break;
                case 2: asignarEntrenador(); break;
                case 3: listarEquipos();     break;
                case 4: mostrarPlantilla();  break;
                case 5: mostrarCoste();      break;
            }
        } while (op != 0);
    }

    private void crearEquipo() {
        System.out.println("\n-- Crear Equipo --");
        String nombre   = Consola.leerString("Nombre: ");
        String ciudad   = Consola.leerString("Ciudad: ");
        double presup   = Consola.leerDouble("Presupuesto: ");
        try {
            liga.registrarEquipo(new Equipo(nombre, ciudad, presup));
            System.out.println("  Equipo creado.");
        } catch (EquipoDuplicadoException e) {
            System.out.println("  Error: " + e.getMessage());
        }
        Consola.pausar();
    }

    private void asignarEntrenador() {
        Equipo eq = seleccionar();
        if (eq == null) { Consola.pausar(); return; }
        String id = Consola.leerString("ID del entrenador: ");
        PersonaLiga p = liga.buscarPersonaPorId(id);
        if (p instanceof Entrenador) {
            eq.setEntrenador((Entrenador) p);
            liga.registrarAccion("Entrenador " + p.getNickname() + " -> " + eq.getNombre());
            System.out.println("  Entrenador asignado.");
        } else {
            System.out.println("  No se encontro entrenador con ese ID.");
        }
        Consola.pausar();
    }

    private void listarEquipos() {
        ArrayList<Equipo> equipos = liga.getEquipos();
        if (equipos.isEmpty()) { System.out.println("  No hay equipos."); }
        else {
            for (int i = 0; i < equipos.size(); i++) {
                System.out.println("  " + (i + 1) + ". " + equipos.get(i));
            }
        }
        Consola.pausar();
    }

    private void mostrarPlantilla() {
        Equipo eq = seleccionar();
        if (eq != null) { System.out.println(); eq.mostrarPlantilla(); }
        Consola.pausar();
    }

    private void mostrarCoste() {
        Equipo eq = seleccionar();
        if (eq != null) {
            System.out.printf("  Coste mensual %s: %.2f euros%n",
                eq.getNombre(), eq.calcularCosteTotalEquipo());
        }
        Consola.pausar();
    }

   
    public Equipo seleccionar() {
        ArrayList<Equipo> equipos = liga.getEquipos();
        if (equipos.isEmpty()) { System.out.println("  No hay equipos."); return null; }
        for (int i = 0; i < equipos.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + equipos.get(i).getNombre());
        }
        int idx = Consola.leerEnteroEnRango("Selecciona (numero): ", 1, equipos.size());
        return equipos.get(idx - 1);
    }
}
