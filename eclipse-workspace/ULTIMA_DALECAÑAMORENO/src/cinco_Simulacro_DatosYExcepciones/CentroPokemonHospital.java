package cinco_Simulacro_DatosYExcepciones;
//Clase que representa un Centro Pokémon tipo Hospital
//Hereda de CentroPokemon
public class CentroPokemonHospital extends CentroPokemon {

 // Número de camas disponibles en el hospital
 private int camasDisponibles;

 // Constructor de la clase
 public CentroPokemonHospital(String codigo, Region region, EnfermeroPokemon enfermero,
                              int[][] pokemonsAtendidos, int[][] emergencias,
                              int camasDisponibles) {

     // Llamada al constructor de la clase padre
     super(codigo, region, enfermero, pokemonsAtendidos, emergencias);

     this.camasDisponibles = camasDisponibles;
 }

 // Getter de camas disponibles
 public int getCamasDisponibles() {
     return camasDisponibles;
 }

 // Cálculo del índice de salud del hospital
 @Override
 public double calcularIndiceSalud() {

     return totalPokemonsAtendidos()
          - totalEmergencias()
          + (camasDisponibles * 2);
 }

 // Método que determina si necesita auditoría
 @Override
 public boolean requiereAuditoria() {

     // Si la tasa de emergencias supera el 25%
     if (tasaEmergencias() > 25) {
         return true;
     }

     // Si algún día tiene menos de 6 pokémon atendidos
     for (int dia = 0; dia < 5; dia++) {
         if (pokemonsPorDia(dia) < 6) {
             return true;
         }
     }

     // Si las emergencias totales son mayores a 20
     if (totalEmergencias() > 20) {
         return true;
     }

     return false;
 }

 // Método toString con información del hospital
 @Override
 public String toString() {
     return super.toString() +
            ", Tipo: CentroPokemonHospital" +
            ", Camas disponibles: " + camasDisponibles;
 }
}