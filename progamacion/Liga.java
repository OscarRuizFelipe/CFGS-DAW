package modelo;

import excepciones.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Collections;

public class Liga {

    private String nombre;

    
    private ArrayList<PersonaLiga> personas;

    
    private ArrayList<Equipo> equipos;

    
    private ArrayList<Partido> partidos;

   
    private LinkedList<Incidencia> incidencias;

    
    private HashSet<String> idsPersonas;
    private HashSet<String> nombresEquipos;
    private HashSet<String> idsPartidos;

    
    private Queue<Partido> colaPartidos;

    
    private Stack<String> pilaHistorial;

   
    private int[][] matrizCalendario;
    private static final int MAX_JORNADAS = 10;
    private int jornadasGeneradas;

    public Liga(String nombre) {
        this.nombre = nombre;
        this.personas    = new ArrayList<PersonaLiga>();
        this.equipos     = new ArrayList<Equipo>();
        this.partidos    = new ArrayList<Partido>();
        this.incidencias = new LinkedList<Incidencia>();
        this.idsPersonas    = new HashSet<String>();
        this.nombresEquipos = new HashSet<String>();
        this.idsPartidos    = new HashSet<String>();
        this.colaPartidos   = new LinkedList<Partido>();
        this.pilaHistorial  = new Stack<String>();
        this.matrizCalendario = new int[MAX_JORNADAS][20];
        this.jornadasGeneradas = 0;
    }

    

    public void registrarPersona(PersonaLiga persona) throws PersonaDuplicadaException {
        if (idsPersonas.contains(persona.getIdentificador())) {
            throw new PersonaDuplicadaException(
                "Ya existe una persona con ID: " + persona.getIdentificador());
        }
        personas.add(persona);
        idsPersonas.add(persona.getIdentificador());
        registrarAccion("Alta persona: " + persona.getNickname());
    }

    public void eliminarPersona(String id) {
       
        Iterator<PersonaLiga> it = personas.iterator();
        while (it.hasNext()) {
            PersonaLiga p = it.next();
            if (p.getIdentificador().equals(id)) {
                it.remove();
                idsPersonas.remove(id);
                registrarAccion("Baja persona: " + p.getNickname());
                return;
            }
        }
    }

    public PersonaLiga buscarPersonaPorId(String id) {
        for (PersonaLiga p : personas) {
            if (p.getIdentificador().equals(id)) return p;
        }
        return null;
    }

    public void listarPersonas() {
        if (personas.isEmpty()) {
            System.out.println("  No hay personas registradas.");
            return;
        }
        for (PersonaLiga p : personas) {
            p.mostrarResumen();
            System.out.println("  --------------------------------");
        }
    }

    public ArrayList<PersonaLiga> getPersonas() { return personas; }



    public void registrarEquipo(Equipo equipo) throws EquipoDuplicadoException {
        if (nombresEquipos.contains(equipo.getNombre().toLowerCase())) {
            throw new EquipoDuplicadoException(
                "Ya existe un equipo con nombre: " + equipo.getNombre());
        }
        equipos.add(equipo);
        nombresEquipos.add(equipo.getNombre().toLowerCase());
        registrarAccion("Creacion equipo: " + equipo.getNombre());
    }

    public Equipo buscarEquipoPorNombre(String nombre) {
        for (Equipo e : equipos) {
            if (e.getNombre().equalsIgnoreCase(nombre)) return e;
        }
        return null;
    }

    public ArrayList<Equipo> getEquipos() { return equipos; }

  

    public void crearPartido(Partido partido) throws PartidoInvalidoException {
        // No puede ser un equipo contra si mismo (enunciado 5.11)
        if (partido.getEquipoLocal() == partido.getEquipoVisitante()) {
            throw new PartidoInvalidoException(
                "Un equipo no puede jugar contra si mismo.");
        }
        // No puede registrarse dos veces el mismo partido (enunciado 5.11)
        if (idsPartidos.contains(partido.getIdentificador())) {
            throw new PartidoInvalidoException(
                "Ya existe un partido con ID: " + partido.getIdentificador());
        }
        partidos.add(partido);
        idsPartidos.add(partido.getIdentificador());
        // Encolar el partido pendiente - FIFO (enunciado 5.9)
        colaPartidos.offer(partido);
        registrarAccion("Partido creado: " + partido.getIdentificador());
    }

    public ArrayList<Partido> getPartidos() { return partidos; }

 
    public Partido verSiguientePartido() {
        return colaPartidos.peek();
    }

   
    public Partido disputarSiguientePartido() {
        if (colaPartidos.isEmpty()) return null;
        return colaPartidos.poll();
    }

    public Queue<Partido> getColaPartidos() { return colaPartidos; }

    public void vaciarCola() {
        colaPartidos.clear();
        registrarAccion("Cola de partidos vaciada");
    }

    public boolean colaVacia() { return colaPartidos.isEmpty(); }

   

   
    public void registrarAccion(String accion) {
        pilaHistorial.push(accion);
    }

   
    public String verUltimaAccion() {
        if (pilaHistorial.isEmpty()) return null;
        return pilaHistorial.peek();
    }

  
    public String deshacerUltimaAccion() {
        if (pilaHistorial.isEmpty()) return null;
        return pilaHistorial.pop();
    }

    public Stack<String> getPilaHistorial() { return pilaHistorial; }

  

    public void registrarIncidencia(Incidencia inc) {
        incidencias.add(inc);
        registrarAccion("Incidencia: [" + inc.getTipo() + "] " + inc.getDescripcion());
    }

