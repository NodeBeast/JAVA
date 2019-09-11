
public class RedBlackTree {
	// 2. Root node is black
	// 3. All leaves are black
	// 4. If node is red, both children are black
	// 5. Path from a node to any of its descendants
	// Max height of log n
	// Rotation - decrease height: larger subtrees up, smaller down
	// ===============CLASS PROPERTIES
	NodeRB root;
	int nodeCount;

	// ================CONSTRUCTORS
	public RedBlackTree() {
		this.root = new NodeRB(); // Set Root node v=-1, black
		this.nodeCount = 0;
	}

	// ===============CENTRAL METHODS

	public Node search(Node rt, int searchVal) { // returns Node
		if (rt == null) {
			System.out.println("Search: returned null");
			return null;// Start at the root, if null (empty), return null
		}

		if (rt.getVal() == searchVal) {
			System.out.println("Search: Match Case: " + rt.getVal());
			return rt; // Matching search key, return corresponding node
		}

		if (rt.getVal() > searchVal) { // proceed left recursively
			return search(rt.getLeftChild(), searchVal);
		} else if (rt.getVal() < searchVal) { // proceed right
			return search(rt.getRightChild(), searchVal);
		} else {
			System.out.println("Search: What the!?");
			return null;
		}
	}

	public void insert(NodeRB rt, int val) {
		//Check current node for value
		// return NodeRB 
	}

	// public Node delete(){}

	public void deleteNode(Node rt, int val) { // INCOMPLETE
		
	}

	public Node getReplacement(Node rt) {
		// Either choose the rightmost left node or vice versa
		Node tmp = rt.getRightChild();

		while (tmp.getLeftChild() != null) {
			tmp = tmp.getLeftChild();
		}
		return tmp;
	}

	// public void rotateLeft(){}
	// public void rotateRight(){}

	// =================HELPER METHODS

	public int getHeight() {
		// Height: #edge from root to deepest node
		// recursive maxDepth call on left subtree
		// recursive maxDepth on right subtree

		if (root.isLeaf()) {// tree is empty, return 0
			return 0;

		} else if (root.getLeftChild()!=null && root.getRightChild()!=null) {
			return 1 + Math.max(depthOf(root.getLeftChild()), depthOf(root.getRightChild()));

		} else if (root.getLeftChild()!=null) {
			return 1 + depthOf(root.getLeftChild());

		} else if (root.getRightChild()!=null) {
			return 1 + depthOf(root.getRightChild());

		} else {
			System.out.println("getDepth: Exception/Error");
			return 0;
		}
	}

	public int depthOf(Node rt) {
		if (rt.isLeaf()) {// tree is empty, return 0
			return 0;

		} else if (rt.getLeftChild()!=null && rt.getRightChild()!=null) { // 2 Children
			return 1 + Math.max(depthOf(rt.getLeftChild()), depthOf(rt.getRightChild()));

		} else if (rt.getLeftChild()!=null) { // Only Left child
			return 1 + depthOf(rt.getLeftChild());

		} else if (rt.getRightChild()!=null) { // Only right child
			return 1 + depthOf(rt.getRightChild());

		} else {
			System.out.println("getDepth: Exception/Error");
			return 0;
		}
	}

	public Node getRoot() {
		return this.root;
	}

	public int getRootVal() {
		return this.root.getVal();
	}

	public int getCount() {
		if (root == null) { // Base Case
			return 0;
		}
		int c = 1;
		if (root.getLeftChild()!=null) {
			c = c + getCountOfSubTree(root.getLeftChild());
		}

		if (root.getRightChild()!=null) {
			c = c + getCountOfSubTree(root.getRightChild());
		}
		return c;
	}

	public int getCountOfSubTree(Node rt) {
		int c = 1;
		if (rt == null) { // Base Case
			return 0;
		}
		if (rt.getLeftChild()!=null) { //
			System.out.println("Counting left child");
			c = c + getCountOfSubTree(rt.getLeftChild());
		}
		if (rt.getRightChild()!=null) {
			c = c + getCountOfSubTree(rt.getRightChild());
		}
		return c;
	}

	// ===================BOOLEAN CHECKERS

	public boolean validate(Node rt) {
		int min = 0;
		int max = Integer.MAX_VALUE;
		return checkTree(rt, min, max);
	}

	public boolean checkTree(Node rt, int min, int max) { // Respect BST rules?
		// are all left/right nodes & subnodes less/greater than their parents?
		if (rt == null) {
			return true;
		}
		if (rt.getVal() > max || rt.getVal() < min) {
			System.out.println("CheckBST: Node out of bounds " + rt.getVal());
			return false; // not within range
		}
		return checkTree(rt.getLeftChild(), min, rt.getVal()) && checkTree(rt.getRightChild(), rt.getVal(), max);
	}

	public boolean hasValue() { // Not null nor '-1' --> TRUE
		if ((this.root != null) && (this.root.getVal() != -1)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isLeaf(Node n) {
		if (n.getLeftChild() != null || n.getRightChild() != null) {
			return false;
		} else {
			return true;
		}
	}

	public double log2(double d) {
		return (int) Math.log(d) / Math.log(2);
	}

	public boolean isTooHigh() {
		int N = getCount();
		int height = getHeight();
		int maxHeight = (int) log2(N);
		System.out.println("With N=" + N + " elements, tree has Height= " + height + " & MaxHeight= " + maxHeight);
		if (height > maxHeight) {
			return false;
		} else {
			return true;
		}

	}

}
