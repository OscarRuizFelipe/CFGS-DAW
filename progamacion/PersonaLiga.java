package modelo;

public abstract class PersonaLiga {

    private String identificador;
    private String nombre;
    private String nickname;
    private int edad;
    private double salarioBase;

    public PersonaLiga(String identificador, String nombre, String nickname,
                       int edad, double salarioBase) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.nickname = nickname;
        this.edad = edad;
        this.salarioBase = salarioBase;
    }

    public String getIdentificador() { return identificador; }
    public void setIdentificador(String identificador) { this.identificador = identificador; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }

    public double getSalarioBase() { return salarioBase; }
    public void setSalarioBase(double salarioBase) { this.salarioBase = salarioBase; }

    // calcularCosteMensual() es abstracto: cada subclase lo implementa de forma distinta
    public abstract double calcularCosteMensual();

    public void mostrarResumen() {
        System.out.println("  ID: " + identificador + " | Nombre: " + nombre
                + " | Nickname: " + nickname + " | Edad: " + edad
                + " | Salario base: " + salarioBase + " euros");
    }

    @Override
    public String toString() {
        return "[" + identificador + "] " + nombre + " (" + nickname + ")"
                + " - Edad: " + edad
                + " - Coste mensual: " + String.format("%.2f", calcularCosteMensual()) + " euros";
    }
}
