// ===DISK
public class Disk {
	Page[] dm;

	public Disk(int size) {
		this.dm = new Page[size];//
		for (int i = 0; i < size; i++) {
			dm[i] = new Page();// initially
		}
	}

	public boolean inDisk(String id) {
		// check if page exists in disk
		for (int i = 0; i < dm.length; i++) {
			if (dm[i].getId() == id)
				return true;
		}
		return false;
	}

	public Page remove(String id){
		int tmpVal = 0;
		for (int i = 0; i < dm.length; i++) {
			if (dm[i].getId() == id){
				tmpVal = dm[i].getValue();
				dm[i].reset();//clear disk space
				break;
			}
		}
		if(tmpVal == 0) System.out.println("Disk remove error, value=0");
		return new Page(id, tmpVal);
	}
	
	public boolean insertPage(Page p) {
		String pid = p.getId();
		int pval = p.getValue();

		for (int i = 0; i < dm.length; i++) {
			if (dm[i] == null) {// Open spot, insert
				dm[i] = new Page(pid, pval);
				return true;
			} else if (dm[i].getId() == "0") {// Open Spot, insert
				dm[i] = new Page(pid, pval);
				return true;
			}
		}
		return false;
	}
	
	public int getSize(){
		return this.dm.length;
	}
}