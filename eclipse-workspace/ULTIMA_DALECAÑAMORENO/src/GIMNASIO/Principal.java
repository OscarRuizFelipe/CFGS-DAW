package GIMNASIO;

public class Principal {

    public static void main(String[] args) {

        Ejercicio[] e1 = {
            new Ejercicio("Press Banca", TipoEjercicio.FUERZA, 100, 8),
            new Ejercicio("Sentadillas", TipoEjercicio.FUERZA, 120, 9),
            new Ejercicio("Correr", TipoEjercicio.CARDIO, 150, 6)
        };

        Ejercicio[] e2 = {
            new Ejercicio("Flexiones", TipoEjercicio.FUERZA, 80, 7),
            new Ejercicio("Burpees", TipoEjercicio.CARDIO, 140, 8),
            new Ejercicio("Plancha", TipoEjercicio.FLEXIBILIDAD, 60, 5)
        };

        Ejercicio[] e3 = {
            new Ejercicio("Yoga", TipoEjercicio.FLEXIBILIDAD, 50, 4),
            new Ejercicio("Estiramientos", TipoEjercicio.FLEXIBILIDAD, 40, 3),
            new Ejercicio("Caminar", TipoEjercicio.CARDIO, 70, 2)
        };

        Ejercicio[] e4 = {
            new Ejercicio("Bici", TipoEjercicio.CARDIO, 130, 6),
            new Ejercicio("Eliptica", TipoEjercicio.CARDIO, 120, 5),
            new Ejercicio("Abdominales", TipoEjercicio.FUERZA, 90, 7)
        };

        Rutina[] rutinas = new Rutina[4];

        rutinas[0] = new RutinaIntensiva("FullBody", e1, 2);
        rutinas[1] = new RutinaIntensiva("HIIT", e2, 3);
        rutinas[2] = new RutinaLigera("Relax", e3, 5);
        rutinas[3] = new RutinaLigera("Cardio Suave", e4, 3);

        // MOSTRAR TODO
        for (Rutina r : rutinas) {
            System.out.println("\n------------------");
            System.out.println(r);

            System.out.println("Ejercicios:");
            r.mostrarEjercicios();

            System.out.println("Calorias: " + r.totalCalorias());
            System.out.println("Dificultad media: " + r.dificultadMedia());
            System.out.println("Rendimiento: " + r.calcularRendimiento());
            System.out.println("Exigente: " + r.esExigente());
        }

        // MEJOR RUTINA
        Rutina mejor = rutinas[0];

        for (int i = 1; i < rutinas.length; i++) {
            if (rutinas[i].calcularRendimiento() > mejor.calcularRendimiento()) {
                mejor = rutinas[i];
            }
        }

        System.out.println("\nMEJOR RUTINA: " + mejor.getNombre());

        // CONTAR TIPOS
        int intensivas = 0;
        int ligeras = 0;

        for (Rutina r : rutinas) {
            if (r instanceof RutinaIntensiva) intensivas++;
            else if (r instanceof RutinaLigera) ligeras++;
        }

        System.out.println("Intensivas: " + intensivas);
        System.out.println("Ligeras: " + ligeras);

        // EJERCICIO MAS DIFICIL
        Ejercicio peor = null;
        int max = -1;

        for (Rutina r : rutinas) {
            for (Ejercicio e : r.getEjercicios()) {
                if (e.getDificultad() > max) {
                    max = e.getDificultad();
                    peor = e;
                }
            }
        }

        System.out.println("\nEJERCICIO MAS DIFICIL: " + peor.getNombre());
        System.out.println("Dificultad: " + max);

        // RUTINA CON MAS CALORIAS
        Rutina mas = rutinas[0];

        for (int i = 1; i < rutinas.length; i++) {
            if (rutinas[i].totalCalorias() > mas.totalCalorias()) {
                mas = rutinas[i];
            }
        }

        System.out.println("\nRUTINA CON MAS CALORIAS: " + mas.getNombre());
    }
}