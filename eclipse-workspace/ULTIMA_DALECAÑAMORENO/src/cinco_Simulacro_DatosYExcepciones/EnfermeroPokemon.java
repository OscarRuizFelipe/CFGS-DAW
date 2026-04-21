package cinco_Simulacro_DatosYExcepciones;
//Clase que representa al enfermero/a Pokémon responsable del centro
public class EnfermeroPokemon {

 // Identificador del enfermero
 private String id;

 // Nombre del enfermero
 private String nombre;

 // Años de experiencia del enfermero
 private int experiencia;

 // Constructor de la clase
 public EnfermeroPokemon(String id, String nombre, int experiencia) {
     this.id = id;
     this.nombre = nombre;
     this.experiencia = experiencia;
 }

 // Getter del id
 public String getId() {
     return id;
 }

 // Getter del nombre
 public String getNombre() {
     return nombre;
 }

 // Getter de la experiencia
 public int getExperiencia() {
     return experiencia;
 }

 // Método toString para mostrar la información del enfermero
 @Override
 public String toString() {
     return "ID: " + id + ", Nombre: " + nombre + ", Experiencia: " + experiencia + " años";
 }
}