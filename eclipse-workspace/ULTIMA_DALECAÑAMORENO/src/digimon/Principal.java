package digimon;

public class Principal {

    public static void main(String[] args) {

        Digimon[] digimons = new Digimon[4];

        int[][] c1 = {{2,1},{1,2},{2,2},{1,1},{2,1}};
        int[][] c2 = {{1,1},{2,2},{1,2},{2,1},{1,1}};
        int[][] c3 = {{2,2},{2,1},{1,1},{1,2},{2,2}};
        int[][] c4 = {{1,2},{1,1},{2,1},{2,2},{1,2}};

        digimons[0] = new DigimonAtaque("D1", "Agumon", c1, 10);
        digimons[1] = new DigimonAtaque("D2", "Greymon", c2, 8);
        digimons[2] = new DigimonSoporte("D3", "Patamon", c3, 15);
        digimons[3] = new DigimonSoporte("D4", "Gatomon", c4, 12);

        double maxPoder = -1;
        Digimon mejor = null;

        int contAtaque = 0;
        int contSoporte = 0;

        int sumaManana = 0;
        int sumaTarde = 0;
        int total = 0;

        int maxDia = -1;
        Digimon mejorDia = null;

        for (Digimon d : digimons) {

            System.out.println(d);
            d.mostrarCombates();

            double poder = d.calcularPoder();

            System.out.println("Total: " + d.calcularCombatesTotales());
            System.out.println("Poder: " + poder);
            System.out.println("¿Elite? " + d.esElite());
            System.out.println("----------------");

            // Mayor poder
            if (poder > maxPoder) {
                maxPoder = poder;
                mejor = d;
            }

            // Contadores (instanceof)
            if (d instanceof DigimonAtaque) contAtaque++;
            if (d instanceof DigimonSoporte) contSoporte++;

            // Media turnos
            for (int i = 0; i < 5; i++) {
                sumaManana += d.combates[i][0];
                sumaTarde += d.combates[i][1];
                total++;
            }

            // Mayor día
            for (int i = 0; i < 5; i++) {
                int comb = d.calcularCombatesDia(i);
                if (comb > maxDia) {
                    maxDia = comb;
                    mejorDia = d;
                }
            }
        }

        System.out.println("Mejor Digimon: " + mejor);
        System.out.println("Ataque: " + contAtaque);
        System.out.println("Soporte: " + contSoporte);

        System.out.println("Media mañana: " + (double)sumaManana / total);
        System.out.println("Media tarde: " + (double)sumaTarde / total);

        System.out.println("Mejor día: " + mejorDia + " con " + maxDia);
    }
}