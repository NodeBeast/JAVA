import java.io.*;

public class TwoThree {

	public static void main(String[] args) throws Exception {
		
		//STRING ARGS INPUT
		String path = args[0];
		int stepsToTraverse = Integer.parseInt(args[1]);
		System.out.println("Reading in "+path+"  "+stepsToTraverse);
		
		//READ IN OPERATIONS & STORE IN ARRAY
		String[] cmds = new String[stepsToTraverse+100];
		File f = new File(path);
		BufferedReader buf = new BufferedReader(new FileReader(f));
		int i=0;
		while((cmds[i] = buf.readLine()) != null){
			if(i+1==stepsToTraverse) break;
			i++;
		}
		
		//NEW INSTANCE OF 2-3 TREE
		TwoThree r = new TwoThree();
		//EXECUTE OPERATIONS
		char cmd;
		int val;
		for(int j=0; j<stepsToTraverse; j++){
			cmd = cmds[j].charAt(0);
			val = Integer.parseInt(cmds[j].substring(1));
			
			if(cmd == 'a'){
				System.out.println("Insert: "+val);
				r.insertion(val);
			} else if(cmd == 'f'){
				System.out.println("Finding: "+val+": "+ r.search(val));
			} else{
				System.out.println("Operation "+cmd+" is was not recognized");
			}
		}
		//POST ANALYSIS
		System.out.println("Number of Node Comparisons  => " + r.getCmpBtwNodes());
		System.out.println("Nodes created               => " + r.getNewNodes());
		System.out.println("Number of Add operations    => " + r.getTotalAdds());
		System.out.println("Number of Find Operations   => " + r.getFindCount());
		System.out.println("Number of remove operations => " + r.getRemoveOps());
		r.printPreOrder(stepsToTraverse);
	}

	// =============================PROPERTIES
	Node3 root;
	int cmpBtwNodes;// Ask prof
	int newNodes;// Every time new Node3
	int totalAdds;
	int findCount;
	int nodeCount;
	int removeOps;

	// =============================CONSTRUCTORS
	public TwoThree() {
		this.root = new Node3();
		this.cmpBtwNodes = 0;
		this.newNodes = 0;
		this.totalAdds = 0;
		this.findCount = 0;
		this.removeOps = 0;
		this.nodeCount = 0;
	}

	// =============================INSERTION

	public void insertion(int val) {
		Node3 insertNode = searchInsert(val);
		totalAdds++;
		if (insertNode.isTwoNode()) {
			if (val < insertNode.getVal()) {
				// shift over A and place val
				cmpBtwNodes++;
				insertNode.setValB(insertNode.getVal());
				insertNode.setVal(val);
			} else if (val > insertNode.getVal()) {
				cmpBtwNodes += 2;
				insertNode.setValB(val);
			} else {
				System.out.println("Duplicate Value, insert aborted");
				cmpBtwNodes += 2;
				return;
			}
		} else if (insertNode.isThreeNode()) { // insertNode is 3-Node
			cmpBtwNodes++;
			if(insertNode.getVal()==val || insertNode.getValB() ==val){
				System.out.println("Duplicate Value, insert aborted");
				return;
			}
			insertNode.setTmpVal(val);
			promote(insertNode);
		} else {
			insertNode.setVal(val);
		}

	}

	public Node3 searchInsert(int v) { // Returns node for insertion of val
		Node3 current = root;
		while (!current.isLeaf()) {
			if (current.isTwoNode()) {
				current = branch2(current, v);
			} else if (current.isThreeNode()) {
				current = branch3(current, v);
			} else {
				System.out.println("SearchInsert Failed");
				return null;
			}
		}
		return current;
	}

	public Node3 branch2(Node3 rt, int val) {
		int va = rt.getVal();
		if (val < va) {
			cmpBtwNodes++;
			return rt.getLeftChild();
		} else if (val > va) {
			cmpBtwNodes+=2;
			return rt.getRightChild();
		} else {// val == va
			cmpBtwNodes+=2;
			System.out.println("Branch2: Aborting - Duplicate Value, " + val);
			return null;
		}
	}

