/* File-Compression Search Trie 
-Use variable-length code to represent data based on frequency of use
relative-frequency <-> weight
-Assign codes to characters based on relative frequency (weight)
-Leaves have Weighted-path-length = weight x depth
-GOAL: minimal external path weight for sum of leaves
high weight <-> low depth
*/
public class HuffTree{	
/*
Build n initial huffTrees (single node)
Put n trees in incr priority queue organized by weight 
Take first 2 trees with lowest weights and merge them
New tree has root with two trees as children
weight of new tree is the sum of the two trees 
return new tree to priority queue 
repeat until only one tree remains	
*/	
	public final int alphabet = 26;
	public HuffNode root;
	public HuffNode[] huffList;
	
	public HuffTree(){
		this.root = new HuffNode();
		huffList = new HuffNode[alphabet];//Queue
	}
	
	public HuffNode buildTree(){
		//Dequeue and merge lowest weight trees
		
	}
	
	public getRoot(){
		return this.root;
	}
	public int getWeight(){
		return root.getWeight();
	}
	public int compareTo(HuffTree ht){
		if(root.getWeight() < ht.getWeight()){
			return -1;
		} else if(root.getWeight() == ht.getWeight()){
			return 0;
		} else{
			return 1;
		}
	}
	public void enqueue(){
		//Add tree to priority queue
	}
	public void dequeue(){}	
		//Remove next element in queue and return it
	}



	
}