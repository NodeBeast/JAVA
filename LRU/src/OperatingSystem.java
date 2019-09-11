import java.util.Date;

public class OperatingSystem {

	public static void main(String[] args) {
		// Test Stubs
		int memSize = 4;
		int diskSize = 10;

		// Init hardware
		MM mem = new MM(memSize);
		Disk d = new Disk(diskSize);

		// New virtual manager with references to disk and main memory
		VMM vm = new VMM(mem, d);
		System.out.println(System.currentTimeMillis());
	}

	// ============================================
	static class VMM extends Thread {
		Frame[] pgTbl; // keeps track of virtual space
		MM mem;// local main memory
		Disk d;// local disk memory

		public VMM(MM m, Disk dsk) {
			this.mem = m;
			this.d = dsk;
			this.pgTbl = new Frame[mem.getSize() + d.getSize()];
		}

		public void run(){
			
		}
		
		// STORE===
		public synchronized void Store(String varId, int value) {
			// Stores id and value into first available spot

			// Parse mem for open spot in memory
			int index = mem.findOpenSpot();// -1 if none exist

			// Store to main memory
			if (index >= 0) {// Insert into memory at pos index
				mem.insertPage(new Page(varId, value), index);
				// Update Page Table
				pgTbl[index].setValid(true);
			}

			// Store to Disk
			if (index < 0) {// No open spot --> store to disk
				if (d.insertPage(new Page(varId, value))) {
					pgTbl[index].setValid(false);
					p("page inserted successfully in disk");
				} else {
					p("Error inserting page in disk");
				}
			}
		}

		// RELEASE===
		public synchronized void Release(String varId) {
			// Clears varId & value from main memory
			int index = mem.findById(varId);
			if (index == -1) {// Not in memory
				p("Release Error, varId not found in memory");
			} else {
				//Remove page from main memory
				Page temp = mem.remove(varId);
				//Insert page into disk
				d.insertPage(temp);
				pgTbl[index].setValid(false); // indicate in disk
			}
		}

		// LOOKUP===
		public synchronized int Lookup(String varId) {
			// Checks if in mm if so, return it's value else return -1
			int index = mem.findById(varId);
			if(index > 0){// Hit! page is in main memory
				return mem.getValue(index);
				
			} else if(index == -1){// Page Fault, not in mem
				if(d.inDisk(varId)){//...but exists in disk
					//Move from disk to main memory
					Page tmp = d.remove(varId);
					// Look for open spot in mm
					int openIndex = mem.findOpenSpot();
					if(openIndex >= 0){//Open spot exist
						mem.insertPage(tmp, openIndex);
						
					} else{//Memory is full, evict LRU
						int lru = mem.LRU();
						//move evicted memory page to disk
						d.insertPage(mem.remove(lru));
						
					}
				}
			}			
			return -1; // -1 if not in mm
		}

		public void p(String s) {
			System.out.println(s);
		}
	}

}
