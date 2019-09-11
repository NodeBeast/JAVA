
public class TwoThree {
	// =============================PROPERTIES
	Node3 root;
	int cmpBtwNodes;
	int newNodes;
	int totalAdds;
	int findCount;
	int nodeCount;

	// =============================CONSTRUCTORS
	public TwoThree() {
		this.root = new Node3();
		this.nodeCount = 1;
	}

	// =============================PRIMARY METHODS
	public void insert(int val) {
		if (root == null) { // =========EMPTY TREE
			Node3 r = new Node3(val);
			this.root = r;
			return; // DONE
		}

		boolean A = root.getA();
		boolean B = root.getB();
		boolean isLeaf = root.isLeaf();
		int va = root.getVal();
		int vb = root.getValB();

		// =======Branch Down to children (unless match)
		if (!isLeaf) {

			if (val < va) {// Branch left
				insert(root, root.getLeftChild(), val);
				return;
			} else if (!B && val > va) {
				root.setValB(val);
				return; // plop in and DONE
			} else if (B && val > vb) {// Branch right
				insert(root, root.getRightChild(), val);
				return;
			} else if (val == va || val == vb) {
				// Duplicate found - do nothing
				System.out.println("Insert: Duplicate value, Root already contains " + val);
				return;
			} else {// Branch Mid
				insert(root, root.getMidChild(), val);
				return;// DONE
			}
		}
		// ====Root IS LEAF: Insert values in order=====
		System.out.println("Root is a leaf and val=" + val);
		if (A && val < va) { // ====VAL < ValueA
			if (B) {// ---HAS B
				root.setTmpVal(va);// move A -> tmp
				root.setVal(val);// set ValueA to val
				promoteRoot(); // *PROMOTION*
				return; // DONE
			} else { // ---No B
				root.setValB(va);// Move A to B
				root.setVal(val); // Set valueA to val
				return; // DONE
			}

		} else if (!A) {
			root.setVal(val);
			return;

		} else if (B && val > vb) {
			root.setTmpVal(vb);// move
			root.setValB(val);
			promoteRoot(); // *PROMOTION*
			return;

		} else if (!B && val > va) {
			root.setValB(val);
			return;

		} else if (val == va || val == vb) {
			// MATCH CASE, do nothing
			System.out.println("Insert: Failed, " + val + " already exists in tree");
			return; // DONE

		} else { // ValA < val < ValB
			root.setTmpVal(val);
			promoteRoot(); // *PROMOTION*
			return;
		}
	}

	public Node3 insert(Node3 parent, Node3 rt, int val) {
		boolean a = rt.getA();
		boolean b = rt.getB();
		boolean isLeaf = rt.isLeaf();
		int va = rt.getVal();
		int vb = rt.getValB();

		if (isLeaf) { // -----------LEAF FOUND, stop!

			if (val < va) {// ............ val < ValueA
				if (a && !b) {// Room to spare
					rt.setValB(va);// Assign A to B
					rt.setVal(val); // Assign val to A
					return rt;

				} else if (a && b) {// No room, overloading node
					rt.setTmpVal(rt.getVal());// Move A to tmp
					rt.setVal(val);// Set A to val
					split(parent, rt);
					return rt;

				} else {
					System.out.println("Recursive Insert: Error!");
					return rt;
				}
			} else if (val > vb) {// ....... val > ValueB

				if (!b) {// Uninitialized: B = "-1"
					rt.setValB(val);// Init B

				} else { // B was valid, carry on
					rt.setTmpVal(rt.getValB());// Move B -> tmp
					rt.setValB(val);// Set new B value
					return rt;
				}
			} else {// ...................... ValueA < val < ValueB

				if (a && b) {
					rt.setTmpVal(val); // Set val to mid
					split(parent, rt); // promote mid of rt
					return rt;
				} else {
					System.out.println("Error, a && b was false");
				}
			}

		} else { // ----------------NOT LEAF! Recursive Branching

			if (val <= rt.getVal()) { // .....Branch left recursive

				if (rt.getLeftChild() != null) {
					insert(rt, rt.getLeftChild(), val);
					return rt;
				}

				Node3 x = new Node3(val);
				rt.setLeftChild(x);
				return rt;

			} else if (!b && val > rt.getVal()) {// .....Branch right recurs.
				insert(rt, rt.getRightChild(), val);// B Abs, cmp(A)
				return rt;

			} else if (val > rt.getValB()) { // B = true
				insert(rt, rt.getRightChild(), val);// B present, cmp(B)
				return rt;

			} else { // Branch Mid, A < val < B
				insert(rt, rt.getMidChild(), val);
				return rt;
			}
		}
		System.out.println("Terminating recursive insertion subroutine");
		return rt;
	}