	public Node3 branch3(Node3 rt, int val) {
		int va = rt.getVal();
		int vb = rt.getValB();
		if (val < va) {
			cmpBtwNodes++;
			return rt.getLeftChild();
		} else if (val > vb) {
			cmpBtwNodes+=2;
			return rt.getRightChild();
		} else if (val < vb) {
			cmpBtwNodes+=3;
			return rt.getMidChild();
		} else {
			System.out.println("Branch3: Duplicate Value, stopping insertion of " + val);
			cmpBtwNodes+=3;
			return null;
		}
	}

	public void promoteRoot() {
		newNodes += 2;
		Node3 newRoot = new Node3(root.takeTmpVal());
		Node3 newRight = new Node3(root.takeValB());
		newRoot.setLeftChild(root);
		newRoot.setRightChild(newRight);
		root = newRoot;
		return;
	}

	public void promote(Node3 rt) {
		sortABC(rt); // Sort elements if needed
		Node3 parent = getParent(rt); // parent of rt

		if (parent != null) {
			int promote = rt.takeTmpVal();// Extract middle value
			if (parent.isTwoNode()) {// ======Parent is a 2-Node
				if (parent.getLeftChild() == rt) {
					newNodes++;
					Node3 midNode = new Node3(rt.takeValB());
					parent.setMidChild(midNode);
					parent.setValB(parent.takeVal());
					parent.setVal(promote);
					return; // DONE
				} else if (parent.getRightChild() == rt) {
					newNodes++;
					Node3 midNode = new Node3(rt.takeVal());
					parent.setMidChild(midNode);//
					parent.setValB(promote);//
					rt.setVal(rt.takeValB());//
					return;
				} else {
					System.out.println("Promotion failed");
					return;
				}
			}

			// ==========================PARENT IS 3-Node
			else if (parent.isThreeNode()) {
				System.out.println("Promoting value=" + promote + ", parent is 3-Node");
				Node3 leftParent, leftLeft, leftRight, rightParent, rightLeft, rightRight;

				// ---LEFTCHLD CASE
				if (rt == parent.getLeftChild()) {
					newNodes+=2;
					int x = parent.getVal();
					parent.setVal(promote);
					promote = x;// update promote
					// Renaming & creation of nodes
					leftParent = parent;
					rightParent = new Node3(leftParent.takeValB());
					// LeftParent Children
					leftLeft = rt;
					leftRight = new Node3(leftLeft.takeValB());
					// RightParent Children
					rightLeft = leftParent.takeMidChild();
					rightRight = leftParent.takeRightChild();
					// Set Parent Pointers
					// leftParent.setLeftChild(leftLeft);
					leftParent.setRightChild(leftRight);
					rightParent.setLeftChild(rightLeft);
					rightParent.setRightChild(rightRight);

					if (leftParent == root) { // NEW ROOT CASE
						newNodes++;
						Node3 newRoot = new Node3(promote);
						newRoot.setLeftChild(leftParent);
						newRoot.setRightChild(rightParent);
						root = newRoot;
						return; //
					} else {// Overload & promote GrandParent
						Node3 grandParent = getParent(leftParent);
						grandParent.setTmpVal(promote);// overload
						sortABC(grandParent);
						promote(grandParent);// RECURSIVE CALL
						return;
					}
				}

				// ----------------MIDCHLD CASE
				else if (rt == parent.getMidChild()) {
					newNodes+=2;
					// No need to update promote
					// Renaming & creation of nodes
					leftParent = parent;
					rightParent = new Node3(leftParent.takeValB());
					// Children
					leftLeft = leftParent.getLeftChild();
					leftRight = rt;
					rightLeft = new Node3(leftRight.takeValB());
					rightRight = leftParent.takeRightChild();
					// Set Parent Pointers
					// leftParent.setLeftChild(leftLeft);
					leftParent.setRightChild(leftParent.takeLeftChild());
					rightParent.setLeftChild(rightLeft);
					rightParent.setRightChild(rightRight);

					if (parent == root) { // NEW ROOT CASE
						newNodes++;
						Node3 newRoot = new Node3(promote);
						newRoot.setLeftChild(leftParent);
						newRoot.setRightChild(rightParent);
						root = newRoot;
						return; // DONE

					} else {// Overload & promote GrandParent
						Node3 grandParent = getParent(leftParent);
						grandParent.setTmpVal(promote);// overload
						promote(grandParent);// RECURSIVE CALL
						return;
					}
				}

				// -------------RIGHTCHLD CASE
				else if (rt == parent.getRightChild()) {
					newNodes+=2;
					parent.setTmpVal(promote);
					sortABC(parent);
					promote = parent.takeTmpVal();// update promote
					// Renaming & creation of nodes
					rightParent = parent;
					leftParent = new Node3(rightParent.takeVal());
					rightParent.setVal(rightParent.takeVal());
					// LeftParent Children
					leftLeft = rightParent.takeLeftChild();
					leftRight = rightParent.takeMidChild();
					// RightParent Children
					rightRight = rt;
					rightLeft = new Node3(rightRight.takeVal());
					rightRight.setVal(rightRight.takeValB());
					// Set Parent Pointers
					leftParent.setLeftChild(leftLeft);
					leftParent.setRightChild(leftRight);
					rightParent.setLeftChild(rightLeft);
					// rightParent.setRightChild(rightRight);

					if (rightParent == root) { // NEW ROOT CASE
						newNodes++;
						Node3 newRoot = new Node3(promote);
						newRoot.setLeftChild(leftParent);
						newRoot.setRightChild(rightParent);
						root = newRoot;
						return; // DONE

					} else {// Overload & promote GrandParent
						Node3 grandParent = getParent(leftParent);
						grandParent.setTmpVal(promote);// overload
						promote(grandParent);// RECURSIVE CALL
						return;
					}
				} else { // Parent
					System.out.println("Error promoting");
					return;
				}
			}
		} else {// Parent is null (rt == ROOT)
			promoteRoot();
			return;
		}
	}

