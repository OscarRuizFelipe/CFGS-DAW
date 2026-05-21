package ui;

import excepciones.JugadorSancionadoException;
import excepciones.PartidoInvalidoException;
import modelo.*;

import java.util.ArrayList;
import java.util.Queue;

public class MenuPartidos {

    private Liga liga;
    private MenuEquipos menuEquipos;

    public MenuPartidos(Liga liga, MenuEquipos menuEquipos) {
        this.liga = liga;
        this.menuEquipos = menuEquipos;
    }

   
    public void mostrarMenuCalendario() {
        int op;
        do {
            System.out.println("\n=== CALENDARIO ===");
            System.out.println("1. Generar calendario");
            System.out.println("2. Mostrar calendario completo");
            System.out.println("3. Consultar jornada");
            System.out.println("4. Crear partido manual");
            System.out.println("0. Volver");
            op = Consola.leerEnteroEnRango("Opcion: ", 0, 4);
            switch (op) {
                case 1: liga.generarCalendario(); Consola.pausar(); break;
                case 2: liga.mostrarCalendario(); Consola.pausar(); break;
                case 3: consultarJornada();  break;
                case 4: crearManual();       break;
            }
        } while (op != 0);
    }

    
    public void mostrarMenuCola() {
        int op;
        do {
            System.out.println("\n=== COLA DE PARTIDOS PENDIENTES ===");
            System.out.println("1. Ver siguiente partido pendiente");
            System.out.println("2. Disputar siguiente partido");
            System.out.println("3. Mostrar todos los pendientes");
            System.out.println("4. Vaciar cola");
            System.out.println("0. Volver");
            op = Consola.leerEnteroEnRango("Opcion: ", 0, 4);
            switch (op) {
                case 1: verSiguiente();       break;
                case 2: disputarSiguiente();  break;
                case 3: mostrarPendientes();  break;
                case 4: vaciarCola();         break;
            }
        } while (op != 0);
    }

  
    public void mostrarMenuRegistro() {
        int op;
        do {
            System.out.println("\n=== PARTIDOS JUGADOS ===");
            System.out.println("1. Registrar resultado");
            System.out.println("2. Ver todos los partidos");
            System.out.println("0. Volver");
            op = Consola.leerEnteroEnRango("Opcion: ", 0, 2);
            switch (op) {
                case 1: registrarResultado(); break;
                case 2: verTodos();           break;
            }
        } while (op != 0);
    }

    private void consultarJornada() {
        int j = Consola.leerEnteroEnRango("Numero de jornada: ", 1, 50);
        liga.mostrarJornada(j);
        Consola.pausar();
    }

    private void crearManual() {
        if (liga.getEquipos().size() < 2) {
            System.out.println("  Necesitas al menos 2 equipos.");
            Consola.pausar(); return;
        }
        String id = Consola.leerString("ID del partido: ");
        int jornada = Consola.leerEnteroEnRango("Jornada: ", 1, 50);
        System.out.println("Equipo LOCAL:");
        Equipo local = menuEquipos.seleccionar();
        System.out.println("Equipo VISITANTE:");
        Equipo visitante = menuEquipos.seleccionar();
        if (local == null || visitante == null) { Consola.pausar(); return; }
        try {
            liga.crearPartido(new Partido(id, jornada, local, visitante));
            System.out.println("  Partido creado y encolado.");
        } catch (PartidoInvalidoException e) {
            System.out.println("  Error: " + e.getMessage());
        }
        Consola.pausar();
    }

    private void verSiguiente() {
        Partido p = liga.verSiguientePartido();
        if (p == null) System.out.println("  No hay partidos pendientes.");
        else System.out.println("  Siguiente: " + p);
        Consola.pausar();
    }

