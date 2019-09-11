import java.util.*;
import java.io.*;

public class MergeDriver {

	public static void main(String[] args) {
		
//Initialize test variables
		int[] d = MergeSort.generate(10);
		MergeSort.show(d);
//Starting Conditions
		p("===TEST BEGIN===");
		MergeSort.show(d);


		
		
//SMS Method Tests	
		MergeSort.structuredMergeSort(d);
//End Test
		p("\n===TEST COMPLETE===");		
		
		
	
	

		
		
		
		
		
		
		
	
	}

	public static void p(String s) {
		System.out.println(s);
	}

}
