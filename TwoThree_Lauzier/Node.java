
public class Node {
	// ==========================PROPERTIES
	public int value;
	public Node leftChild;
	public Node rightChild;
	
	// ==========================CONSTRUCTORS
	public Node() {
		this.value = -1;
		this.leftChild = null;
		this.rightChild = null;
	}
	
	public Node(int val) {
		this.value = val;
		this.leftChild = null;
		this.rightChild = null;
	}

	// ==========================GETTERS & SETTERS

	public int getVal() {
		return this.value;
	}

	public void setVal(int val) {
		System.out.println("SetVal: Set to " + val);
		this.value = val;
	}

	public Node getRightChild() {
		return this.rightChild;
	}

	public int getRightChildValue() {
		return this.rightChild.getVal();
	}

	public Node getLeftChild() {
		return this.leftChild;
	}

	public int getLeftChildValue() {
		return this.leftChild.getVal();
	}

	// ==========================BOOLEAN CHECKERS
	public boolean isLeaf() {
		return (this.leftChild == null && this.rightChild == null);
	}

}