	public void sortABC(Node3 rt) { // Sort Node w/ 3 va, vb, vtmp
		// take node rt and check that values are in correct order
		int vt = rt.getTmpVal();
		int va = rt.getVal();
		int vb = rt.getValB();

		if (rt.isThreeNode()) { // ==========3-Node
			cmpBtwNodes+=2;
			if (va < vt) {

				if (vt < vb) {
					return;
				} else {
					rt.setTmpVal(vb);
					rt.setValB(vt);
					return;
				}
			} else { // va >= vt
				rt.setVal(vt);
				rt.setTmpVal(va);
				if (va < vb) {
					return;
				} else {
					rt.setValB(va);
					rt.setTmpVal(vb);
				}
			}
		}
	}

	public Node3 getParent(Node3 iChild) { // INCOMPLETE
		if (iChild == root) {
			return null;
		}
		
		int v = iChild.getVal(); // value we are searching for
		Node3 tmpNode = root; // Current node start at ROOT

		while (!tmpNode.isLeaf()) {
			// BRANCH LEFT/MID/RIGHT OR CHECK FOR MATCH

			// Check children for match-case (ie: tmpNode is PARENT)
			if (tmpNode.getLeftChild() == iChild || tmpNode.getRightChild() == iChild
					|| tmpNode.getMidChild() == iChild) {
				return tmpNode;
			}

			if (v <= tmpNode.getVal()) {// --- Branch left
				cmpBtwNodes++;
				if (tmpNode.getLeftChild().isLeaf()) {
					return tmpNode;
				} else {
					tmpNode = tmpNode.getLeftChild();
					continue;
				}
			}

			else if (tmpNode.isTwoNode()) { // ===2-Node
				cmpBtwNodes+=2;
				if (v > tmpNode.getVal()) {
					
					if (tmpNode.getRightChild().isLeaf()) {
						return tmpNode;
					} else {
						tmpNode = tmpNode.getRightChild();
						continue;
					}
				}
			}

			else if (tmpNode.isThreeNode()) { // ===3-Node
				cmpBtwNodes++;
				if (v <= tmpNode.getValB()) { // ---Branch Middle
					if (tmpNode.getMidChild().isLeaf()) {
						return tmpNode;
					} else {
						tmpNode = tmpNode.getMidChild();
						continue;
					}

				} else { // v >= VB
					if (tmpNode.getRightChild().isLeaf()) {
						return tmpNode;
					} else {
						tmpNode = tmpNode.getRightChild();
						continue;
					}
				}
			}
			else {
				System.out.println("GetParent, node not 3 nor 3 Node...");
				return null;
			}
		}
		return tmpNode;
	}
	
