public class NodeRB extends Node {
	// ==============================PROPERTIES
	// FROM Node superclass:
		// value (smaller)
		// valueB (bigger)
	NodeRB root;
	NodeRB leftChild;
	NodeRB rightChild;
	boolean red;

	// ==================================SETTERS & GETTERS

	// ==============================HELPER FUNCTIONS

	// ========================Boolean Checkers

	public boolean isRed() {
		return (this.red == true);
	}

	// OVERLOAD isLeaf()

}
