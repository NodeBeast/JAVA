
public class JumpList {
	// PROPERTIES
	public ARun[] jlist; // Circular Array of (runs & rlengths)
	public int head = 0; // Queue head index
	public int current = 0;
	public int tail = 0; // Queue tail index
	public int count = 0;
	public int MAX_LENGTH;

	// CONSTRUCTORS
	public JumpList(int MAX_LENGTH) { // Specify max list size
		this.MAX_LENGTH = MAX_LENGTH;
		this.jlist = new ARun[MAX_LENGTH];
	}

	public JumpList(int[] arr) { // Arr-Load: 'Singles' run (No-Struct)
		this.MAX_LENGTH = arr.length;
		this.jlist = new ARun[MAX_LENGTH];
		for (int i = 0; i < arr.length; i++) { //
			enqueue(new ARun(i, 1));// N runs with length=1
		}
	}

	// MAIN METHODS
	public void enqueue(ARun run) { // Add run to tail--
		if (!isFull()) { // Array not full, proceed
			System.out.println("Enqueue ARun = {Run="+run.getRun()+
					", Length="+run.getLength() );
			jlist[tail] = run;
			tail++;
			count++;
			if (tail == MAX_LENGTH) {
				System.out.println("Enqueue: Wrapping tail");
				tail = 0;
			}
		} else {
			System.out.println("Enqueue: The array is full, failed to load");
			return;
		}
	}

	public ARun dequeue() { // Remove ARun from head--
		ARun tmp;
		if (!isEmpty()) { // If Not Empty
			tmp = jlist[head];
			jlist[head] = null; // Remove head element from jlist
			System.out.println("Dequeue: Successful, New Count="+(this.count-1));
			head++;
			count--;
			return tmp;
		} else {
			System.out.println("ERROR! Cannot Dequeue, empty queue");
			return null;
		}
	}
	// DISPLAY METHODS
	public void showAll() {
		if(this.count == 0) System.out.println("ShowAll: ---EMPTY ARUN[]---\n");
		
		for (int i = this.head; i < (this.head+this.count); i++) {
			System.out.println("    ARun[" + i + "] --> RunStart = " +
		jlist[i].getRun()+" Length = " + jlist[i].getLength());
		}
		System.out.println(" ");
	}
	/*public void showAllARuns(int[] d, Jumplist jl){
		int i=jl.getHead();	//index of head of the queue
		int count = jl.getCount();
		for(int k=0; k < ; k++) { //k iterates runs
			
			int runStart = jl.getRun(i); 	// j is the start of pos in arr
			int runEnd = runStart + jl.getLength();
			
		}
	}*/
	
	public void displayHT() {
		System.out.println("Head=" + getHead() + " & Tail=" + getTail());
	}

	public void displayCount(){
		System.out.println("Current #ARuns Count="+this.count);
	}
	// GETTERS
	public int getHead() {
		return this.head;
	}

	public void setHead(int h) {
		this.head = h;
	}
	
	public int getTail() {
		return (this.tail-1);
	}

	public int getCount() {
		return this.count;
	}

	public int getMaxLength() {
		System.out.println("MAX_LENGTH ARuns: " + this.MAX_LENGTH);
		return MAX_LENGTH;
	}

	public void setJumpListLength(int max){ //INCOMPLETE
		this.MAX_LENGTH = max;
		//resize(jlist, MAX_LENGTH);
	}
	
	public int getLength(int index) { // length of JL[index]
		return jlist[index].getLength();
	}

	public int getTailRun() { // Get run (index) of current ARun
		return jlist[this.tail-1].getRun();
	}

	public int getTailLength() {
		return jlist[this.tail-1].getLength();
	}
	
	public void incTailLength(int index){ // At ARun[index]
		//System.out.println("Incrementing Run Length of ARUN["+index+"]");
		jlist[index].incLength();
	}
	
	public boolean isEmpty() {
		return (count == 0);
	}

	public boolean isFull() {
		if (count >= jlist.length) {
			System.out.println("isFull: FULL!");
			return true;
		} else {
			return false;
		}
	}

	public int getRun(int x) {
		if(jlist[x] != null){
			return jlist[x].getRun();
		} else{
			System.out.println("ERROR! Called JL.getRun("+x+") = NULL");
			return 0;
		}
		
	}

}