import java.util.*;
public class ISort{
	public static void main(String[] args) {
//Argument parameters for manual input
		int [] data = new int[args.length];
		for(int i=0; i<args.length; i++) {
			data[i] = Integer.parseInt(args[i]);
		}
		System.out.println("Structuring run: Initial data");
		showMe(data);
		System.out.println(data.length+" elements in input array");
		//structure(data);
		//System.out.println("--Structuring Complete, Modified array shown below:");
		//showMe(data);
		System.out.println("Begining ISort.java");
		iSort(data);
		
		//System.out.println("Ascending Sorted Array:");
		//showMe(data);
		
		testAlgo(20000);
	};
	
//Structuring Run - Ascending/descending Run Correction
	public static void structure(int[] d){
		int fstreak = 0;
		int rstreak = 0;
		
		for(int i =1; i<d.length+1; i++){
			//System.out.println("i="+i+"     Rstreak="+rstreak+"     Fstreak="+fstreak);
			if(rstreak>1){//reverse elements from  to i
				rstreak = 0;
				fstreak=0;
				
			} else if(fstreak>1 & d[i]<d[i-1] ){
				System.out.println("Ending Ascending Run of "+(fstreak)
						+" ");
				showMe(showSeg(d, i-fstreak-1, i-1));
				fstreak = 0;
				rstreak = 1;
			
			} else if(d[i] > d[i-1]) {//Increasing Run - Skip ahead
				fstreak++;
				rstreak=0;
			} 
			else {
				fstreak=0;
				while(d[i]<d[i-1] & i<d.length-1){ 
				//System.out.println("Array L="+d.length);
				//System.out.println("Descending, i="+i+"    d[i]="+d[i]+"   d[i-1]="+d[i-1]);
						i++;
						rstreak++;
					}//When while loop exits, direction just changed
				System.out.println("Direction change -Decreasing run length="+rstreak);
				int[] rev = reverseArray(d, (i-rstreak), i);
				showMe(rev);
				//System.out.println("Start index, range")
				}
			}//Structuring runs complete
			
		}
//TESTING PARAMETERS FOR RANDOM ARRAY GENERATOR	
	public static void testAlgo(int sample){
		int[] timeArray = new int[sample];
		int[] data = new int[sample];
		for(int i=0;i<sample;i+=1000){ 
			System.out.println(i + "       " + iSort(generate(i, 1000)));
			}
		}
		
	
//Insertion Sort Algorithm	
	public static int iSort(int[] data) {  //SORTING ALGORITHM	- TIMER START
		int comparisons = 0;
		long start = System.currentTimeMillis();
		for(int k=1; k < data.length; k++) {
			//System.out.println("====-Main loop iteration-==- k=" + k+" targeting value "+data[k]);
			if(data[k] > data[k-1]) { 	//KEEP CALM AND CARRY ON
				//System.out.println("Value "+data[k]+">"+data[k-1]+" - Already Sorted - value at k="+k);
				comparisons++;
				continue;										//Life is good.  
			} 
			//System.out.println("Unordered, proceed to SHIFT & INSERT  =>  "+data[k]+" < "+data[k-1]);
			int j = 1;
			int cursor = data[k];	
			while((k-j)>(-1) & (cursor < data[k-j])) { 		//SHIFT & INSERT - Current value is not in position
				comparisons++;
				//System.out.println("Copying over {"+data[k-j]+"} --> {"+data[k-j+1]+"} and Temp="+ cursor);
				data[k-j+1] = data[k-j];						//SHIFT Value at [k-j]
				if(k-j == 0) {
					j++;
					comparisons++;
					break;
				}
				j++;
			}
			data[k-j+1] = cursor;					//INSERT temp value
		}
		System.out.println("---iSort Complete---");
		System.out.println("Algorithm took: "+ runTime(start) + "ms with "+data.length+" elements");
		System.out.println("Comparisons made: "+ comparisons);
		//long end = System.currentTimeMillis();
		//long ms = (end-start);
		int t = (int) runTime(start);
		return t;
	}

	public static void showMe(int[] a) {
		System.out.println(Arrays.toString(a));
		return;
	}
	public static int[] showSeg(int[] d, int a, int b){ //Display seg & return modified array
		int diff = (b-a+1); 
		int [] displ = new int[diff];
		for(int i = 0; i<diff;i++){
			displ[i] = d[i+a];	
		}
		System.out.println("Run Segment: "+Arrays.toString(displ)+
				"  with start index "+a+" to "+b);
		return displ;
	}
	
	public static int[] reverseArray(int[] d, int a, int b) {
		int tmp;
		int flips = (b - a + 1)/2;
		for(int i=0; i<flips;i++){ //Reversal of values
			tmp = d[a+i]; //Save value at i
			d[a+i] = d[b-i]; //Copy end to i
			d[b-i] = tmp; 
		}
		return d;
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
		//System.out.println("Generating "+N+" Random numbers");
		Random ran = new Random();
		int[] array = new int[N];
		for(int i=0; i<N; i++) {
			array[i] = ran.nextInt(range);}
		return array;
	}
	public static long runTime(long start){
		long end = System.currentTimeMillis();
		return (end - start);
	}
}