    public LinkedList<Incidencia> getIncidencias() { return incidencias; }

    public void listarIncidencias() {
        if (incidencias.isEmpty()) {
            System.out.println("  No hay incidencias.");
            return;
        }
       
        Iterator<Incidencia> it = incidencias.iterator();
        while (it.hasNext()) {
            System.out.println("  " + it.next());
        }
    }

    public ArrayList<Incidencia> buscarPorEquipo(String idEquipo) {
        ArrayList<Incidencia> resultado = new ArrayList<Incidencia>();
        Iterator<Incidencia> it = incidencias.iterator();
        while (it.hasNext()) {
            Incidencia i = it.next();
            if (i.getIdEquipo().equals(idEquipo)) resultado.add(i);
        }
        return resultado;
    }

    public ArrayList<Incidencia> buscarPorJugador(String idJugador) {
        ArrayList<Incidencia> resultado = new ArrayList<Incidencia>();
        Iterator<Incidencia> it = incidencias.iterator();
        while (it.hasNext()) {
            Incidencia i = it.next();
            if (idJugador.equals(i.getIdJugador())) resultado.add(i);
        }
        return resultado;
    }

    public void aplicarSancion(String idJugador) {
        for (PersonaLiga p : personas) {
            if (p.getIdentificador().equals(idJugador) && p instanceof Jugador) {
                Jugador j = (Jugador) p;
                j.setSancionado(true);
                registrarAccion("Sancion a: " + j.getNickname());
                return;
            }
        }
    }

    public void levantarSancion(String idJugador) {
        for (PersonaLiga p : personas) {
            if (p.getIdentificador().equals(idJugador) && p instanceof Jugador) {
                Jugador j = (Jugador) p;
                j.setSancionado(false);
                registrarAccion("Sancion levantada a: " + j.getNickname());
                return;
            }
        }
    }

   

    public void generarCalendario() {
        int numEquipos = equipos.size();
        if (numEquipos < 2) {
            System.out.println("  Necesitas al menos 2 equipos.");
            return;
        }
        matrizCalendario = new int[MAX_JORNADAS][numEquipos];
        jornadasGeneradas = 0;

        int jornada = 0;
        for (int i = 0; i < numEquipos && jornada < MAX_JORNADAS; i++) {
            for (int j = i + 1; j < numEquipos && jornada < MAX_JORNADAS; j++) {
                String id = "P" + (jornada + 1) + "-" + i + "v" + j;
                try {
                    if (!idsPartidos.contains(id)) {
                        Partido p = new Partido(id, jornada + 1,
                            equipos.get(i), equipos.get(j));
                        crearPartido(p);
                        jornada++;
                    }
                } catch (PartidoInvalidoException e) {
                    System.out.println("  Error: " + e.getMessage());
                }
            }
        }
        jornadasGeneradas = jornada;
        registrarAccion("Calendario generado: " + jornadasGeneradas + " jornadas");
    }

    public void actualizarMatrizCalendario() {
        for (Partido p : partidos) {
            if (p.isDisputado()) {
                int fila = p.getJornada() - 1;
                int colLocal = equipos.indexOf(p.getEquipoLocal());
                int colVisit = equipos.indexOf(p.getEquipoVisitante());
                if (fila >= 0 && fila < MAX_JORNADAS) {
                    if (colLocal >= 0)
                        matrizCalendario[fila][colLocal] = p.getPuntuacionLocal();
                    if (colVisit >= 0)
                        matrizCalendario[fila][colVisit] = p.getPuntuacionVisitante();
                }
            }
        }
    }

    public void mostrarCalendario() {
        actualizarMatrizCalendario();
        System.out.println("\n=== CALENDARIO ===");
        System.out.printf("%-15s", "");
        for (int e = 0; e < equipos.size(); e++) {
            String n = equipos.get(e).getNombre();
            System.out.printf("%-12s", n.length() > 10 ? n.substring(0, 10) : n);
        }
        System.out.println();
        System.out.println("-".repeat(15 + equipos.size() * 12));
        for (int j = 0; j < jornadasGeneradas; j++) {
            System.out.printf("%-15s", "Jornada " + (j + 1));
            for (int e = 0; e < equipos.size(); e++) {
                System.out.printf("%-12s", matrizCalendario[j][e]);
            }
            System.out.println();
        }
    }

    public void mostrarJornada(int jornada) {
        System.out.println("\n=== Jornada " + jornada + " ===");
        boolean hay = false;
        for (Partido p : partidos) {
            if (p.getJornada() == jornada) {
                System.out.println("  " + p);
                hay = true;
            }
        }
        if (!hay) System.out.println("  No hay partidos en esa jornada.");
    }

    

    public ArrayList<Equipo> generarClasificacion() {
        ArrayList<Equipo> clasificacion = new ArrayList<Equipo>(equipos);
        // Ordenar con Comparator (criterios enunciado 5.14)
        Collections.sort(clasificacion, (a, b) -> {
            if (b.getVictorias() != a.getVictorias())
                return b.getVictorias() - a.getVictorias();
            if (b.getDiferenciaPuntos() != a.getDiferenciaPuntos())
                return b.getDiferenciaPuntos() - a.getDiferenciaPuntos();
            return a.getNombre().compareTo(b.getNombre());
        });
        return clasificacion;
    }

    public String getNombre() { return nombre; }
    public int getJornadasGeneradas() { return jornadasGeneradas; }
}
