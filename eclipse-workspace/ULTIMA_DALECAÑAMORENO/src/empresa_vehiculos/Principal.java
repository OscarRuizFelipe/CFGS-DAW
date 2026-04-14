package empresa_vehiculos;

/*
 * CLASE PRINCIPAL
 * ---------------
 * Aquí se ejecuta el programa
 */
public class Principal {

    public static void main(String[] args) {

        // Array de tipo Vehiculo (polimorfismo)
        Vehiculo[] vehiculos = new Vehiculo[4];

        // Inicialización de matrices
        double[][] km1 = {{50},{60},{40},{70},{30},{20},{10}};
        double[][] km2 = {{30},{40},{20},{50},{60},{30},{20}};
        double[][] km3 = {{20},{30},{25},{35},{40},{20},{15}};
        double[][] km4 = {{60},{70},{50},{80},{40},{30},{20}};

        // Creación de objetos
        vehiculos[0] = new Coche("1111AAA", "Toyota", km1, 10);
        vehiculos[1] = new Coche("2222BBB", "Ford", km2, 8);
        vehiculos[2] = new Moto("3333CCC", "Yamaha", km3, 20);
        vehiculos[3] = new Moto("4444DDD", "Honda", km4, 15);

        double maxUso = -1;
        Vehiculo mejor = null;

        int contCoches = 0;
        int contMotos = 0;

        double sumaKm = 0;
        int totalDias = 0;

        double maxDia = -1;
        Vehiculo vehiculoMaxDia = null;

        // Recorrido del array
        for (Vehiculo v : vehiculos) {

            System.out.println(v);
            v.mostrarKilometros();

            double uso = v.calcularUso();

            System.out.println("Km totales: " + v.calcularKmTotales());
            System.out.println("Uso: " + uso);
            System.out.println("¿Necesita revisión? " + v.necesitaRevision());
            System.out.println("----------------------");

            // Mayor uso
            if (uso > maxUso) {
                maxUso = uso;
                mejor = v;
            }

            // Contar tipos
            if (v instanceof Coche) contCoches++;
            if (v instanceof Moto) contMotos++;

            // Media km
            for (int i = 0; i < 7; i++) {
                sumaKm += v.calcularKmDia(i);
                totalDias++;
            }

            // Mayor día
            for (int i = 0; i < 7; i++) {
                double kmDia = v.calcularKmDia(i);
                if (kmDia > maxDia) {
                    maxDia = kmDia;
                    vehiculoMaxDia = v;
                }
            }
        }

        // Resultados finales
        System.out.println("Vehículo con mayor uso: " + mejor);
        System.out.println("Coches: " + contCoches);
        System.out.println("Motos: " + contMotos);
        System.out.println("Media km por día: " + (sumaKm / totalDias));
        System.out.println("Vehículo con más km en un solo día: " 
                           + vehiculoMaxDia + " con " + maxDia + " km");
    }
}