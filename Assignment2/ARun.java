public class ARun {
	// PROPERTIES
	public int run; // starting index
	public int runLength;

	// CONSTRUCTOR
	public ARun() { // Default constructor
		this.run = 0;
		this.runLength = 1;
	}

	public ARun(int ri) { // Initialize run index with length 1
		this.run = ri;
		this.runLength = 1;
	}

	public ARun(int r, int rLength) { // Init index & length
		this.run = r;
		this.runLength = rLength;
	}

	// HELPER METHODS
	public int getRun() {
		return this.run;
	}

	public int getLength() {
		return this.runLength;
	}

	public void incLength(){
		this.runLength = this.runLength + 1;
	}

	public void setLength(int rlength) {
		this.runLength = rlength;
	}
	public void showRun(){
		System.out.println("ARun show: Index="+this.run+" with length="+this.runLength);
	}
}
