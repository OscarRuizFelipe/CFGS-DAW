package ui;

import excepciones.PersonaDuplicadaException;
import modelo.*;

public class MenuPersonas {

    private Liga liga;

    public MenuPersonas(Liga liga) {
        this.liga = liga;
    }

    public void mostrar() {
        int op;
        do {
            System.out.println("\n=== PERSONAS ===");
            System.out.println("1. Alta jugador");
            System.out.println("2. Alta entrenador");
            System.out.println("3. Listar personas");
            System.out.println("4. Buscar por ID");
            System.out.println("5. Modificar persona");
            System.out.println("6. Eliminar persona");
            System.out.println("0. Volver");
            op = Consola.leerEnteroEnRango("Opcion: ", 0, 6);
            switch (op) {
                case 1: altaJugador();     break;
                case 2: altaEntrenador();  break;
                case 3: liga.listarPersonas(); Consola.pausar(); break;
                case 4: buscar();          break;
                case 5: modificar();       break;
                case 6: eliminar();        break;
            }
        } while (op != 0);
    }

    private void altaJugador() {
        System.out.println("\n-- Alta Jugador --");
        String id       = Consola.leerString("ID: ");
        String nombre   = Consola.leerString("Nombre: ");
        String nick     = Consola.leerString("Nickname: ");
        int edad        = Consola.leerEnteroEnRango("Edad: ", 16, 60);
        double salario  = Consola.leerDouble("Salario base: ");
        System.out.println("Roles: TOP, JUNGLE, MID, ADC, SUPPORT");
        Rol rol = null;
        while (rol == null) {
            try {
                rol = Rol.fromString(Consola.leerString("Rol: "));
            } catch (IllegalArgumentException e) {
                System.out.println("  Rol no valido.");
            }
        }
        int mec = Consola.leerEnteroEnRango("Nivel mecanico (1-10): ", 1, 10);
        int est = Consola.leerEnteroEnRango("Nivel estrategico (1-10): ", 1, 10);

        try {
            liga.registrarPersona(new Jugador(id, nombre, nick, edad, salario, rol, mec, est));
            System.out.println("  Jugador registrado.");
        } catch (PersonaDuplicadaException e) {
            System.out.println("  Error: " + e.getMessage());
        }
        Consola.pausar();
    }

    private void altaEntrenador() {
        System.out.println("\n-- Alta Entrenador --");
        String id       = Consola.leerString("ID: ");
        String nombre   = Consola.leerString("Nombre: ");
        String nick     = Consola.leerString("Nickname: ");
        int edad        = Consola.leerEnteroEnRango("Edad: ", 18, 70);
        double salario  = Consola.leerDouble("Salario base: ");
        int exp         = Consola.leerEnteroEnRango("Experiencia (años): ", 0, 50);
        String esp      = Consola.leerString("Especialidad: ");

        try {
            liga.registrarPersona(new Entrenador(id, nombre, nick, edad, salario, exp, esp));
            System.out.println("  Entrenador registrado.");
        } catch (PersonaDuplicadaException e) {
            System.out.println("  Error: " + e.getMessage());
        }
        Consola.pausar();
    }

    private void buscar() {
        String id = Consola.leerString("ID a buscar: ");
        PersonaLiga p = liga.buscarPersonaPorId(id);
        if (p == null) System.out.println("  No encontrada.");
        else p.mostrarResumen();
        Consola.pausar();
    }

    private void modificar() {
        String id = Consola.leerString("ID a modificar: ");
        PersonaLiga p = liga.buscarPersonaPorId(id);
        if (p == null) { System.out.println("  No encontrada."); Consola.pausar(); return; }
        System.out.println("1.Nombre  2.Nickname  3.Edad  4.Salario");
        int op = Consola.leerEnteroEnRango("Opcion: ", 1, 4);
        switch (op) {
            case 1: p.setNombre(Consola.leerString("Nuevo nombre: "));    break;
            case 2: p.setNickname(Consola.leerString("Nuevo nickname: ")); break;
            case 3: p.setEdad(Consola.leerEnteroEnRango("Nueva edad: ", 16, 70)); break;
            case 4: p.setSalarioBase(Consola.leerDouble("Nuevo salario: ")); break;
        }
        liga.registrarAccion("Modificacion persona: " + p.getIdentificador());
        System.out.println("  Datos actualizados.");
        Consola.pausar();
    }

    private void eliminar() {
        String id = Consola.leerString("ID a eliminar: ");
        PersonaLiga p = liga.buscarPersonaPorId(id);
        if (p == null) { System.out.println("  No encontrada."); Consola.pausar(); return; }
        if (Consola.leerSiNo("Eliminar " + p.getNickname() + "?")) {
            liga.eliminarPersona(id);
            System.out.println("  Eliminada.");
        }
        Consola.pausar();
    }
}
