package ui;

import modelo.*;

import java.util.ArrayList;

public class MenuIncidencias {

    private Liga liga;

    public MenuIncidencias(Liga liga) {
        this.liga = liga;
    }

    public void mostrar() {
        int op;
        do {
            System.out.println("\n=== INCIDENCIAS Y SANCIONES ===");
            System.out.println("1. Registrar incidencia");
            System.out.println("2. Listar incidencias");
            System.out.println("3. Buscar por equipo");
            System.out.println("4. Buscar por jugador");
            System.out.println("5. Aplicar sancion");
            System.out.println("6. Levantar sancion");
            System.out.println("0. Volver");
            op = Consola.leerEnteroEnRango("Opcion: ", 0, 6);
            switch (op) {
                case 1: registrar();        break;
                case 2: liga.listarIncidencias(); Consola.pausar(); break;
                case 3: buscarEquipo();     break;
                case 4: buscarJugador();    break;
                case 5: aplicarSancion();   break;
                case 6: levantarSancion();  break;
            }
        } while (op != 0);
    }

    private void registrar() {
        System.out.println("\n-- Registrar Incidencia --");
        System.out.println("Tipos: sancion, expulsion, error_tecnico, partido_aplazado, otro");
        String tipo      = Consola.leerString("Tipo: ");
        String desc      = Consola.leerString("Descripcion: ");
        String idEquipo  = Consola.leerString("Nombre del equipo afectado: ");
        String idJugador = Consola.leerString("ID del jugador (ENTER si no aplica): ");
        if (idJugador.isEmpty()) idJugador = null;

        liga.registrarIncidencia(new Incidencia(tipo, desc, idEquipo, idJugador));

        // Si es sancion o expulsion, aplicar sancion automaticamente
        if ((tipo.equalsIgnoreCase("sancion") || tipo.equalsIgnoreCase("expulsion"))
                && idJugador != null) {
            liga.aplicarSancion(idJugador);
            System.out.println("  Sancion aplicada automaticamente al jugador.");
        }
        System.out.println("  Incidencia registrada.");
        Consola.pausar();
    }

    private void buscarEquipo() {
        String id = Consola.leerString("Nombre del equipo: ");
        ArrayList<Incidencia> lista = liga.buscarPorEquipo(id);
        if (lista.isEmpty()) System.out.println("  Sin incidencias para ese equipo.");
        else for (Incidencia i : lista) System.out.println("  " + i);
        Consola.pausar();
    }

    private void buscarJugador() {
        String id = Consola.leerString("ID del jugador: ");
        ArrayList<Incidencia> lista = liga.buscarPorJugador(id);
        if (lista.isEmpty()) System.out.println("  Sin incidencias para ese jugador.");
        else for (Incidencia i : lista) System.out.println("  " + i);
        Consola.pausar();
    }

    private void aplicarSancion() {
        String id = Consola.leerString("ID del jugador a sancionar: ");
        PersonaLiga p = liga.buscarPersonaPorId(id);
        if (!(p instanceof Jugador)) {
            System.out.println("  Jugador no encontrado.");
        } else if (((Jugador) p).isSancionado()) {
            System.out.println("  Ya esta sancionado.");
        } else {
            liga.aplicarSancion(id);
            System.out.println("  Sancion aplicada.");
        }
        Consola.pausar();
    }

    private void levantarSancion() {
        String id = Consola.leerString("ID del jugador: ");
        PersonaLiga p = liga.buscarPersonaPorId(id);
        if (!(p instanceof Jugador)) {
            System.out.println("  Jugador no encontrado.");
        } else if (!((Jugador) p).isSancionado()) {
            System.out.println("  No esta sancionado.");
        } else {
            liga.levantarSancion(id);
            System.out.println("  Sancion levantada.");
        }
        Consola.pausar();
    }
}
