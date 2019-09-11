public class TreeDriver {
	public static void main(String[] args) {
		System.out.println("===STARTING TESTING===\n");
		
		RedBlackTree rbt = new RedBlackTree();
		int[] data = {22, 15, 45, 92, 55, 74};
		
		System.out.println("Root val: "+rbt.getRootVal());
		System.out.println("===Loading Values===");
		for(int i=0; i<data.length; i++){
			rbt.insert(data[i]);
		}
		rbt.printInOrder();
		System.out.println("\n===TREE Nodes Loaded===");
		
		System.out.println("BST Maximum height exceeded: "+rbt.isTooHigh());
		//System.out.println(rbt.depthOf(rbt.root.getRightChild()));		
		//System.out.println(rbt.getCount());
	}

}
