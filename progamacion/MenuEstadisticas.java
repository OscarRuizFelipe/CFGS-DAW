package ui;

import modelo.*;

import java.util.ArrayList;
import java.util.Stack;

public class MenuEstadisticas {

    private Liga liga;

    public MenuEstadisticas(Liga liga) {
        this.liga = liga;
    }

    public void mostrarClasificacion() {
        System.out.println("\n=== CLASIFICACION GENERAL ===");
        ArrayList<Equipo> clas = liga.generarClasificacion();
        if (clas.isEmpty()) { System.out.println("  No hay equipos."); Consola.pausar(); return; }
        System.out.printf("  %-4s %-20s %-5s %-5s %-6s %-6s %-6s%n",
            "Pos", "Equipo", "V", "D", "PF", "PC", "Dif");
        Consola.linea();
        for (int i = 0; i < clas.size(); i++) {
            Equipo e = clas.get(i);
            System.out.printf("  %-4s %-20s %-5d %-5d %-6d %-6d %-6d%n",
                (i + 1), e.getNombre(), e.getVictorias(), e.getDerrotas(),
                e.getPuntosAFavor(), e.getPuntosEnContra(), e.getDiferenciaPuntos());
        }
        Consola.pausar();
    }

    public void mostrarEstadisticas() {
        int op;
        do {
            System.out.println("\n=== ESTADISTICAS ===");
            System.out.println("1. Estadisticas de jugadores");
            System.out.println("2. Estadisticas de equipos");
            System.out.println("0. Volver");
            op = Consola.leerEnteroEnRango("Opcion: ", 0, 2);
            switch (op) {
                case 1: statsJugadores(); break;
                case 2: statsEquipos();   break;
            }
        } while (op != 0);
    }

    private void statsJugadores() {
        System.out.println("\n=== JUGADORES ===");
        boolean hay = false;
        for (PersonaLiga p : liga.getPersonas()) {
            if (p instanceof Jugador) {
                Jugador j = (Jugador) p;
                hay = true;
                System.out.printf("  %-15s | %-8s | Partidas: %-4d | MVPs: %-4d"
                    + " | Rendimiento: %-5.2f | %s%n",
                    j.getNickname(), j.getRol(),
                    j.getPartidasJugadas(), j.getMvpTotales(),
                    j.calcularRendimiento(),
                    j.isSancionado() ? "SANCIONADO" : "Activo");
            }
        }
        if (!hay) System.out.println("  No hay jugadores.");
        Consola.pausar();
    }

    private void statsEquipos() {
        System.out.println("\n=== EQUIPOS ===");
        ArrayList<Equipo> equipos = liga.getEquipos();
        if (equipos.isEmpty()) { System.out.println("  No hay equipos."); Consola.pausar(); return; }
        for (int i = 0; i < equipos.size(); i++) {
            Equipo e = equipos.get(i);
            System.out.println("\n  " + e.getNombre() + " (" + e.getCiudad() + ")");
            System.out.println("    Victorias: " + e.getVictorias()
                + " | Derrotas: " + e.getDerrotas());
            System.out.println("    Puntos a favor: " + e.getPuntosAFavor()
                + " | Puntos en contra: " + e.getPuntosEnContra());
            System.out.println("    Diferencia: " + e.getDiferenciaPuntos());
            System.out.printf("    Coste plantilla: %.2f euros%n",
                e.calcularCosteTotalEquipo());
        }
        Consola.pausar();
    }

    public void mostrarHistorial() {
        int op;
        do {
            System.out.println("\n=== HISTORIAL DE ACCIONES ===");
            System.out.println("1. Ver ultima accion");
            System.out.println("2. Ver historial completo");
            System.out.println("3. Deshacer ultima accion");
            System.out.println("0. Volver");
            op = Consola.leerEnteroEnRango("Opcion: ", 0, 3);
            switch (op) {
                case 1: verUltima();    break;
                case 2: verCompleto();  break;
                case 3: deshacer();     break;
            }
        } while (op != 0);
    }

    private void verUltima() {
        String a = liga.verUltimaAccion();
        if (a == null) System.out.println("  Historial vacio.");
        else System.out.println("  Ultima accion: " + a);
        Consola.pausar();
    }

    private void verCompleto() {
        Stack<String> pila = liga.getPilaHistorial();
        if (pila.isEmpty()) { System.out.println("  Historial vacio."); }
        else {
            System.out.println("  Historial (mas reciente primero):");
            int i = 1;
            for (String a : pila) {
                System.out.println("  " + i++ + ". " + a);
            }
        }
        Consola.pausar();
    }

    private void deshacer() {
        String a = liga.deshacerUltimaAccion();
        if (a == null) System.out.println("  Historial vacio.");
        else System.out.println("  Accion eliminada del historial: " + a);
        Consola.pausar();
    }
}
