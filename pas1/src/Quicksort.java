
public class Quicksort{
	private static int qsCount=0;
	private static int partCount=0;
	public static void quicksort(int[] A){
		int endIndex = A.length-1;
		System.out.println("Initial Quicksort() from 0 to "+endIndex);
		qs(A,0,endIndex);
		
	}
	
	public static void qs(int A[], int left, int right){
		qsCount++;
		System.out.println("qsCount iteration #"+qsCount);
		if(left==right){
			return;
		}
		int pivotVal = A[(left+right)/2];
		System.out.println("Pivot = "+pivotVal);
		

		System.out.println("---Calling partition with left="+left+", right="+right+", pivot="+pivotVal);
		int index = partition(A,left, right, pivotVal);
		System.out.println("---Returned from partition with INDEX="+index);
		qs(A, left, index-1);
		qs(A, index, right);
		System.out.println("---Sort Complete!");
	}

	public static int partition(int[] A, int start, int end, int piv){
		partCount++;
		System.out.println("Partition Iteration #"+partCount);
		int left = start;
		int right = end;
		
		if(left == right){
			System.out.println("Left is equal to right, returning");
			return left;
		}
		
		while(left<right){
			
			while(A[left]<piv){
				left++;
			}
			
			while(A[right]>piv){	
				right--;
			} 
			if(left <= right){
				swap(A,left,right);
				left++;
				right--;
			}
		}
		return left;
	}
	
	public static void swap(int[] A, int i, int j) {
		if(i==j){
			System.out.println("ERROR: Swapping with self");
			return;
		}
		int tmp = A[i];
		A[i] = A[j];
		A[j] = tmp;
		System.out.println("***Swap(): "+A[i]+" & "+A[j]);
		printArray(A);
	}
	
	public static void printArray(int[] A){
		int length = A.length;
		if(length==0){
			System.out.println("[EMPTY ARRAY]");
			return;
		}
		System.out.print(" ===> ["+A[0]);
		for(int i=1; i<length; i++){
			System.out.print(", "+A[i]);
		}
		System.out.print("]\n");
	}

	public static void main(String[] args) {
		// Input file
		int[] data = {2,8,10,9,7,3,1,6,5};
		System.out.print("--Starting main--");
		printArray(data);

		quicksort(data);
		
		System.out.print("--Ending main--  ");
		printArray(data);
	}
}
