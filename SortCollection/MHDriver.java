
public class MHDriver {
	public static void main(String[] args) {
		//INIT
		int[] data = {2,4,6,8,10,1,3};
		MaxHeap mh = new MaxHeap(data, data.length, 10);
		mh.insert(2);
		mh.insert(10);
		//TESTING
		for(int i=0; i<data.length;i++) {
			System.out.println("Element heap["+i+"] = "+mh.heap[i]+" isLeaf? : "+mh.isLeaf(i));
		}
		//ANALYSIS
		System.out.println("\n===ANALYSIS===");
		System.out.println("Total sifts: "+mh.sifts);
		System.out.println("Total swaps: "+mh.swaps);
	}
		
		
	
}