	public void promoteRoot() {
		if (root.getA() && root.getB() && root.getTmp()) {
			// promotion conditions met
			Node3 vb = new Node3(root.getValB());
			Node3 vt = new Node3(root.getTmpVal());
			root.resetValues(); // valA
			vt.setLeftChild(root);
			vt.setRightChild(vb);
			root = vt;
			System.out.println("Promotion of ROOT success");
			return;
		} else {
			System.out.println("Promotion denied, conditions not met");
		}
	}

	public void split(Node3 parent, Node3 rt) {
		int pa = parent.getVal();// parent value
		int small = rt.getVal();// Node's left
		int mid = rt.getTmpVal();// Node's mid
		int big = rt.getValB();// Node's right
		boolean isRtLeftChild = parent.getLeftChild() == rt ? true : false;

		// Verification
		if (!(small <= mid && mid < big))
			System.out.println("Out of order");

		if (!parent.getB()) {// -------------Case 1: Parent has room

			// Set the parent left to smaller of mid | value
			if (mid > pa) { // Mid > Parent.valueA
				parent.setValB(mid);// affix mid to valB
			} else { // Mid <= pa
				parent.setValB(pa);// Move A -> B
				parent.setVal(mid);// Change A to mid
			}

			if (isRtLeftChild) { // Root was leftchild of its parent
				Node3 biggy = new Node3(big);
				rt.resetValues(); // left child of parent
				parent.setMidChild(biggy);// Point parent mid to biggy
			} else {// Root was rightChild of Parent
				Node3 smalls = new Node3(small);
				rt.resetValues(); // left child of parent
				parent.setMidChild(smalls);// Point parent mid to biggy
			}

		} else {// -----------------------------Case: Parent is full
			// Assume parent has 2 values (sorted)

		}
	}

	// public void delete(int val){}

	public boolean search(int val) {

		if (root != null) { // VALID Root
			int va = root.getVal();
			int vb = root.getValB();

			if (val == va || val == vb) {
				System.out.println("Search: value " + val + " found (in root node)");
				return true; // Value exists in root node
			}

			if (!root.isLeaf()) { // BRANCHING LEFT/MID/RIGHT
				if (val <= va) {// Early escape for val <= valueA
					if (search(root.getLeftChild(), val) == null) {
						return false;// LEFT Recursive search call
					}
				} else if (root.getB() && val < vb) {// val > valB
					if (search(root.getMidChild(), val) == null) {
						return false;// MIDDLE Recursive search
					}
				} else { // val > valueB: RIGHT recursive search branch
					if (search(root.getRightChild(), val) == null) {
						return false;
					}
				}
				System.out.println("Search: Value " + val + " --- found");
				return true;
			}

		}
		System.out.println("Search: Root is NULL");
		return false;

	}

	public Node3 search(Node3 rt, int val) {
		if (rt == null)
			return null;

		int va = rt.getVal();
		int vb = rt.getValB();

		if (va == val || vb == val) {
			return rt;
		}

		// BRANCHING LEFT/MID/RIGHT
		if (val < va) {// val < valueA
			Node3 temp = search(rt.getLeftChild(), val);// LEFT Recursive search
			return temp;
		} else if (rt.getB() && val > vb) {// val > valueB
			Node3 temp = search(rt.getRightChild(), val);
			return temp;
		} else {
			Node3 temp = search(rt.getMidChild(), val);// MIDDLE Recursive
														// search
			return temp;
		}
	}

	public void traversePreOrder(int opNum) {
		// After opNum Operations, call printPreOrder();
	}

