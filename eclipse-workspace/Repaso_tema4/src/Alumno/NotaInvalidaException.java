package Alumno;

// Excepción personalizada para cuando la nota no es válida
public class NotaInvalidaException extends Exception {

    public NotaInvalidaException(String mensaje) {
        super(mensaje);
    }
}