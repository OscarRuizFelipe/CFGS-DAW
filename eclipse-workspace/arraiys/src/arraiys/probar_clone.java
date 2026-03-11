package arraiys;

import java.util.*;

public class probar_clone {
	 public static void main(String[] args) {
		 
	
	
  int[] array = {1,2,3,4,5};
  int[] clonArray;
  
  clonArray =array.clone();
  
  array[4]=55;
  
  System.out.println(Arrays.toString(clonArray));
  System.out.println(Arrays.toString(array));
}
	 }
