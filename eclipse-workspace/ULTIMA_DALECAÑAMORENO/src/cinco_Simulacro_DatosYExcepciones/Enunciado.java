/*SIMULACRO POO – UNIVERSO POKÉMON (SISTEMA DE CENTROS POKÉMON)

La Liga Pokémon quiere desarrollar un sistema para controlar el rendimiento de los Centros Pokémon de distintas regiones.

Cada centro registra semanalmente su actividad de lunes a viernes, en dos turnos diarios:

mañana
tarde

Para cada turno se guarda:

número de Pokémon atendidos
número de emergencias registradas

Además, cada centro tiene un Enfermero/a Pokémon responsable.

El sistema debe distinguir dos tipos de centros:

CentroPokemonHospital
CentroPokemonEmergencias

El programa debe usar:
clase abstracta, interfaz, enum, herencia, polimorfismo, matrices 5x2, arrays de objetos, entrada por teclado (Scanner), try/catch y excepciones personalizadas.

🚨 SE PIDE
1) ENUMERADO

Crear un enum llamado Region con los valores:

KANTO
JOHTO
HOENN
SINNOH
UNOVA
2) EXCEPCIÓN PERSONALIZADA

Crear una excepción llamada DatosInvalidosException.

Se lanzará cuando:

se introduzcan valores negativos en las matrices
o haya errores de entrada por teclado
3) CLASE AUXILIAR

Crear la clase EnfermeroPokemon con:

Atributos:

String id
String nombre
int experiencia

Métodos:

constructor
getters
toString()
4) INTERFAZ

Crear la interfaz Auditables con el método:

boolean requiereAuditoria();
5) CLASE ABSTRACTA

Crear la clase abstracta CentroPokemon que implementa Auditables.

Atributos:
String codigo
Region region
EnfermeroPokemon enfermero
int[][] pokemonsAtendidos (5x2)
int[][] emergencias (5x2)
Métodos obligatorios:
constructor
getters
toString()
Métodos de cálculo:
int totalPokemonsAtendidos()
int totalEmergencias()
int pokemonsPorDia(int dia)
int emergenciasPorDia(int dia)
double tasaEmergencias()

Fórmula:
(totalEmergencias * 100.0) / totalPokemonsAtendidos
Si totalPokemonsAtendidos = 0 → 0

Método:
void mostrarResumenSemanal()

Debe mostrar por cada día:

pokemons mañana y tarde
emergencias mañana y tarde
Método abstracto:
double calcularIndiceSalud();
6) SUBCLASES
🏥 CentroPokemonHospital

Atributo:

int camasDisponibles

Índice de salud:
(totalPokemonsAtendidos - totalEmergencias + camasDisponibles * 2)

Auditoría si:

tasa emergencias > 25%
algún día tiene menos de 6 pokemons atendidos
emergencias totales > 20
🚨 CentroPokemonEmergencias

Atributo:

int rescatesEspeciales

Índice de salud:
(totalPokemonsAtendidos - totalEmergencias * 0.7 + rescatesEspeciales * 3)

Auditoría si:

algún turno tiene 0 pokemons
emergencias > pokemons en algún turno
total pokemons < 30
7) PROGRAMA PRINCIPAL

En la clase Main:

Debes pedir TODOS los datos por teclado usando Scanner:

datos del enfermero
código del centro
región
matrices 5x2 de pokemons y emergencias
atributos propios
⚠️ IMPORTANTE

Debes usar:

try/catch
excepción personalizada DatosInvalidosException
8) ARRAY POLIMÓRFICO

Crear:

CentroPokemon[] centros = new CentroPokemon[6];

3 CentroPokemonHospital
3 CentroPokemonEmergencias
9) FUNCIONALIDADES

a) Mostrar todos los centros con:

datos generales
enfermero
resumen semanal
totales
tasa de emergencias
índice de salud
auditoría

b) Centro con mayor índice de salud

c) Centro con peor tasa de emergencias

d) Contar tipos con instanceof

e) Medias globales por turno:

pokemons mañana
pokemons tarde
emergencias mañana
emergencias tarde

f) Día más crítico (más emergencias totales)

g) Centro más equilibrado (menor diferencia entre pokemons y emergencias)

h) Enfermeros con experiencia > 10 años

i) Centros destacados:

más de 40 pokemons
menos de 10 emergencias
no requieren auditoría
 * 
 * 
 * 
 * */
