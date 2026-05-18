package SISTEMAS_DE_EMPLEADOS;

public abstract class Empleado implements Evaluable {

    protected int id;
    protected String nombre;
    protected Departamento departamento;
    protected Tarea[] tareas;

    public Empleado(int id, String nombre, Departamento departamento, Tarea[] tareas) {
        this.id = id;
        this.nombre = nombre;
        this.departamento = departamento;
        this.tareas = tareas;
    }

    public String getNombre() {
        return nombre;
    }

    public Tarea[] getTareas() {
        return tareas;
    }

    // total horas
    public int totalHoras() {
        int suma = 0;

        for (Tarea t : tareas) {
            suma += t.getHoras();
        }

        return suma;
    }

    // dificultad media
    public double dificultadMedia() {
        int suma = 0;

        for (Tarea t : tareas) {
            suma += t.getDificultad();
        }

        return (double) suma / tareas.length;
    }

    // mostrar tareas
    public void mostrarTareas() {
        for (Tarea t : tareas) {
            System.out.println(t);
        }
    }

    // abstracto
    public abstract double calcularProductividad();

    @Override
    public String toString() {
        return "Empleado: " + nombre + " | Departamento: " + departamento;
    }
}