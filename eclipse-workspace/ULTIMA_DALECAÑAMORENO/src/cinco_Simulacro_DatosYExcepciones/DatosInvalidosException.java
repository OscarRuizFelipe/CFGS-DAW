package cinco_Simulacro_DatosYExcepciones;
//Excepción personalizada para controlar datos incorrectos
public class DatosInvalidosException extends Exception {

 // UID de serialización (requerido por Java para excepciones)
 private static final long serialVersionUID = 1L;

 // Constructor que recibe el mensaje de error
 public DatosInvalidosException(String mensaje) {
     super(mensaje);
 }
}