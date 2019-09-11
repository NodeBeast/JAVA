package hello352;
import java.util.*;
public class Hello352 {
	public static void main(String[] args) {
		System.out.println("Insertion Sort Algorithm Begin");
		int [] data = new int[args.length];	
		System.out.println("============Loading Values================");
		
		for(int i=0; i<args.length; i++){ //Load Args to array
			data[i] = Integer.parseInt(args[i]);
		}
		
		System.out.println("Initial Argument Input Data Array:");
		showMe(data);
		System.out.println("------------Input Complete, Begin Sort-------------------");
		
//MAIN SORTING LOOP	- TIMER START
		long startingTime = System.currentTimeMillis();
		for(int k=1; k < data.length; k++) {
			System.out.println("===========-Main loop iteration-===========- k=" + k+" targeting value "+data[k]);
			showMe(data);
			if(data[k] > data[k-1]) { 	//KEEP CALM AND CARRY ON
				System.out.println("Value "+data[k]+">"+data[k-1]+" - Already Sorted - value at k="+k);
				continue;										//Life is good.  
			} 

			System.out.println("Unordered, proceed to SHIFT & INSERT  =>  "+data[k]+" < "+data[k-1]);
			int j = 1;
			int current = data[k];	
			while((k-j)>(-1) & (current < data[k-j])) { 		//SHIFT & INSERT - Current value is not in position
				System.out.println("Copying over {"+data[k-j]+"} --> {"+data[k-j+1]+"} and Temp="+ current);
				data[k-j+1] = data[k-j];						//SHIFT Value at [k-j]
				if(k-j == 0) {
					System.out.println("Breaking while loop");
					j++;
					break;
				}
				j++;
				System.out.println(" Inc j ");
			}
			System.out.println("Correct position at data[" +(k-j+1)+"]="+data[k-j+1]+" was found for variable:  temp="+current);
			data[k-j+1] = current;								//INSERT
			showMe(data);
			
		}
		
		long endingTime = System.currentTimeMillis();	//Calculate and display main loop duration
		long runtime = (endingTime - startingTime);
		System.out.println("---Sorting Complete---");
		System.out.println("Algorithm took: "+ runtime + " ms");
		showMe(generate(100,50));
	}//End of MAIN()

//Helper Methods	
	public static void showMe(int[] a) {
		System.out.println(Arrays.toString(a));
		return;
	}
	public static void loopStatus(int c, int g) {
		System.out.println("K = "+c+"   J= "+ g+ " --- [k-j]="+(c-g)+" && [k-j+1]="+(c-g+1));
		return;
	}
	public static void diag(int k, int[] data){
		System.out.println("=== k-1=["+(k-1)+"] holding {"+data[k-1]+"}" );
		System.out.println("=== k  =["+( k )+"] holding {"+data[ k ]+"}" );
		System.out.println("=== k+1=["+(k-1)+"] holding {"+data[k+1]+"}" );
	}

	public static int[] generate(int N, int range){ //Random number generator
		System.out.println("Generating "+N+" Random numbers");
		Random ran = new Random();
		int[] array = new int[N];
		for(int i=0; i<N; i++) {
			array[i] = ran.nextInt(range);
			System.out.println("Array["+i+"] = "+array[i]);
		}
		return array;
	}
}











