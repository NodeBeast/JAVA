import java.util.Arrays;
public class MaxHeap{
	//Complete tree, parents are greater, 
	int size; //Max size of array
	int[] heap;//Pointer to heap array
	int n; //Elements in heap
	int sifts;
	int swaps;
	//CONSTRUCTORS
	public MaxHeap(int[] h, int n, int heapSize){
		size = heapSize;
		this.n = n;
		heap = h;
		buildHeap(); //O(NlogN) to build the heap
		stats();
	}
	//PRIMARY METHODS
	public void buildHeap(){
		System.out.println("Building heap");
		for(int i = n/2; i>0; i--) 
			siftDown(i);
	}
	public void insert(int insert){// nlogn time to insert n values
	int curr = n++;
	System.out.println("Inserting "+insert+" at index "+ curr);
	adjustSize();
	stats();
	heap[curr] = insert;
	while((curr != 0) && (heap[curr] > heap[parentIndex(curr)])){
		swap(curr,parentIndex(curr));
		curr = parentIndex(curr);
		}
	}
	public void siftDown(int pos){
		sifts++;
		assert (pos >= 0) &&(pos < n) : "Illegal heap pos";
		while(!isLeaf(pos)){
			int j = leftChildIndex(pos);
			if((j<(n-1)) && (heap[j] < heap[j+1])) 
				j++; //j is index of child with greater value
			if(heap[pos] >= heap[j])
				return;
			swap(pos, j);
			pos = j; //move down
		}
	}
	public int removeMax(){
		assert (n>0) : "Removing from empty heap";
		swap(0, --n); //swap max with last value
		if(n != 0) 
			siftDown(0); //Put heap root in correct position
		System.out.println("Removing: "+heap[n]);
		return heap[n];
	}
	public int remove(int pos){
		assert (pos >= 0) && (pos < n) : "Illegal heap position";
		if(pos ==(n-1)) n--; //last element, Stop!
		else{
			swap(pos, --n);
			while((pos >0) && heap[pos] > heap[parentIndex(pos)]){
				swap(pos, parentIndex(pos));
				pos = parentIndex(pos);
			}
			if(n != 0) siftDown(pos);
		}
		return heap[n];
	}
	//INDEX GETTERS
	public int leftChildIndex(int parentIndex){
		int left = parentIndex*2 + 1;
		if(left <= size-1){
			return left;
		} else{
			System.out.println("Error, "+parentIndex+" cannot have child at "+left);
			return -1;
		}
	}
	public int rightChildIndex(int parentIndex){
		int right = parentIndex*2 + 2;
		if(right <= size-1){
			return right;
		} else{
			System.out.println("Error, "+parentIndex+" cannot have child at "+right);
			return -1;
		}
	}
	public int parentIndex(int childIndex){
		int parent = (childIndex-1)/2; 
		if(parent >= 0){
			return parent;
		} else{
			System.out.println("Error, child at "+childIndex+" has no valid parent");
			return -1;
		}
	}
	//===Helper Methods 
	public void swap(int a, int b){
		swaps++;
		int tmp = heap[a];
		heap[a] = heap[b];
		heap[b] = tmp;
	}
	public boolean isFull(){ //Make sure capacity is sufficient to
		if(n >= (size)){
			return true;
		} else{
			return false;
		}
	}
	public void stats(){
		System.out.println("---Heap Stats---");
		printHeap();
		System.out.print("          ---> "+n+"/"+size + "\n");
	}	
	public void adjustSize(){
		System.out.println("Heap is full, Doubling capacity to "+(size*2));
		stats();
		if(isFull()){
			size *= 2;
			heap = Arrays.copyOf(heap, size);
		}
	}
	public void printHeap(){
		System.out.print("HEAP ---> |");
		for(int i=0; i<heap.length; i++){
			System.out.print(" "+heap[i]+" ");
		}
		System.out.print("|\n");
	}
	public boolean isLeaf(int pos){return (pos >= n/2) && (pos < n);}
	public int getSize(){ return this.size;} //#elements in heap
	public int getCount(){ return this.n; }
}