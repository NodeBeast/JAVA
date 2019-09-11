/* If you know the size, use Array-list of objects
 * inserting to the end is O(1) but 
 * Inserting at head costs O(n), elements must be shifted
 * 
 * Linked list Implementation 
 * 
 */
public class LinkedList {
	int length; //length of list
	NodeL head;
	NodeL curr;
	NodeL tail;
	//CONSTRUCTOR
	public LinkedList() {
		head = new NodeL(-1);
		curr = head;
		tail = head;
		length = 0;
	}
	
	//METHODS
	public void insertVal(int val){
		length++;
		NodeL nl = new NodeL(val);
		nl.setNext(curr.getNext());
		if(curr == head){
			head.setNext(nl);

		} else{
			nl.setNext(curr.getNext());//set new node --> curr's next
			curr.setNext(nl);//set curr's next
		}		
	}
	public void insertNode(NodeL ins){
		length++;
		if(tail == curr){
			curr.setNext(ins);
			ins.setNext(null);
			tail = ins;
		} else{
			
		}
	}
	public void append(NodeL ins){
		//Add element to end of list
		tail.setNext(ins);
		tail = ins;
		tail.setNext(null);
	}
	public NodeL remove(){
		return curr;
	}
	public void moveToPos(NodeL pos){
		curr = pos;
	}
	public void moveToStart(){
		curr = head;
	}
	public void moveToEnd(){
		curr = tail;
	}
	public void prev(){
		//change currentPos to previous element
	}
	public NodeL next(){
		return curr.getNext();
	}
	public int getLength(){
		return this.length;
	}
	public boolean isEmpty(){
		return (curr == head);
	}
	//getValue(){ //returns value of current element;
	//getCurrent(){
	//next()
	//prev()
	//show()
	
	public static void main(String[] args) {
		LinkedList LL = new LinkedList();
		int[] data = {1, 2, 3, 4, 5};
		for(int i=0; i<data.length; i++){
			
			LL.insertVal(data[i]);
		}
			

	}

}
