package modelo;

public class Partido {

    private String identificador;
    private int jornada;
    private Equipo equipoLocal;
    private Equipo equipoVisitante;
    private int puntuacionLocal;
    private int puntuacionVisitante;
    private Jugador mvp;
    private boolean disputado;

    public Partido(String identificador, int jornada,
                   Equipo equipoLocal, Equipo equipoVisitante) {
        this.identificador = identificador;
        this.jornada = jornada;
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.puntuacionLocal = 0;
        this.puntuacionVisitante = 0;
        this.mvp = null;
        this.disputado = false;
    }

    public String getIdentificador() { return identificador; }
    public int getJornada() { return jornada; }
    public Equipo getEquipoLocal() { return equipoLocal; }
    public Equipo getEquipoVisitante() { return equipoVisitante; }
    public int getPuntuacionLocal() { return puntuacionLocal; }
    public void setPuntuacionLocal(int puntuacionLocal) { this.puntuacionLocal = puntuacionLocal; }
    public int getPuntuacionVisitante() { return puntuacionVisitante; }
    public void setPuntuacionVisitante(int puntuacionVisitante) { this.puntuacionVisitante = puntuacionVisitante; }
    public Jugador getMvp() { return mvp; }
    public void setMvp(Jugador mvp) { this.mvp = mvp; }
    public boolean isDisputado() { return disputado; }

   
    public Equipo calcularGanador() {
        if (!disputado) return null;
        if (puntuacionLocal > puntuacionVisitante) return equipoLocal;
        if (puntuacionVisitante > puntuacionLocal) return equipoVisitante;
        return null; // empate
    }

    
    public void registrarResultado(int pLocal, int pVisitante, Jugador jugadorMVP) {
        this.puntuacionLocal = pLocal;
        this.puntuacionVisitante = pVisitante;
        this.mvp = jugadorMVP;
        this.disputado = true;
        actualizarEstadisticas();
    }

    private void actualizarEstadisticas() {
        
        equipoLocal.setPuntosAFavor(equipoLocal.getPuntosAFavor() + puntuacionLocal);
        equipoLocal.setPuntosEnContra(equipoLocal.getPuntosEnContra() + puntuacionVisitante);
        equipoVisitante.setPuntosAFavor(equipoVisitante.getPuntosAFavor() + puntuacionVisitante);
        equipoVisitante.setPuntosEnContra(equipoVisitante.getPuntosEnContra() + puntuacionLocal);

       
        Equipo ganador = calcularGanador();
        if (ganador == equipoLocal) {
            equipoLocal.setVictorias(equipoLocal.getVictorias() + 1);
            equipoVisitante.setDerrotas(equipoVisitante.getDerrotas() + 1);
            if (equipoLocal.getEntrenador() != null)
                equipoLocal.getEntrenador().incrementarVictorias();
        } else if (ganador == equipoVisitante) {
            equipoVisitante.setVictorias(equipoVisitante.getVictorias() + 1);
            equipoLocal.setDerrotas(equipoLocal.getDerrotas() + 1);
            if (equipoVisitante.getEntrenador() != null)
                equipoVisitante.getEntrenador().incrementarVictorias();
        }

        
        for (Jugador j : equipoLocal.getTitulares()) {
            if (j != null) j.incrementarPartidas();
        }
        for (Jugador j : equipoVisitante.getTitulares()) {
            if (j != null) j.incrementarPartidas();
        }

        
        if (mvp != null) mvp.incrementarMVP();
    }

    @Override
    public String toString() {
        String resultado = disputado
                ? puntuacionLocal + " - " + puntuacionVisitante
                : "Pendiente";
        return "Partido [" + identificador + "] J" + jornada + ": "
                + equipoLocal.getNombre() + " vs " + equipoVisitante.getNombre()
                + " | " + resultado
                + (mvp != null ? " | MVP: " + mvp.getNickname() : "");
    }
}
