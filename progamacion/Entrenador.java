package modelo;

public class Entrenador extends PersonaLiga {

    private int experiencia;
    private String especialidad;
    private int victoriasTotales;

    public Entrenador(String identificador, String nombre, String nickname,
                      int edad, double salarioBase,
                      int experiencia, String especialidad) {
        super(identificador, nombre, nickname, edad, salarioBase);
        this.experiencia = experiencia;
        this.especialidad = especialidad;
        this.victoriasTotales = 0;
    }

    public int getExperiencia() { return experiencia; }
    public void setExperiencia(int experiencia) { this.experiencia = experiencia; }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    public int getVictoriasTotales() { return victoriasTotales; }
    public void setVictoriasTotales(int victoriasTotales) { this.victoriasTotales = victoriasTotales; }
    public void incrementarVictorias() { this.victoriasTotales++; }

    
    @Override
    public double calcularCosteMensual() {
        double bonusExp = experiencia * 150.0;
        double bonusVic = victoriasTotales * 20.0;
        return getSalarioBase() + bonusExp + bonusVic;
    }

    @Override
    public void mostrarResumen() {
        super.mostrarResumen();
        System.out.println("  Experiencia: " + experiencia + " años"
                + " | Especialidad: " + especialidad
                + " | Victorias: " + victoriasTotales);
    }

    @Override
    public String toString() {
        return super.toString()
                + " | Entrenador - Exp: " + experiencia + " años"
                + " | " + especialidad;
    }
}
