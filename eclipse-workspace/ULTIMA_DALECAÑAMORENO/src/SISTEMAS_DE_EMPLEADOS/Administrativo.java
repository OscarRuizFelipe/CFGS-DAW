package SISTEMAS_DE_EMPLEADOS;

public class Administrativo extends Empleado {

    private int documentosProcesados;

    public Administrativo(int id, String nombre, Departamento departamento,
                          Tarea[] tareas, int documentosProcesados) {
        super(id, nombre, departamento, tareas);
        this.documentosProcesados = documentosProcesados;
    }

    @Override
    public double calcularProductividad() {
        return totalHoras() + documentosProcesados * 1.2;
    }

    @Override
    public boolean necesitaFormacion() {
        return totalHoras() < 20;
    }
}