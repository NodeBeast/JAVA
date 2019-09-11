public class Frame {
	String fid;
	boolean valid; // true when in mm
	public Frame() {
		this.fid = "0";
		this.valid = false;
	}
	public Frame(String frameId, boolean validBit) {
		this.fid = frameId;
		this.valid = validBit;
	}
	public void toMem(){
		this.valid = true;
	}
	public void toDisk(){
		this.valid = false;
	}
	public void setValid(boolean b){
		this.valid = b;
	}
}