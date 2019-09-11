public class Page {
	private String id;
	private int value;

	public Page() {
		this.id = "0";
		this.value = 0;
	}

	public Page(String s, int val) {
		this.id = s;
		this.value = val;
	}
	public String getId(){
		return this.id;
	}
	public int getValue(){
		return this.value;
	}
	public void set(String s, int val) {
		this.id = s;
		this.value = val;
	}
	public void reset(){
		this.id = "0";
		this.value = 0;
	}
}
