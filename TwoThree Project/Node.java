public class Node {
	// ==========================PROPERTIES
	private int value;

	// ==========================CONSTRUCTORS
	public Node() {
		this.value = -1;
	}

	public Node(int val) {
		this.value = val;
		this.red = true;
		this.leftChild = null;
		this.rightChild = null;
	}

	public Node(int value, boolean red) {
		this.value = value;
		this.red = red;
		this.leftChild = null;
		this.rightChild = null;
	}

	// ===========================PRIMARY METHODS
	
	public void insertNodeValue(int val) {
		if (val <= this.value) { // Insert left
			if (this.hasLeftChild()) { // Insert left
				// has a left child:
				leftChild.insertNodeValue(val);
			} else { // Right Child == null
				Node left = new Node(val);
				leftChild = left;
			}
		} else if (val > this.value) { // insert right
			if (this.hasRightChild()) {
				// right child is leaf
				rightChild.insertNodeValue(val);// Insert val at right child
			} else {
				Node right = new Node(val);
				rightChild = right;
			}
		}
	}

	// ============================PRINTING METHODS

	public void printTree(Node rt) {
		System.out.println(value);// Print root
		printTreeUtil(rt, 1);
	}

	public void printTreeUtil(Node rt, int rightBranches) {
		if (rt.hasLeftChild()) {
			System.out.print(rt.value + "  ");
			printTreeUtil(rt.leftChild, rightBranches);
			rightBranches++;
		}

		if (rt.hasRightChild()) {
			for (int i = 0; i < rightBranches + 2; i++)
				System.out.println(" ");
			System.out.print(rt.value + "  ");
			rightBranches++;
			printTreeUtil(rt.rightChild, rightBranches);
		}
	}

	public void printInOrder() {
		// Print All left subtrees
		if (this.hasLeftChild()) {
			this.leftChild.printInOrder();
		}
		// Then, print this.value
		System.out.println(this.value);
		// Print all left subtrees
		if (this.hasRightChild()) {
			this.rightChild.printInOrder();
		}
	}

	public void printPreOrder() {
		// Print root, then left/right children
		System.out.println(this.value);
		if (this.hasLeftChild()) {
			getLeftChild().printPreOrder();
		}
		if (this.hasRightChild()) {
			getRightChild().printPreOrder();
		}
	}

	public void printPostOrder() {
		if (this.hasLeftChild()) {
			getLeftChild().printPostOrder();
		}
		if (this.hasRightChild()) {
			getRightChild().printPostOrder();
		}
		System.out.println(this.value);
	}

	public void printNodeStatus(Node rt) {
		System.out.print("Root Value: " + rt.value);
		if (rt.hasLeftChild()) {
			System.out.print(" has left child: " + rt.getLeftChildValue());
		}
		if (rt.hasRightChild()) {
			System.out.print(" has right child: " + rt.getRightChildValue());
		}
		System.out.println(" ");
	}

	// ==========================GETTERS & SETTERS

	public int getVal() {
		return this.value;
	}

	public void setVal(int val) {
		System.out.println("SetVal: Set to " + val);
		this.value = val;
	}

	public Node getParent() {
		if (this.parent != null) {
			return this.parent;
		} else {
			System.out.println("Null parent pointer, root?");
			return this.parent;
		}
	}

	public void setParent(Node adopt) {
		if (!isRoot()) {
			this.parent = adopt;
		} else {
			System.out.println("setParent: Error, isRoot");
		}

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
	public boolean isRoot() {
		if (this.parent == null) {
			return true;
		} else
			return false;
	}

	public boolean isRed() {
		return (this.red == true);
	}

	public boolean isLeaf() {
		return (this.leftChild == null && this.rightChild == null);
	}

	public boolean hasLeftChild() {
		return (this.leftChild != null);
	}

	public boolean hasRightChild() {
		return (this.rightChild != null);
	}

}