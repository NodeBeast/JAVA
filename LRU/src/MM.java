public class MM {
	//Main memory component
	//"memconfig.txt" --> 
	Page[] mm;
	long[] lastAccess; // contains last access of mm[i]
	
	public MM(int size) {
		mm = new Page[size];
		lastAccess = new long[size];//same size as mm 1:1
	}

	public void insertPage(Page p, int i) {
		this.mm[i] = p;
		lastAccess[i] = System.currentTimeMillis();
	}

	public int findOpenSpot() {
		// Returns first open spot in mm -1 if no spot
		for (int i = 0; i < mm.length; i++) {
			if (mm[i] != null) {
				if (mm[i].getId() == "0") {
					return i;
				}
			} else {
				return i;
			}
		}
		return -1;
	}
	
	public int findById(String id){
		// returns index in main memory
		for(int i=0; i<mm.length; i++){
			if(mm[i] != null){
				if(mm[i].getId() == id){
					//Update Access time
					lastAccess[i] = System.currentTimeMillis();
					return i;
				}
			}
		}
		return -1;
	}

	public Page remove(String varId) {
		int i = findById(varId);
		if(i > 0){
			//Save local values
			String tmpId = mm[i].getId();
			int tmpVal = mm[i].getValue();
			//Reset the memory location
			mm[i].reset();
			lastAccess[i] = 0;
			return new Page(tmpId, tmpVal);
		}
		return null;
	}
	public Page remove(int i) {
		//Save local values
		String tmpId = mm[i].getId();
		int tmpVal = mm[i].getValue();
		//Reset the memory location
		mm[i].reset();
		lastAccess[i] = 0;
		return new Page(tmpId, tmpVal);
	}
	public int LRU(){
		int min = 0;
		int lru;
		//Returns index of oldest page 
		for(int i=0; i<mm.length; i++){
			if(min > lastAccess[i]){
				min = lastAccess[i];
				lru = i;//oldest page
			}
		}
		return lru;
	}
	
	public int getValue(int index){
		return this.mm[index].getValue();
	}
	public String getId(int index){
		return this.mm[index].getId();
	}
	public int getSize(){
		return this.mm.length;
	}
}

