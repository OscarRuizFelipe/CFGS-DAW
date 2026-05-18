package EXAMEN_ULTIMO;

public class Principal {

	
    public static void main(String[] args) {
    	
    	Robot[] robots =new robot[4];
    	
    	Modulo m1 = new Modulo("laser muerte",TipoModulo.ARMA, 80);
    	Modulo m2 = new Modulo("puño loco",TipoModulo.ARMA, 800);
    	Modulo m3 =new Modulo("escudo titanio",TipoModulo.DEFENSA, 80);
    	Modulo m4 =new Modulo("botas rapidas",TipoModulo.MOVILIDAD, 80);
    	
    	Modulo[] misModulos = {m1, m2, m3, m4}
    	
    	int[][] act1 = {{50,85}, {30,40} , {50,70} , {80,100}};
    	int[][] act2 = {{20,85}, {20,40} , {40,70} , {50,100}};
    	
    	robots[0] = new RobotAtaque(1, "fosela", TipoModulo.ARMA, 90,misModulos,act1);
    	robots[1] = new RobotAtaque(2, "HugIA", TipoModulo.ARMA, 1000,misModulos,act1);
    	robots[2] = new RobotDefensa(3, "FiliBOT",TipoModulo.DEFENSA 100, misModulos, act2, 30);
    	robots[3]	new RobotDefensa(4, "PEPE",TipoModulo.DEFENSA 1000, misModulos, act2, 60);
    	
    	System.out.println("Lista de guerreros");
    	for (Robot r : Robots) {
    		System.out.println(r.toString());
    		System.out.println("Modulos activos");
    		r.mostrarModulosActivos();
    		System.out.println("potencia media activa" + r.calcularPotenciaMediaActiva());
    		System.out.println("indice de combate" + r.calcularIndiceCombate());
    		System.out.println("¿necesita revision?" + (r.necesitaRevision());
    		
    		
    	}
    	
    }
}