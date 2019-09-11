public class HuffLeaf {// LEAF CLASS
	char letter;
	int weight;

	
	public HuffLeaf(char c, int w) {
		this.letter = c;
		this.weight = w;
	}
	
	public boolean isLeaf(){
		return true;
	}
	
	// getters & isLeaf()
}