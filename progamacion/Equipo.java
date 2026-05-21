package modelo;

import excepciones.RolNoDisponibleException;
import excepciones.JugadorSancionadoException;
import java.util.ArrayList;

public class Equipo {

    private String nombre;
    private String ciudad;
    private Entrenador entrenador;
    private double presupuesto;
    private int victorias;
    private int derrotas;
    private int puntosAFavor;
    private int puntosEnContra;

    private Jugador[] titulares;


    private ArrayList<Jugador> suplentes;

    public Equipo(String nombre, String ciudad, double presupuesto) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.presupuesto = presupuesto;
        this.victorias = 0;
        this.derrotas = 0;
        this.puntosAFavor = 0;
        this.puntosEnContra = 0;
        this.titulares = new Jugador[5];
        this.suplentes = new ArrayList<Jugador>();
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }
    public Entrenador getEntrenador() { return entrenador; }
    public void setEntrenador(Entrenador entrenador) { this.entrenador = entrenador; }
    public double getPresupuesto() { return presupuesto; }
    public void setPresupuesto(double presupuesto) { this.presupuesto = presupuesto; }
    public int getVictorias() { return victorias; }
    public void setVictorias(int victorias) { this.victorias = victorias; }
    public int getDerrotas() { return derrotas; }
    public void setDerrotas(int derrotas) { this.derrotas = derrotas; }
    public int getPuntosAFavor() { return puntosAFavor; }
    public void setPuntosAFavor(int puntosAFavor) { this.puntosAFavor = puntosAFavor; }
    public int getPuntosEnContra() { return puntosEnContra; }
    public void setPuntosEnContra(int puntosEnContra) { this.puntosEnContra = puntosEnContra; }
    public Jugador[] getTitulares() { return titulares; }
    public ArrayList<Jugador> getSuplentes() { return suplentes; }

    public int getDiferenciaPuntos() {
        return puntosAFavor - puntosEnContra;
    }

    
    private int indicePorRol(Rol rol) {
        switch (rol) {
            case TOP:     return 0;
            case JUNGLE:  return 1;
            case MID:     return 2;
            case ADC:     return 3;
            case SUPPORT: return 4;
            default:      return -1;
        }
    }

    
    public void ficharTitular(Jugador jugador) throws RolNoDisponibleException {
        int indice = indicePorRol(jugador.getRol());
        if (titulares[indice] != null) {
            throw new RolNoDisponibleException(
                "Ya hay un titular con el rol " + jugador.getRol() + " en " + nombre);
        }
        titulares[indice] = jugador;
    }
    

    public void ficharSuplente(Jugador jugador) {
        suplentes.add(jugador);
    }


    // Eliminar titular del array (enunciado 5.3)
    public boolean eliminarTitular(String idJugador) {
        for (int i = 0; i < titulares.length; i++) {
            if (titulares[i] != null && titulares[i].getIdentificador().equals(idJugador)) {
                titulares[i] = null;
                return true;
            }
        }
        return false;
    }

    
    public boolean eliminarSuplente(String idJugador) {
        for (int i = 0; i < suplentes.size(); i++) {
            if (suplentes.get(i).getIdentificador().equals(idJugador)) {
                suplentes.remove(i);
                return true;
            }
        }
        return false;
    }

   
    public boolean promoverSuplente(String idJugador) throws RolNoDisponibleException {
        for (int i = 0; i < suplentes.size(); i++) {
            Jugador s = suplentes.get(i);
            if (s.getIdentificador().equals(idJugador)) {
                int indice = indicePorRol(s.getRol());
                if (titulares[indice] != null) {
                    throw new RolNoDisponibleException(
                        "Ya hay titular con el rol " + s.getRol());
                }
                titulares[indice] = s;
                suplentes.remove(i);
                return true;
            }
        }
        return false;
    }

    
    public boolean sustituirTitular(String idTitular, String idSuplente)
            throws RolNoDisponibleException {
        // Buscar el suplente
        Jugador suplente = null;
        int posSuplente = -1;
        for (int i = 0; i < suplentes.size(); i++) {
            if (suplentes.get(i).getIdentificador().equals(idSuplente)) {
                suplente = suplentes.get(i);
                posSuplente = i;
                break;
            }
        }
        if (suplente == null) return false;

        
        int indiceTitular = -1;
        for (int i = 0; i < titulares.length; i++) {
            if (titulares[i] != null && titulares[i].getIdentificador().equals(idTitular)) {
                indiceTitular = i;
                break;
            }
        }
        if (indiceTitular == -1) return false;

        Jugador titularActual = titulares[indiceTitular];

      
        if (suplente.getRol() != titularActual.getRol()) {
            throw new RolNoDisponibleException(
                "El suplente tiene rol " + suplente.getRol()
                + " y el titular tiene rol " + titularActual.getRol());
        }

        titulares[indiceTitular] = suplente;
        suplentes.remove(posSuplente);
        suplentes.add(titularActual);
        return true;
    }

    // Generar convocatoria válida para un partido (enunciado 5.13)
    public ArrayList<Jugador> generarConvocatoria() throws JugadorSancionadoException {
        ArrayList<Jugador> convocados = new ArrayList<Jugador>();

        for (int i = 0; i < titulares.length; i++) {
            Jugador t = titulares[i];
            if (t == null) {
                throw new JugadorSancionadoException(
                    "Falta titular en la posicion " + i + " del equipo " + nombre);
            }
            if (t.isSancionado()) {
                
                Jugador sustituto = null;
                for (int j = 0; j < suplentes.size(); j++) {
                    Jugador s = suplentes.get(j);
                    if (s.getRol() == t.getRol() && !s.isSancionado()) {
                        sustituto = s;
                        break;
                    }
                }
                if (sustituto == null) {
                    throw new JugadorSancionadoException(
                        "El jugador " + t.getNickname() + " esta sancionado y no hay"
                        + " suplente disponible para el rol " + t.getRol());
                }
                convocados.add(sustituto);
            } else {
                convocados.add(t);
            }
        }



        if (convocados.size() != 5) {
            throw new JugadorSancionadoException(
                "La convocatoria de " + nombre + " no tiene 5 jugadores.");
        }
        return convocados;
    }

    
    public double calcularCosteTotalEquipo() {
        double total = 0;
        if (entrenador != null) total += entrenador.calcularCosteMensual();
        for (int i = 0; i < titulares.length; i++) {
            if (titulares[i] != null) total += titulares[i].calcularCosteMensual();
        }
        for (int i = 0; i < suplentes.size(); i++) {
            total += suplentes.get(i).calcularCosteMensual();
        }
        return total;
    }

    
    public void mostrarPlantilla() {
        System.out.println("=== Plantilla de " + nombre + " ===");
        System.out.println("Entrenador: "
            + (entrenador != null ? entrenador.toString() : "Sin asignar"));
        System.out.println("--- Titulares ---");
        String[] nombresRol = {"TOP", "JUNGLE", "MID", "ADC", "SUPPORT"};
        for (int i = 0; i < titulares.length; i++) {
            if (titulares[i] != null) {
                System.out.println("  [" + nombresRol[i] + "] " + titulares[i]);
            } else {
                System.out.println("  [" + nombresRol[i] + "] Vacante");
            }
        }
        System.out.println("--- Suplentes ---");
        if (suplentes.isEmpty()) {
            System.out.println("  Sin suplentes");
        } else {
            for (int i = 0; i < suplentes.size(); i++) {
                System.out.println("  " + suplentes.get(i));
            }
        }
        System.out.printf("Coste total mensual: %.2f euros%n", calcularCosteTotalEquipo());
    }

    @Override
    public String toString() {
        return nombre + " (" + ciudad + ")"
            + " | V: " + victorias + " D: " + derrotas
            + " | PF: " + puntosAFavor + " PC: " + puntosEnContra
            + " | Dif: " + getDiferenciaPuntos();
    }
}
