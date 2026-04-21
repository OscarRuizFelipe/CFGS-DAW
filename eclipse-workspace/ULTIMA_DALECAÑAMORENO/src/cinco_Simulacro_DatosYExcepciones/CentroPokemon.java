package cinco_Simulacro_DatosYExcepciones;
//Clase abstracta que representa un Centro Pokémon general
//Implementa la interfaz Auditables
public abstract class CentroPokemon implements Auditables {

 // Código identificador del centro
 private String codigo;

 // Región donde se encuentra el centro
 private Region region;

 // Enfermero responsable del centro
 private EnfermeroPokemon enfermero;

 // Matriz de pokémon atendidos (5 días x 2 turnos)
 private int[][] pokemonsAtendidos;

 // Matriz de emergencias registradas (5 días x 2 turnos)
 private int[][] emergencias;

 // Constructor de la clase
 public CentroPokemon(String codigo, Region region, EnfermeroPokemon enfermero,
                      int[][] pokemonsAtendidos, int[][] emergencias) {
     this.codigo = codigo;
     this.region = region;
     this.enfermero = enfermero;
     this.pokemonsAtendidos = pokemonsAtendidos;
     this.emergencias = emergencias;
 }

 // Getter del código
 public String getCodigo() {
     return codigo;
 }

 // Getter de la región
 public Region getRegion() {
     return region;
 }

 // Getter del enfermero
 public EnfermeroPokemon getEnfermero() {
     return enfermero;
 }

 // Getter de la matriz de pokemons
 public int[][] getPokemonsAtendidos() {
     return pokemonsAtendidos;
 }

 // Getter de la matriz de emergencias
 public int[][] getEmergencias() {
     return emergencias;
 }

 // Calcula el total de pokémons atendidos en la semana
 public int totalPokemonsAtendidos() {
     int total = 0;

     for (int i = 0; i < pokemonsAtendidos.length; i++) {
         for (int j = 0; j < pokemonsAtendidos[i].length; j++) {
             total += pokemonsAtendidos[i][j];
         }
     }

     return total;
 }

 // Calcula el total de emergencias en la semana
 public int totalEmergencias() {
     int total = 0;

     for (int i = 0; i < emergencias.length; i++) {
         for (int j = 0; j < emergencias[i].length; j++) {
             total += emergencias[i][j];
         }
     }

     return total;
 }

 // Calcula los pokémon atendidos en un día concreto
 public int pokemonsPorDia(int dia) {
     int total = 0;

     for (int j = 0; j < pokemonsAtendidos[dia].length; j++) {
         total += pokemonsAtendidos[dia][j];
     }

     return total;
 }

 // Calcula las emergencias en un día concreto
 public int emergenciasPorDia(int dia) {
     int total = 0;

     for (int j = 0; j < emergencias[dia].length; j++) {
         total += emergencias[dia][j];
     }

     return total;
 }

 // Calcula la tasa de emergencias en porcentaje
 public double tasaEmergencias() {
     int totalPokemons = totalPokemonsAtendidos();
     int totalEmergencias = totalEmergencias();

     // Si no hay pokémon atendidos, la tasa es 0
     if (totalPokemons == 0) {
         return 0;
     }

     return (totalEmergencias * 100.0) / totalPokemons;
 }

 // Muestra el resumen semanal del centro
 public void mostrarResumenSemanal() {

     String[] dias = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes"};

     System.out.println("Resumen semanal del centro " + codigo + ":");

     for (int i = 0; i < pokemonsAtendidos.length; i++) {
         System.out.println(
             dias[i] +
             " -> Pokémons mañana: " + pokemonsAtendidos[i][0] +
             ", Pokémons tarde: " + pokemonsAtendidos[i][1] +
             ", Emergencias mañana: " + emergencias[i][0] +
             ", Emergencias tarde: " + emergencias[i][1]
         );
     }
 }

 // Método abstracto que calcula el índice de salud del centro
 public abstract double calcularIndiceSalud();

 // Método toString con información básica del centro
 @Override
 public String toString() {
     return "Código: " + codigo +
            ", Región: " + region +
            ", Enfermero: [" + enfermero + "]";
 }
}