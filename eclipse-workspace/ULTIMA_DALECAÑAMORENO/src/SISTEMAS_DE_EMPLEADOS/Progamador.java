package SISTEMAS_DE_EMPLEADOS;

public class Progamador extends Empleado {

    private int bugsResueltos;

    public Progamador(int id, String nombre, Departamento departamento,
                       Tarea[] tareas, int bugsResueltos) {
        super(id, nombre, departamento, tareas);
        this.bugsResueltos = bugsResueltos;
    }

    @Override
    public double calcularProductividad() {
        return totalHoras() + bugsResueltos * 2;
    }

    @Override
    public boolean necesitaFormacion() {
        return dificultadMedia() > 7;
    }
}