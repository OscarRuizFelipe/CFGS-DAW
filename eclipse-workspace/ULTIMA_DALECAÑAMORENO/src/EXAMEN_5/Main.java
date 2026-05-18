package EXAMEN_5;

public class Main {

    public static void main(String[] args) {

        Movimiento[] movimientosCharizard = {
            new Movimiento("Ascuas", Tipo.FUEGO, 40),
            new Movimiento("Lanzallamas", Tipo.FUEGO, 90),
            new Movimiento("Giro Fuego", Tipo.FUEGO, 35),
            new Movimiento("Tajo Aereo", Tipo.NORMAL, 75)
        };

        int[][] aprendizajeCharizard = {
            {1, 40},
            {24, 90},
            {15, 35},
            {36, 75}
        };

        Movimiento[] movimientosBlastoise = {
            new Movimiento("Pistola Agua", Tipo.AGUA, 40),
            new Movimiento("Hidrobomba", Tipo.AGUA, 110),
            new Movimiento("Placaje", Tipo.NORMAL, 40),
            new Movimiento("Burbuja", Tipo.AGUA, 30)
        };

        int[][] aprendizajeBlastoise = {
            {1, 40},
            {42, 110},
            {1, 40},
            {10, 30}
        };

        Movimiento[] movimientosVenusaur = {
            new Movimiento("Látigo Cepa", Tipo.PLANTA, 45),
            new Movimiento("Hoja Afilada", Tipo.PLANTA, 55),
            new Movimiento("Placaje", Tipo.NORMAL, 40),
            new Movimiento("Rayo Solar", Tipo.PLANTA, 120)
        };

        int[][] aprendizajeVenusaur = {
            {1, 45},
            {20, 55},
            {1, 40},
            {50, 120}
        };

        Movimiento[] movimientosPikachu = {
            new Movimiento("Impactrueno", Tipo.ELECTRICO, 40),
            new Movimiento("Chispa", Tipo.ELECTRICO, 65),
            new Movimiento("Ataque Rapido", Tipo.NORMAL, 40),
            new Movimiento("Trueno", Tipo.ELECTRICO, 110)
        };

        int[][] aprendizajePikachu = {
            {1, 40},
            {18, 65},
            {10, 40},
            {40, 110}
        };

        Pokemon[] pokemons = new Pokemon[4];

        pokemons[0] = new PokemonOfensivo(6, "Charizard", Tipo.FUEGO, 30,
                movimientosCharizard, aprendizajeCharizard, 2);

        pokemons[1] = new PokemonOfensivo(9, "Blastoise", Tipo.AGUA, 18,
                movimientosBlastoise, aprendizajeBlastoise, 1);

        pokemons[2] = new PokemonDefensivo(3, "Venusaur", Tipo.PLANTA, 25,
                movimientosVenusaur, aprendizajeVenusaur, 30);

        pokemons[3] = new PokemonDefensivo(25, "Pikachu", Tipo.ELECTRICO, 16,
                movimientosPikachu, aprendizajePikachu, 18);

        mostrarInformacionCompleta(pokemons);
        mostrarMayorIndiceCombate(pokemons);
        contarTipos(pokemons);
        mostrarMovimientoMasPotenteDisponible(pokemons);
        mostrarPokemonConMasMovimientosDisponibles(pokemons);
    }

    public static void mostrarInformacionCompleta(Pokemon[] pokemons) {
        System.out.println("=== INFORMACION DE TODOS LOS POKEMON ===");

        for (int i = 0; i < pokemons.length; i++) {
            System.out.println("----------------------------------------");
            System.out.println(pokemons[i]);
            System.out.println("Movimientos disponibles:");
            pokemons[i].mostrarMovimientosDisponibles();
            System.out.println("Cantidad de movimientos disponibles: " + pokemons[i].contarMovimientosDisponibles());
            System.out.println("Potencia media disponible: " + pokemons[i].calcularPotenciaMediaDisponible());
            System.out.println("Indice de combate: " + pokemons[i].calcularIndiceCombate());
            System.out.println("Necesita mejorar: " + pokemons[i].necesitaMejorar());
        }
    }

    public static void mostrarMayorIndiceCombate(Pokemon[] pokemons) {
        Pokemon mejor = pokemons[0];

        for (int i = 1; i < pokemons.length; i++) {
            if (pokemons[i].calcularIndiceCombate() > mejor.calcularIndiceCombate()) {
                mejor = pokemons[i];
            }
        }

        System.out.println("\n=== POKEMON CON MAYOR INDICE DE COMBATE ===");
        System.out.println("Nombre: " + mejor.getNombre());
        System.out.println("Indice: " + mejor.calcularIndiceCombate());

        if (mejor instanceof PokemonOfensivo) {
            System.out.println("Tipo real: PokemonOfensivo");
        } else if (mejor instanceof PokemonDefensivo) {
            System.out.println("Tipo real: PokemonDefensivo");
        }
    }

    public static void contarTipos(Pokemon[] pokemons) {
        int ofensivos = 0;
        int defensivos = 0;

        for (int i = 0; i < pokemons.length; i++) {
            if (pokemons[i] instanceof PokemonOfensivo) {
                ofensivos++;
            } else if (pokemons[i] instanceof PokemonDefensivo) {
                defensivos++;
            }
        }

        System.out.println("\n=== CONTEO DE TIPOS ===");
        System.out.println("Pokemon ofensivos: " + ofensivos);
        System.out.println("Pokemon defensivos: " + defensivos);
    }

    public static void mostrarMovimientoMasPotenteDisponible(Pokemon[] pokemons) {
        Movimiento mejorMovimiento = null;
        Pokemon pokemonDelMovimiento = null;
        int mayorPotencia = -1;

        for (int i = 0; i < pokemons.length; i++) {
            Movimiento[] movimientos = pokemons[i].getMovimientos();
            int[][] aprendizaje = pokemons[i].getAprendizaje();

            for (int j = 0; j < movimientos.length; j++) {
                if (aprendizaje[j][0] <= pokemons[i].getNivelActual()) {
                    if (aprendizaje[j][1] > mayorPotencia) {
                        mayorPotencia = aprendizaje[j][1];
                        mejorMovimiento = movimientos[j];
                        pokemonDelMovimiento = pokemons[i];
                    }
                }
            }
        }

        System.out.println("\n=== MOVIMIENTO MAS POTENTE DISPONIBLE ===");
        if (mejorMovimiento != null) {
            System.out.println("Movimiento: " + mejorMovimiento.getNombre());
            System.out.println("Pokemon: " + pokemonDelMovimiento.getNombre());
            System.out.println("Potencia: " + mayorPotencia);
        }
    }

    public static void mostrarPokemonConMasMovimientosDisponibles(Pokemon[] pokemons) {
        Pokemon mejor = pokemons[0];
        int maxMovimientos = pokemons[0].contarMovimientosDisponibles();

        for (int i = 1; i < pokemons.length; i++) {
            if (pokemons[i].contarMovimientosDisponibles() > maxMovimientos) {
                maxMovimientos = pokemons[i].contarMovimientosDisponibles();
                mejor = pokemons[i];
            }
        }

        System.out.println("\n=== POKEMON CON MAS MOVIMIENTOS DISPONIBLES ===");
        System.out.println("Nombre: " + mejor.getNombre());
        System.out.println("Cantidad: " + maxMovimientos);
    }
}