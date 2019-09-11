public class Quicksort {
	/* Fastest known in-memory algorithm (avg case)
	Use recursion to call QS on left & right subarrays
	O(n^2) worst case (based on pivot selection) 
	*/
	int[] data;
	int size;
	int pivot;
	//CONSTRUCTOR
	public void Quicksort(int[] arr){
		System.out.println("Quicksort Initialized");
		data = arr;
		size = data.length;
	}

	//SORT DATA USING RECURSION
	public void sort(int left, int right){
		int piv = selectPivot();
		partition(0, size-1, piv));
	}
	//SELECT PIVOT
	public void selectPivot(int left, int right){
		pivot = (right + left)/2;
	}
	
	public int partition(int left, int right, int pivot){
		int vp = data[pivot];
		int li = left;
		int ri = right;
		
		while(li < ri){//ends when left crosses right pivot
			while(data[li] <= vp){
				li++;
			}
			while(ri != 0 && data[ri] >= vp){
				ri--;
			}	
			swap(li,ri);
		}
		return li;
		

	}
	
	public void swap(int l, int r){
		System.out.println("Swapping: "+data[l]+" & "+data[r]);
		int tmp = data[l];
		data[l] = data[r];
		data[r] = tmp;
	}
	
	public static void main(String[] args) {
		int[] d = {7,2,5,8,1};
		Quicksort qs = new Quicksort(d);

	}
}