public class Radix{
/* Use 'PILES' to sort subsets
 * Use %10 to sort integers into 10 bins 
 * Every key is assigned bin matching its right digit
 * Improves on binsort (#bins >= key range)
 * Still limited range of element types
 * O(n) to sort into bins
 * O(nk + rk) total work: O(n) in all cases if k=const
 * 		depends on size of base, r and key range k
 * lower bound Ohm(nlogn) for n distinct values
*/	
	//PROPERTIES
	final int empty = -1;
	int radix; 					//base of radix 
	int[] arr;				//Input array
	int[] count; 			//Stores # records in bin [index]
	int[][] bins; 			//original array
	
	//CONSTRUCTOR r=#bins, 
	public Radix(int[] data, int radix){
		bins = new int[radix][radix];
		count = new int[radix];
		arr = new int[data.length];
		this.radix = radix;
		//Copy array to local
		for(int m=0; m < data.length; m++){
			arr[m] = data[m];
		}
		//Initialize buckets & countArr
		for(int i=0; i< radix;i++){
			count[i]=0;
			for(int j=0; j<radix; j++){
				bins[i][j] = empty;
			}
		}
	}
	
	public void fillBuckets(){
		//put all values ending in i in bucket i ...
		int tmp;
		int placeValue; // 1's, 10's, 100's, ...
		for(int k=0; k<placeValue; k++){
			tmp = arr[k]%placeValue;
			
		}
		
	}
	
	//=====================MAIN METHOD
	public static void main(String[] args) {
		int[] data = {28,78,1,82,83,7,80,19,12,58};
		Radix rad = new Radix();
		
		
	}
}