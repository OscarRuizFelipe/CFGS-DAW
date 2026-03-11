package arraiys;

public class arraysSumaYmedia {
	  public static void main(String[] args) {
		 
		  double SumaTotal=0;
		  double[] SumaMedia = {2.2,4.6,7.6,5.5,6.5,88.5};
		  
		  for (double Numeros : SumaMedia) {
				SumaTotal += Numeros; 
			 
			}
		  System.out.println(SumaTotal);
		  double mediaTotal= SumaTotal / SumaMedia.length;
		  System.out.println(mediaTotal);
	  }
}
