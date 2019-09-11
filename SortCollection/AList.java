class AList<E> implements List<E> {
	private static final int defaultSize = 10; // Default size
	private int maxSize; // Maximum size of list
	private int listSize; // Current # of list items
	private int curr; // Position of current element
	private E[] listArray;
	//Constructors
	AList() { this(defaultSize); }
	AList(int size) {
		maxSize = size;
		listSize = curr = 0;
		listArray = (E[])new Object[size]; // Create listArray
	}
	//METHODS
	public void clear() { listSize = curr = 0; }
	public void insert(E it) {
		assert listSize < maxSize : "List capacity exceeded";
		for (int i=listSize; i>curr; i--) // Shift elements up
		listArray[i] = listArray[i-1]; // to make room
		listArray[curr] = it;
		listSize++; // Increment list size
	}
	public void append(E it) {
		assert listSize < maxSize : "List capacity exceeded";
		listArray[listSize++] = it;
		} 
	public E remove() {
		if ((curr<0) || (curr>=listSize)) // No current element
		return null;
		E it = listArray[curr]; // Copy the element
		for(int i=curr; i<listSize-1; i++) // Shift them down
		listArray[i] = listArray[i+1];
		listSize--; // Decrement size
		return it;
	}
	public void moveToStart() { curr = 0; } // Set to front
	public void moveToEnd() { curr = listSize; } // Set at end
	public void prev() { if (curr != 0) curr--; } // Back up
	public void next() { if (curr < listSize) curr++; }
	public int length() { return listSize; }
	public int currPos() { return curr; }
	public void moveToPos(int pos) {
		assert (pos>=0) && (pos<=listSize) : "Pos out of range";
		curr = pos;
	} 
	public E getValue() {
		assert (curr>=0) && (curr<listSize) : "No current element";
		return listArray[curr];
	}
}