    private void disputarSiguiente() {
        if (liga.colaVacia()) {
            System.out.println("  No hay partidos pendientes.");
            Consola.pausar(); return;
        }
        Partido p = liga.disputarSiguientePartido();
        if (p.isDisputado()) {
            System.out.println("  Este partido ya fue disputado.");
            Consola.pausar(); return;
        }
        System.out.println("\n  Disputando: "
            + p.getEquipoLocal().getNombre() + " vs "
            + p.getEquipoVisitante().getNombre());

        // Validar convocatorias
        try { p.getEquipoLocal().generarConvocatoria(); }
        catch (JugadorSancionadoException e) {
            System.out.println("  Aviso local: " + e.getMessage());
        }
        try { p.getEquipoVisitante().generarConvocatoria(); }
        catch (JugadorSancionadoException e) {
            System.out.println("  Aviso visitante: " + e.getMessage());
        }



        int pL = Consola.leerEnteroEnRango("Puntos " + p.getEquipoLocal().getNombre() + ": ", 0, 100);
        int pV = Consola.leerEnteroEnRango("Puntos " + p.getEquipoVisitante().getNombre() + ": ", 0, 100);

        Jugador mvp = pedirMVP();
        p.registrarResultado(pL, pV, mvp);
        liga.actualizarMatrizCalendario();

        Equipo ganador = p.calcularGanador();
        System.out.println("  Resultado registrado.");
        if (ganador != null) System.out.println("  Ganador: " + ganador.getNombre());
        else System.out.println("  Empate.");
        liga.registrarAccion("Partido disputado: " + p.getIdentificador() + " " + pL + "-" + pV);
        Consola.pausar();
    }

    private void mostrarPendientes() {
        Queue<Partido> cola = liga.getColaPartidos();
        if (cola.isEmpty()) {
            System.out.println("  No hay partidos pendientes.");
        } else {
            System.out.println("  Pendientes (FIFO):");
            int i = 1;
            for (Partido p : cola) {
                System.out.println("  " + i++ + ". " + p);
            }
        }
        Consola.pausar();
    }

    private void vaciarCola() {
        if (Consola.leerSiNo("Vaciar la cola de pendientes?")) {
            liga.vaciarCola();
            System.out.println("  Cola vaciada.");
        }
        Consola.pausar();
    }

    private void registrarResultado() {
        ArrayList<Partido> pendientes = new ArrayList<Partido>();
        for (Partido p : liga.getPartidos()) {
            if (!p.isDisputado()) pendientes.add(p);
        }
        if (pendientes.isEmpty()) {
            System.out.println("  No hay partidos sin resultado.");
            Consola.pausar(); return;
        }
        for (int i = 0; i < pendientes.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + pendientes.get(i));
        }
        int idx = Consola.leerEnteroEnRango("Selecciona partido: ", 1, pendientes.size());
        Partido p = pendientes.get(idx - 1);

        int pL = Consola.leerEnteroEnRango("Puntos " + p.getEquipoLocal().getNombre() + ": ", 0, 100);
        int pV = Consola.leerEnteroEnRango("Puntos " + p.getEquipoVisitante().getNombre() + ": ", 0, 100);
        Jugador mvp = pedirMVP();

        p.registrarResultado(pL, pV, mvp);
        liga.actualizarMatrizCalendario();
        liga.registrarAccion("Resultado: " + p.getIdentificador());
        System.out.println("  Resultado guardado.");
        Consola.pausar();
    }

    private void verTodos() {
        ArrayList<Partido> partidos = liga.getPartidos();
        if (partidos.isEmpty()) { System.out.println("  No hay partidos."); }
        else {
            for (Partido p : partidos) System.out.println("  " + p);
        }
        Consola.pausar();
    }

    private Jugador pedirMVP() {
        if (!Consola.leerSiNo("Asignar MVP?")) return null;
        String id = Consola.leerString("ID del jugador MVP: ");
        PersonaLiga p = liga.buscarPersonaPorId(id);
        if (p instanceof Jugador) return (Jugador) p;
        System.out.println("  Jugador no encontrado. MVP sin asignar.");
        return null;
    }
}
