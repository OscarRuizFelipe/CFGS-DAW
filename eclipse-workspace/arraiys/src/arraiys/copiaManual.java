package arraiys;

import java.util.Arrays;

public class copiaManual {
	 public static void main(String[] args) {
		 
		 int[] array = {1,2,3,4,5};
		 int[] clon;
		 clon=new int[array.length];
			
		 for(int a=0; a<array.length;a++) {
			 
			 
			 clon[a]=array[a];
			 
			 
			 
		 }
		 System.out.println(Arrays.toString(clon));

	 }

}
