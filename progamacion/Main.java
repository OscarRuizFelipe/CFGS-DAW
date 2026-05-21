import modelo.Liga;
import ui.*;

public class Main {

    public static void main(String[] args) {

        Liga liga = new Liga("Liga 1GSY E-Sports");

        MenuPersonas     menuPersonas     = new MenuPersonas(liga);
        MenuEquipos      menuEquipos      = new MenuEquipos(liga);
        MenuFichajes     menuFichajes     = new MenuFichajes(liga, menuEquipos);
        MenuPartidos     menuPartidos     = new MenuPartidos(liga, menuEquipos);
        MenuIncidencias  menuIncidencias  = new MenuIncidencias(liga);
        MenuEstadisticas menuEstadisticas = new MenuEstadisticas(liga);

        int op;
        do {
            System.out.println("\n==========================================");
            System.out.println("       LIGA DE E-SPORTS - 1GSY");
            System.out.println("==========================================");
            System.out.println(" 1.  Gestionar personas de la liga");
            System.out.println(" 2.  Gestionar equipos");
            System.out.println(" 3.  Gestionar fichajes y plantillas");
            System.out.println(" 4.  Gestionar calendario");
            System.out.println(" 5.  Gestionar cola de partidos");
            System.out.println(" 6.  Registrar partidos jugados");
            System.out.println(" 7.  Gestionar incidencias y sanciones");
            System.out.println(" 8.  Mostrar clasificacion");
            System.out.println(" 9.  Mostrar estadisticas");
            System.out.println(" 10. Mostrar historial de acciones");
            System.out.println(" 11. Deshacer ultima accion");
            System.out.println(" 0.  Salir");
            System.out.println("==========================================");
            op = Consola.leerEnteroEnRango("Opcion: ", 0, 11);

            switch (op) {
                case 1:  menuPersonas.mostrar();                       break;
                case 2:  menuEquipos.mostrar();                        break;
                case 3:  menuFichajes.mostrar();                       break;
                case 4:  menuPartidos.mostrarMenuCalendario();         break;
                case 5:  menuPartidos.mostrarMenuCola();               break;
                case 6:  menuPartidos.mostrarMenuRegistro();           break;
                case 7:  menuIncidencias.mostrar();                    break;
                case 8:  menuEstadisticas.mostrarClasificacion();      break;
                case 9:  menuEstadisticas.mostrarEstadisticas();       break;
                case 10: menuEstadisticas.mostrarHistorial();          break;
                case 11:
                    String accion = liga.deshacerUltimaAccion();
                    if (accion == null) System.out.println("  Historial vacio.");
                    else System.out.println("  Deshecha: " + accion);
                    Consola.pausar();
                    break;
                case 0:
                    System.out.println("  Hasta la proxima temporada.");
                    break;
            }
        } while (op != 0);
    }
}
