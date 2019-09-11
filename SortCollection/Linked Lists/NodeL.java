
public class NodeL {
	int value;
	NodeL next;
	
	//CONSTRUCTORS
	public NodeL(int val, NodeL next) {
		this.value = val;
		this.next = next;
	}
	public NodeL(int val){
		this.value = val;
		this.next = null;
	}
	public NodeL(NodeL nextNode){
		this.next = nextNode;
	}
	
	//METHODS
	public int getVal(){
		return this.value;
	}
	public void setVal(int val){
		this.value = val;
	}
	public NodeL getNext(){
		return this.next;
	}
	public void setNext(NodeL n){
		this.next = n;
	}
	public void  
}
