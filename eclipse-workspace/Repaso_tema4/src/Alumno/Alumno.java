package Alumno;

public class Alumno implements Comparable<Alumno> {

    private String nombre;
    private double nota;

    // Constructor con validación
    public Alumno(String nombre, double nota) throws NotaInvalidaException {

        if (nota < 0 || nota > 10) {
            throw new NotaInvalidaException("La nota debe estar entre 0 y 10");
        }

        this.nombre = nombre;
        this.nota = nota;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public double getNota() {
        return nota;
    }

    // toString
    @Override
    public String toString() {
        return nombre + " - Nota: " + nota;
    }

    // Comparable -> ordenar por nota (de menor a mayor)
    @Override
    public int compareTo(Alumno otro) {

        if (this.nota < otro.nota) {
            return -1;
        } else if (this.nota > otro.nota) {
            return 1;
        } else {
            return 0;
        }
    }

    // Método para subir nota sin pasar de 10
    public void subirNota() {
        if (nota < 10) {
            nota++;
            if (nota > 10) {
                nota = 10;
            }
        }
    }
}