package ui;

import excepciones.RolNoDisponibleException;
import excepciones.JugadorSancionadoException;
import modelo.*;

import java.util.ArrayList;

public class MenuFichajes {

    private Liga liga;
    private MenuEquipos menuEquipos;

    public MenuFichajes(Liga liga, MenuEquipos menuEquipos) {
        this.liga = liga;
        this.menuEquipos = menuEquipos;
    }

    public void mostrar() {
        int op;
        do {
            System.out.println("\n=== FICHAJES Y PLANTILLAS ===");
            System.out.println("1. Fichar titular");
            System.out.println("2. Fichar suplente");
            System.out.println("3. Eliminar titular");
            System.out.println("4. Eliminar suplente");
            System.out.println("5. Promover suplente a titular");
            System.out.println("6. Sustituir titular por suplente");
            System.out.println("7. Ver convocatoria");
            System.out.println("0. Volver");
            op = Consola.leerEnteroEnRango("Opcion: ", 0, 7);
            switch (op) {
                case 1: ficharTitular();    break;
                case 2: ficharSuplente();   break;
                case 3: eliminarTitular();  break;
                case 4: eliminarSuplente(); break;
                case 5: promover();         break;
                case 6: sustituir();        break;
                case 7: verConvocatoria();  break;
            }
        } while (op != 0);
    }

    private void ficharTitular() {
        Equipo eq = menuEquipos.seleccionar();
        if (eq == null) { Consola.pausar(); return; }
        String id = Consola.leerString("ID del jugador: ");
        PersonaLiga p = liga.buscarPersonaPorId(id);
        if (!(p instanceof Jugador)) {
            System.out.println("  Jugador no encontrado.");
            Consola.pausar(); return;
        }
        try {
            eq.ficharTitular((Jugador) p);
            liga.registrarAccion("Titular " + p.getNickname() + " -> " + eq.getNombre());
            System.out.println("  Titular fichado.");
        } catch (RolNoDisponibleException e) {
            System.out.println("  Error: " + e.getMessage());
        }
        Consola.pausar();
    }

    private void ficharSuplente() {
        Equipo eq = menuEquipos.seleccionar();
        if (eq == null) { Consola.pausar(); return; }
        String id = Consola.leerString("ID del jugador: ");
        PersonaLiga p = liga.buscarPersonaPorId(id);
        if (!(p instanceof Jugador)) {
            System.out.println("  Jugador no encontrado.");
            Consola.pausar(); return;
        }
        eq.ficharSuplente((Jugador) p);
        liga.registrarAccion("Suplente " + p.getNickname() + " -> " + eq.getNombre());
        System.out.println("  Suplente fichado.");
        Consola.pausar();
    }

    private void eliminarTitular() {
        Equipo eq = menuEquipos.seleccionar();
        if (eq == null) { Consola.pausar(); return; }
        String id = Consola.leerString("ID del titular a eliminar: ");
        if (eq.eliminarTitular(id)) {
            liga.registrarAccion("Baja titular " + id + " de " + eq.getNombre());
            System.out.println("  Titular eliminado.");
        } else {
            System.out.println("  No encontrado en titulares.");
        }
        Consola.pausar();
    }

    private void eliminarSuplente() {
        Equipo eq = menuEquipos.seleccionar();
        if (eq == null) { Consola.pausar(); return; }
        String id = Consola.leerString("ID del suplente a eliminar: ");
        if (eq.eliminarSuplente(id)) {
            liga.registrarAccion("Baja suplente " + id + " de " + eq.getNombre());
            System.out.println("  Suplente eliminado.");
        } else {
            System.out.println("  No encontrado en suplentes.");
        }
        Consola.pausar();
    }

    private void promover() {
        Equipo eq = menuEquipos.seleccionar();
        if (eq == null) { Consola.pausar(); return; }
        mostrarSuplentes(eq);
        String id = Consola.leerString("ID del suplente a promover: ");
        try {
            if (eq.promoverSuplente(id)) {
                liga.registrarAccion("Promocion suplente " + id + " en " + eq.getNombre());
                System.out.println("  Promovido a titular.");
            } else {
                System.out.println("  No encontrado en suplentes.");
            }
        } catch (RolNoDisponibleException e) {
            System.out.println("  Error: " + e.getMessage());
        }
        Consola.pausar();
    }

    private void sustituir() {
        Equipo eq = menuEquipos.seleccionar();
        if (eq == null) { Consola.pausar(); return; }
        String idTitular  = Consola.leerString("ID del titular a sustituir: ");
        String idSuplente = Consola.leerString("ID del suplente que entra: ");
        try {
            if (eq.sustituirTitular(idTitular, idSuplente)) {
                liga.registrarAccion("Sustitucion en " + eq.getNombre());
                System.out.println("  Sustitucion realizada.");
            } else {
                System.out.println("  No se encontro el titular o el suplente.");
            }
        } catch (RolNoDisponibleException e) {
            System.out.println("  Error: " + e.getMessage());
        }
        Consola.pausar();
    }

    private void verConvocatoria() {
        Equipo eq = menuEquipos.seleccionar();
        if (eq == null) { Consola.pausar(); return; }
        try {
            ArrayList<Jugador> conv = eq.generarConvocatoria();
            System.out.println("  Convocatoria de " + eq.getNombre() + ":");
            for (int i = 0; i < conv.size(); i++) {
                System.out.println("    [" + conv.get(i).getRol() + "] "
                    + conv.get(i).getNickname());
            }
        } catch (Exception e) {
            System.out.println("  Convocatoria invalida: " + e.getMessage());
        }
        Consola.pausar();
    }

    private void mostrarSuplentes(Equipo eq) {
        System.out.println("  Suplentes de " + eq.getNombre() + ":");
        if (eq.getSuplentes().isEmpty()) {
            System.out.println("    Sin suplentes.");
        } else {
            for (int i = 0; i < eq.getSuplentes().size(); i++) {
                System.out.println("    " + eq.getSuplentes().get(i));
            }
        }
    }
}
