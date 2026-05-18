package SISTEMAS_VIDEOJUEGOS;

public class Principal {

    public static void main(String[] args) {

        Juego[] juegos1 = {
            new Juego("FIFA", Genero.DEPORTES, 100, 8.5),
            new Juego("COD", Genero.ACCION, 200, 9.0),
            new Juego("GTA", Genero.ACCION, 300, 9.5)
        };

        Juego[] juegos2 = {
            new Juego("Zelda", Genero.RPG, 150, 9.8),
            new Juego("Mario", Genero.ACCION, 120, 8.9),
            new Juego("Smash", Genero.ACCION, 180, 9.1)
        };

        Juego[] juegos3 = {
            new Juego("LoL", Genero.ACCION, 500, 8.7),
            new Juego("Valorant", Genero.ACCION, 400, 8.8),
            new Juego("WoW", Genero.RPG, 350, 9.2)
        };

        Juego[] juegos4 = {
            new Juego("CSGO", Genero.ACCION, 450, 8.6),
            new Juego("Dota", Genero.RPG, 300, 8.9),
            new Juego("Fortnite", Genero.ACCION, 600, 8.5)
        };

        Plataforma[] plataformas = new Plataforma[4];

        plataformas[0] = new Consola("PlayStation", juegos1, 2000);
        plataformas[1] = new Consola("Nintendo", juegos2, 1500);
        plataformas[2] = new PC("PC Gamer 1", juegos3, 800);
        plataformas[3] = new PC("PC Gamer 2", juegos4, 600);

        // MOSTRAR TODO
        for (Plataforma p : plataformas) {
            System.out.println("\n------------------");
            System.out.println(p);

            System.out.println("Juegos:");
            p.mostrarJuegos();

            System.out.println("Horas totales: " + p.totalHorasJugadas());
            System.out.println("Media puntuacion: " + p.puntuacionMedia());
            System.out.println("Exito: " + p.calcularExito());
            System.out.println("Popular: " + p.esPopular());
        }

        // PLATAFORMA MAS EXITOSA
        Plataforma mejor = plataformas[0];

        for (int i = 1; i < plataformas.length; i++) {
            if (plataformas[i].calcularExito() > mejor.calcularExito()) {
                mejor = plataformas[i];
            }
        }

        System.out.println("\nPLATAFORMA MAS EXITOSA: " + mejor.getNombre());

        // CONTAR TIPOS
        int consolas = 0;
        int pcs = 0;

        for (Plataforma p : plataformas) {
            if (p instanceof Consola) consolas++;
            else if (p instanceof PC) pcs++;
        }

        System.out.println("Consolas: " + consolas);
        System.out.println("PCs: " + pcs);

        // JUEGO MAS JUGADO
        Juego masJugado = null;
        int max = -1;

        for (Plataforma p : plataformas) {
            for (Juego j : p.getJuegos()) {
                if (j.getHorasJugadas() > max) {
                    max = j.getHorasJugadas();
                    masJugado = j;
                }
            }
        }

        System.out.println("\nJUEGO MAS JUGADO: " + masJugado.getNombre());
        System.out.println("Horas: " + max);

        // PLATAFORMA CON MAS HORAS
        Plataforma masHoras = plataformas[0];

        for (int i = 1; i < plataformas.length; i++) {
            if (plataformas[i].totalHorasJugadas() > masHoras.totalHorasJugadas()) {
                masHoras = plataformas[i];
            }
        }

        System.out.println("\nPLATAFORMA CON MAS HORAS: " + masHoras.getNombre());
    }
}