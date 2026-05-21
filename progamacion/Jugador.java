package modelo;

public class Jugador extends PersonaLiga implements Entrenable {

    private Rol rol;
    private int nivelMecanico;
    private int nivelEstrategico;
    private int partidasJugadas;
    private int mvpTotales;
    private boolean sancionado;

    public Jugador(String identificador, String nombre, String nickname,
                   int edad, double salarioBase,
                   Rol rol, int nivelMecanico, int nivelEstrategico) {
        super(identificador, nombre, nickname, edad, salarioBase);
        this.rol = rol;
        this.nivelMecanico = nivelMecanico;
        this.nivelEstrategico = nivelEstrategico;
        this.partidasJugadas = 0;
        this.mvpTotales = 0;
        this.sancionado = false;
    }

    public Rol getRol() { return rol; }
    public void setRol(Rol rol) { this.rol = rol; }

    public int getNivelMecanico() { return nivelMecanico; }
    public void setNivelMecanico(int nivelMecanico) { this.nivelMecanico = nivelMecanico; }

    public int getNivelEstrategico() { return nivelEstrategico; }
    public void setNivelEstrategico(int nivelEstrategico) { this.nivelEstrategico = nivelEstrategico; }

    public int getPartidasJugadas() { return partidasJugadas; }
    public void setPartidasJugadas(int partidasJugadas) { this.partidasJugadas = partidasJugadas; }
    public void incrementarPartidas() { this.partidasJugadas++; }

    public int getMvpTotales() { return mvpTotales; }
    public void setMvpTotales(int mvpTotales) { this.mvpTotales = mvpTotales; }
    public void incrementarMVP() { this.mvpTotales++; }

    public boolean isSancionado() { return sancionado; }
    public void setSancionado(boolean sancionado) { this.sancionado = sancionado; }


    @Override
    public double calcularCosteMensual() {
        double bonus = (nivelMecanico + nivelEstrategico) * 50.0;
        return getSalarioBase() + bonus;
    }

    
    @Override
    public void entrenar() {
        if (nivelMecanico < 10) nivelMecanico++;
        if (nivelEstrategico < 10) nivelEstrategico++;
        System.out.println("  " + getNickname() + " ha entrenado."
                + " Mecanico: " + nivelMecanico
                + " | Estrategico: " + nivelEstrategico);
    }

    @Override
    public double calcularRendimiento() {
        if (partidasJugadas == 0) return 0.0;
        double base = (nivelMecanico + nivelEstrategico) / 2.0;
        double bonusMVP = (double) mvpTotales / partidasJugadas * 10;
        return Math.min(10.0, base + bonusMVP);
    }

    @Override
    public void mostrarResumen() {
        super.mostrarResumen();
        System.out.println("  Rol: " + rol
                + " | Mecanico: " + nivelMecanico
                + " | Estrategico: " + nivelEstrategico
                + " | Partidas: " + partidasJugadas
                + " | MVPs: " + mvpTotales
                + " | Sancionado: " + (sancionado ? "SI" : "No")
                + " | Rendimiento: " + String.format("%.2f", calcularRendimiento()));
    }

    @Override
    public String toString() {
        return super.toString() + " | Rol: " + rol
                + (sancionado ? " [SANCIONADO]" : "");
    }
}