	public void printPreOrder() {
	}

	public Node3 ppoUtil(Node3 rt) {// INCOMPLETE
		return rt;
	}

	public void printInOrder() { // BROKEN

		System.out.println("Printing 2-3 Tree In-Order: ");
		if (this.root == null) {
			System.out.println("NULL ROOT: Nothing to print");
			return;
		}
		// CHECK LEFT CHILD
		if (root.getLeftChild() != null) {
			pioUtil(root.getLeftChild());
		}

		// Print ValueA
		System.out.print(root.getVal() + ", ");

		// CHECK MID CHILD (if exists)
		if (root.getMidChild() != null) {
			pioUtil(root.getMidChild());
		}
		// Print ValueB (if exists)
		if (root.getB()) {
			System.out.print(root.getValB() + ", ");
		}

		if (root.getRightChild() != null) {
			pioUtil(root.getRightChild());
		}
	}

	public Node3 pioUtil(Node3 rt) {
		// CHECK LEFT CHILD
		if (rt.getLeftChild() != null) {
			return pioUtil(rt.getLeftChild());
		}
		// Print ValueA
		System.out.print(root.getVal() + ", ");
		// CHECK MID CHILD (if exists)
		if (rt.getMidChild() != null) {
			return pioUtil(rt.getMidChild());
		}
		// Print ValueB (if exists)
		if (rt.getB()) {
			System.out.print(root.getValB() + ", ");
		}
		// CHECK RIGHT CHILD
		if (rt.getRightChild() != null) {
			return pioUtil(rt.getRightChild());
		}
		return null;
	}

	public void printNode(Node3 rt) { // BROKEN
		Node3 left = rt.getLeftChild();
		Node3 mid = rt.getMidChild();
		Node3 right = rt.getRightChild();

		// Left Child
		if (left != null) {
			if (left.getA())
				System.out.print(left.getVal() + ", ");

			if (rt.getLeftChild().getB())
				System.out.print(left.getValB() + ", ");
		}
		// Self A
		System.out.print(rt.getVal() + ", ");
		// Mid Child
		if (mid != null) {
			if (mid.getA())
				System.out.print(mid.getVal() + ", ");

			if (right.getB())
				System.out.print(right.getValB() + ", ");
		}
		// Self B
		System.out.println(rt.getValB());
		// Right Child
		if (right != null) {
			if (right.getA())
				System.out.print(right.getVal() + ", ");

			if (right.getB())
				System.out.print(right.getValB() + ", ");
		}
	}

	public void printPreOrder(int opNum) {
		// Pre-order traversal
		// after
	}

	// =======================================HELPERS
	public int getOpNum() {
		return this.opNum;
	}

	public int getCmpBtwNodes() {
		return this.cmpBtwNodes;
	}

	public int getNewNodes() {
		return this.newNodes;
	}

	public int getTotalAdds() {
		return this.totalAdds;
	}

	public int getNodeCount() {
		return this.nodeCount;
	}

	public int getFindCount() {
		return this.findCount;
	}
//===========================================MAIN METHOD
	
	public static void main(String[] args) {
		System.out.println("   ===Testing Begin===");
		
		TwoThree r = new TwoThree();
		int opNum = 0;

		System.out.println("   ===Inserting Values===");
		r.insert(50);//ok
		r.insert(60);//ok
		r.insert(70);// Split
		r.insert(40);
		// r.insert(30);
		// r.insert(20);
		// r.insert(10);
		// r.insert(80);
		// r.insert(90);
		// r.insert(100);
		// r.printInOrder();
		
		System.out.println("   ===Printing Values===");
		r.printNode(r.root);
		System.out.println("\n   ===Ending Testing===");
		System.out.println("Number of Operations        => " + r.getOpNum());
		System.out.println("Number of Node Comparisons  => " + r.getCmpBtwNodes());
		System.out.println("Number of 2-3 Nodes created => " + r.getNewNodes());
		System.out.println("Number of Add operations    => " + r.getTotalAdds());
		System.out.println("Number of Find Operations   => " + r.getFindCount());
		System.out.println("Number of Remove Operations => ");
	}

}// =========================================END TwoThree