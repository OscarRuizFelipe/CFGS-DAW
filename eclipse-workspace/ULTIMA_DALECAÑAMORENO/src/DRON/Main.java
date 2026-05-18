package DRON;

public class Main {

    public static void main(String[] args) {

        ModoVuelo[] m1 = {
            new ModoVuelo("Eco", TipoDron.REPARTO, 50),
            new ModoVuelo("Turbo", TipoDron.REPARTO, 90),
            new ModoVuelo("Estable", TipoDron.REPARTO, 60),
            new ModoVuelo("Nocturno", TipoDron.REPARTO, 80)
        };

        int[][] r1 = {
            {1, 50},
            {10, 90},
            {5, 60},
            {20, 80}
        };

        ModoVuelo[] m2 = m1;
        int[][] r2 = r1;

        ModoVuelo[] m3 = m1;
        int[][] r3 = r1;

        ModoVuelo[] m4 = m1;
        int[][] r4 = r1;

        Dron[] drones = new Dron[4];

        drones[0] = new DronRapido(1, "Falcon", TipoDron.REPARTO, 25, m1, r1, 3);
        drones[1] = new DronRapido(2, "Swift", TipoDron.VIGILANCIA, 18, m2, r2, 2);
        drones[2] = new DronResistente(3, "Tank", TipoDron.INDUSTRIAL, 22, m3, r3, 40);
        drones[3] = new DronResistente(4, "Heavy", TipoDron.REPARTO, 14, m4, r4, 25);

        System.out.println("=== INFORMACION COMPLETA ===");

        for (Dron d : drones) {
            System.out.println("\n------------------");
            System.out.println(d);

            System.out.println("Modos disponibles:");
            d.mostrarModosDisponibles();

            System.out.println("Cantidad: " + d.contarModosDisponibles());
            System.out.println("Media eficiencia: " + d.eficienciaMedia());
            System.out.println("Indice: " + d.calcularIndiceRendimiento());
            System.out.println("Revision: " + d.necesitaRevision());
        }

        // mejor dron
        Dron mejor = drones[0];

        for (int i = 1; i < drones.length; i++) {
            if (drones[i].calcularIndiceRendimiento() > mejor.calcularIndiceRendimiento()) {
                mejor = drones[i];
            }
        }

        System.out.println("\nMEJOR DRON: " + mejor.getModelo());

        // conteo
        int rapidos = 0;
        int resistentes = 0;

        for (Dron d : drones) {
            if (d instanceof DronRapido) rapidos++;
            else if (d instanceof DronResistente) resistentes++;
        }

        System.out.println("Rapidos: " + rapidos);
        System.out.println("Resistentes: " + resistentes);

        // mejor modo
        ModoVuelo mejorModo = null;
        String dronModo = "";
        int max = -1;

        for (Dron d : drones) {
            for (int i = 0; i < d.getModosVuelo().length; i++) {
                if (d.getRendimiento()[i][0] <= d.getNivelSistema()) {
                    if (d.getRendimiento()[i][1] > max) {
                        max = d.getRendimiento()[i][1];
                        mejorModo = d.getModosVuelo()[i];
                        dronModo = d.getModelo();
                    }
                }
            }
        }

        System.out.println("\nMEJOR MODO: " + mejorModo.getNombre());
        System.out.println("Dron: " + dronModo);
        System.out.println("Eficiencia: " + max);

        // más modos
        Dron mas = drones[0];

        for (int i = 1; i < drones.length; i++) {
            if (drones[i].contarModosDisponibles() > mas.contarModosDisponibles()) {
                mas = drones[i];
            }
        }

        System.out.println("\nMAS MODOS: " + mas.getModelo());
    }
}