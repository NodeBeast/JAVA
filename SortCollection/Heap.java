import java.util.Arrays;

public class Heap{
	/*MIN HEAP - Efficient, array-based O(nlog n) in all cases
	Partially ordered, special case Priority queue
	Good when data does not fit in main memory
	N-deletes: O(n log n)
	Removing top of heap: O(log n)
	Insertion/build-heap: O(n)
	Height of nlogn
		COMPLETE BINARY TREE (Property)
			Filled level by level, left to right
	
		inserting: insert at bottom of heap, and it bubbles up into position
		deleting root, then plug hole with bottom element (rightmost leaf), heafify
		compare root with children, swap with smaller of the two (if root is bigger)
	indexParent = (ParentIndex-1)/2
	indexLeftChild = ParentIndex x2 +1
	indexRightChild = ParentIndex x 2 +2
	 
	 */
	public static void main(String[] args){
		Heap h = new Heap(10);
		System.out.println("New heap size: "+h.getSize());
		for(int i=0; i<h.getSize(); i++){
			System.out.println("Parent of "+i+" is at "+h.parentIndex(i));
			//System.out.println("Right child of "+i+" is "+h.rightChildIndex(i));
			System.out.println("\n");
		}
	}
	
	//=============================================
	//PARAMETERS
	int size; //Current size of array
	int[] heap;
	int count; //Elements in heap
	int index; //Current position
	//CONSTRUCTORS
	public Heap(int heapSize){
		size = heapSize;
		heap = new int[size];
		count = 0;
		index = 0;
	}
	//PRIMARY METHODS
	public void buildHeap(int[] arr, int n){
		for(int i = n/2; i>0; i--){
			//Heapify(arr, i, n);
		}
	}
	
	public void add(int insert){
				//ensure sufficient capacity
		//insert to tree from left to right 
		//check heap property
		//arr[size] = insert;
		//size++;
		if(!isFull()){
			heap[++index] = insert;
		}
		heapifyUp();
	}
	
	public void maxHeapify(){}
	
	public void heapifyUp(){
		int i = size - 1; // Start at end of arr[]
		//while (arr[i] has a parent ie: not rt) && (parentOf(i) > arr[i])
			//swap node with it's parents - i = getParentIndex(index)
	}
	
	public void heapifyDown(){
		int i = 0; // Start at min (top of heap)
		//while (node has a left child)
			//if( hasRightChild(i) && rightChild(i) < leftChild(i)
				//smaller child is the right child
			//if arr[i] < arr[smallerChildIndex]
				//break;
			//else swap(i, smallerChildIndex);
			
			//i = smallerChildIndex;
			//if arr[i] < arr[
	}
	
	public int peek() { //Returns top of heap
		return heap[0];
	}
	
	public int poll(){
		if(count == 0){
			System.out.println("Nothing in Heap, cannot poll");
			return -1;
		} else{
			return heap[0];
		}
		
		//Poll method
	//if empty, throw error
	//size--
	//call HeapifyDown
	//return arr[0]
	}
	
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
	
	public boolean hasLeftChild(int index){
		return (leftChildIndex() < count);
	}


	
	//Swap Method 
	public static void swap(int[] arr, int left, int right){}
	
	 
	public boolean isFull(){ //Make sure capacity is sufficient to
		if(count >= (size)){
			return true;
		} else{
			return false;
		}
	}
	public void adjustSize(){
		if(isFull()){
			size *= 2;
			heap = Arrays.copyOf(heap, size);
		}
	}
	
	public int getSize(){
		return this.size;
	}
	public int getCount(){
		return this.count;
	}

	public int getIndex(){
		return this.index;
	}
	
	
}