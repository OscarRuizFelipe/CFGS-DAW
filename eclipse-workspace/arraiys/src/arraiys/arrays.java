package arraiys;

public class arrays {
	  public static void main(String[] args) {

	int[] NumeroEntero = {1,2,3,4,5};
	
	for(int a=0; a<NumeroEntero.length;a++) {
		
    System.out.println("elemento "+a+": "+NumeroEntero[a]);		
	
	}
	
	for (int Numeros : NumeroEntero) {
		System.out.println(Numeros);
	}
	
}
}