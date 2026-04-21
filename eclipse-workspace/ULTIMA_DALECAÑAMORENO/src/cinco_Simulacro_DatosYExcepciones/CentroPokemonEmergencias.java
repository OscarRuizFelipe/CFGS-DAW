package cinco_Simulacro_DatosYExcepciones;
//Clase que representa un Centro Pokémon de tipo Emergencias
//Hereda de CentroPokemon
public class CentroPokemonEmergencias extends CentroPokemon {

 // Número de rescates especiales realizados
 private int rescatesEspeciales;

 // Constructor de la clase
 public CentroPokemonEmergencias(String codigo, Region region, EnfermeroPokemon enfermero,
                                 int[][] pokemonsAtendidos, int[][] emergencias,
                                 int rescatesEspeciales) {

     // Llamada al constructor de la clase padre
     super(codigo, region, enfermero, pokemonsAtendidos, emergencias);

     this.rescatesEspeciales = rescatesEspeciales;
 }

 // Getter de rescates especiales
 public int getRescatesEspeciales() {
     return rescatesEspeciales;
 }

 // Cálculo del índice de salud del centro de emergencias
 @Override
 public double calcularIndiceSalud() {

     return totalPokemonsAtendidos()
          - (totalEmergencias() * 0.7)
          + (rescatesEspeciales * 3);
 }

 // Método que determina si el centro necesita auditoría
 @Override
 public boolean requiereAuditoria() {

     // Recorremos toda la matriz para comprobar condiciones por turno
     for (int i = 0; i < getPokemonsAtendidos().length; i++) {
         for (int j = 0; j < getPokemonsAtendidos()[i].length; j++) {

             // Si algún turno tiene 0 pokémon atendidos
             if (getPokemonsAtendidos()[i][j] == 0) {
                 return true;
             }

             // Si hay más emergencias que pokémon en ese turno
             if (getEmergencias()[i][j] > getPokemonsAtendidos()[i][j]) {
                 return true;
             }
         }
     }

     // Si el total de pokémon atendidos es menor a 30
     if (totalPokemonsAtendidos() < 30) {
         return true;
     }

     return false;
 }

 // Método toString con información del centro de emergencias
 @Override
 public String toString() {
     return super.toString() +
            ", Tipo: CentroPokemonEmergencias" +
            ", Rescates especiales: " + rescatesEspeciales;
 }
}