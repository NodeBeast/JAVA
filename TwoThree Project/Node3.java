
public class Node3{
	// ==========================PROPERTIES
	public int value;
	public int tmpVal; // Temp middle value
	public int valueB; // Greater value
	public boolean a, b; // true if value is initialized
	public boolean tmp;  // true if overloaded (tmpVal) 
	
	public Node3 leftChild;
	public Node3 rightChild;
	public Node3 midChild;
	// ==========================CONSTRUCTORS
	public Node3() { // Default Constructor
		this.value = -1; // Indicates invalid input
		this.valueB = -1;
		this.tmpVal = -1;
		this.a = false;
		this.b = false;
		this.tmp = false;
		this.leftChild = null;
		this.rightChild = null;
	}

	public Node3(int val) {
		this.value = val;
		this.valueB = -1;
		this.tmpVal = -1;
		this.a = true;
		this.b = false;
		this.tmp = false;
		this.leftChild = null;
		this.rightChild = null;
		this.midChild = null;
	}

	public Node3(int a, int b) { // a < b
		this.value = a;
		this.valueB = b;
		this.a = true;
		this.b = true;
		this.tmp = false;
		this.leftChild = null;
		this.rightChild = null;
		this.midChild = null;
	}
	// ==========================GETTERS
	public Node3 getLeftChild() {
		return this.leftChild;
	}
	public Node3 getRightChild() {
		return this.rightChild;
	}
	public Node3 getMidChild() {
		return this.midChild;
	}

	public int getVal(){
		return this.value;
	}
	public int getValB() {
		return this.valueB;
	}
	public int getTmpVal() {
		return this.tmpVal;
	}
		
	public boolean getA() {
		this.a = this.value==-1 ? false : true;
		return this.a;
	}
	public boolean getB() {
		this.b = this.valueB==-1 ? false : true;
		return this.b;
	}
	public boolean getTmp() {
		this.tmp = this.tmpVal==-1 ? false : true;
		return this.tmp;
	}
	//============================TAKERS
	public int takeVal(){ //Return value & remove from this
		int v = this.value;
		this.value = -1;
		this.a = false;
		return v;
	}
	public int takeValB(){
		int v = this.valueB;
		this.valueB = -1;
		this.b = false;
		return v;
	}
	public int takeTmpVal(){
		int v = this.tmpVal;
		this.tmpVal = -1;
		this.tmp = false;
		return v;
	}
	
	public Node3 takeLeftChild() {
		Node3 x = this.leftChild;
		this.leftChild = null;
		return x;
	}
	public Node3 takeMidChild() {
		Node3 x = this.midChild;
		if(midChild==null) {
			System.out.println("Tried to take null midChild");
		} else{
			this.midChild = null;
		}
		return x;
	}
	public Node3 takeRightChild() {
		Node3 x = this.rightChild;
		this.rightChild = null;
		return x;
	}
	//============================SETTERS
	public void setVal(int val) {
		if(val == -1){
			this.a = false;
			
		} else{
			this.a = true;
		}
		this.value = val;
	}
	public void setValB(int val) {
		if(val == -1){
			this.b = false;
			
		} else{
			this.b = true;
		}
		this.valueB = val;
	}
	public void setTmpVal(int val) {
		if(val == -1){
			this.tmp = false;
			
		} else{
			this.tmp = true;
		}
		this.tmpVal = val;
	}

	public void setLeftChild(Node3 rt) {
		this.leftChild = rt;
	}
	public void setRightChild(Node3 rt) {
		this.rightChild = rt;
	}
	public void setMidChild(Node3 rt) {
		this.midChild = rt;
	}

	public void resetValues(){
		this.a = true;
		this.b = false;
		this.tmp = false;
		this.valueB = -1;
		this.tmpVal = -1;
	}
	// ==========================BOOLEAN
	public boolean isLeaf() {
		return this.leftChild == null;
	}
	public boolean isTwoNode(){
		if(a && !b){
			return true;
		} else {
			return false;
		}
	}
	public boolean isThreeNode(){
		if(a && b){
			return true;
		} else {
			return false;
		}
	}
}
