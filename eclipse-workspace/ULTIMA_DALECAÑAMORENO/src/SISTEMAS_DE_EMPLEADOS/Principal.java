package SISTEMAS_DE_EMPLEADOS;

public class Principal {

    public static void main(String[] args) {

        Tarea[] tareas1 = {
            new Tarea("Backend", 10, 8),
            new Tarea("Frontend", 8, 6),
            new Tarea("Debug", 6, 9)
        };

        Tarea[] tareas2 = {
            new Tarea("API", 12, 7),
            new Tarea("Testing", 5, 6),
            new Tarea("Deploy", 4, 5)
        };

        Tarea[] tareas3 = {
            new Tarea("Facturas", 6, 4),
            new Tarea("Emails", 5, 3),
            new Tarea("Archivo", 4, 2)
        };

        Tarea[] tareas4 = {
            new Tarea("Reportes", 7, 5),
            new Tarea("Llamadas", 6, 4),
            new Tarea("Gestión", 8, 6)
        };

        Empleado[] empleados = new Empleado[4];

        empleados[0] = new Progamador(1, "Carlos", Departamento.IT, tareas1, 10);
        empleados[1] = new Progamador(2, "Ana", Departamento.IT, tareas2, 8);
        empleados[2] = new Administrativo(3, "Luis", Departamento.RRHH, tareas3, 20);
        empleados[3] = new Administrativo(4, "Marta", Departamento.VENTAS, tareas4, 15);

        // MOSTRAR TODO
        for (Empleado e : empleados) {
            System.out.println("\n------------------");
            System.out.println(e);

            System.out.println("Tareas:");
            e.mostrarTareas();

            System.out.println("Horas totales: " + e.totalHoras());
            System.out.println("Dificultad media: " + e.dificultadMedia());
            System.out.println("Productividad: " + e.calcularProductividad());
            System.out.println("Necesita formación: " + e.necesitaFormacion());
        }

        // MEJOR EMPLEADO
        Empleado mejor = empleados[0];

        for (int i = 1; i < empleados.length; i++) {
            if (empleados[i].calcularProductividad() > mejor.calcularProductividad()) {
                mejor = empleados[i];
            }
        }

        System.out.println("\nMEJOR EMPLEADO: " + mejor.getNombre());

        // CONTAR TIPOS
        int prog = 0;
        int admin = 0;

        for (Empleado e : empleados) {
            if (e instanceof Progamador) prog++;
            else if (e instanceof Administrativo) admin++;
        }

        System.out.println("Programadores: " + prog);
        System.out.println("Administrativos: " + admin);

        // TAREA MAS DIFICIL
        Tarea peor = null;
        int max = -1;

        for (Empleado e : empleados) {
            for (Tarea t : e.getTareas()) {
                if (t.getDificultad() > max) {
                    max = t.getDificultad();
                    peor = t;
                }
            }
        }

        System.out.println("\nTAREA MAS DIFICIL: " + peor.getNombre());
        System.out.println("Dificultad: " + max);

        // EMPLEADO CON MAS TAREAS
        Empleado mas = empleados[0];

        for (int i = 1; i < empleados.length; i++) {
            if (empleados[i].getTareas().length > mas.getTareas().length) {
                mas = empleados[i];
            }
        }

        System.out.println("\nMAS TAREAS: " + mas.getNombre());
    }
}