	//=================================SEARCH (FIND)
	
	public boolean search(int val) {
		findCount++;
		Node3 target = searchInsert(val);
		if(target != null){
			if(target.getVal() == val || (target.isThreeNode() && target.getValB() == val)){
				cmpBtwNodes+=2;
				return true;
			}
		}
		return false;
	}

	//==================================TRAVSERSE/PRINT

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
		System.out.print(root.getVal() + " ");

		// CHECK MID CHILD (if exists)
		if (root.getMidChild() != null) {
			pioUtil(root.getMidChild());
		}
		// Print ValueB (if exists)
		if (root.getB()) {
			System.out.print(root.getValB() + " ");
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
		System.out.print(root.getVal() + " ");
		// CHECK MID CHILD (if exists)
		if (rt.getMidChild() != null) {
			return pioUtil(rt.getMidChild());
		}
		// Print ValueB (if exists)
		if (rt.getB()) {
			System.out.print(root.getValB() + " ");
		}
		// CHECK RIGHT CHILD
		if (rt.getRightChild() != null) {
			return pioUtil(rt.getRightChild());
		}
		return null;
	}

	public void printNode(Node3 rt) {
		System.out.println("Node contains: {" + rt.getVal() + ", " + rt.getValB() + "}");

		if (rt.isLeaf())
			return;

		if (rt.getLeftChild() != null) {
			printNode(rt.getLeftChild());
		}

		if (rt.getMidChild() != null) {
			printNode(rt.getMidChild());
		}

		if (rt.getRightChild() != null) {
			printNode(rt.getRightChild());
		}
	}

	public void printPreOrder(int ops) {// INCOMPLETE
		System.out.print("\nPre-order traversal after step "+ops+": \n");
		if(root.isTwoNode()){
			System.out.print(root.getVal()+" ");
			if(!root.isLeaf()){
				ppoUtil(root.getLeftChild());
				ppoUtil(root.getRightChild());
			}
		} else if(root.isThreeNode()){
			System.out.print(root.getVal()+" "+root.getValB()+" ");
			if(!root.isLeaf()){
				ppoUtil(root.getLeftChild());
				ppoUtil(root.getMidChild());
				ppoUtil(root.getRightChild());
			}
		}else{
			System.out.println("\nprintPreOrder encountered an exception");
		}	
	}
	
	public void ppoUtil(Node3 rt){
		if(rt.isTwoNode()){
			System.out.print(rt.getVal()+" ");
			if(!rt.isLeaf()){
				ppoUtil(rt.getLeftChild());
				ppoUtil(rt.getRightChild());
			}
		} else if(rt.isThreeNode()){
			System.out.print(rt.getVal()+" "+rt.getValB()+" ");
			if(!rt.isLeaf()){
				ppoUtil(rt.getLeftChild());
				ppoUtil(rt.getMidChild());
				ppoUtil(rt.getRightChild());
			}
		}else{
			System.out.println("ppoUtil encountered an exception");
		}
		
	}

	// =======================================HELPERS
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
	public int getRemoveOps(){
		return this.removeOps;
	}
}
