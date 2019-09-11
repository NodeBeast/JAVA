import java.util.*;
import java.io.*;

public class Sort {

	public static void appendTo(String ss, String fname) {
		String fx = fname + ".txt";
		try {
			File f = new File("C:\\Users\\Marc-Andre\\Desktop\\Java\\Sorting Algorithms and Testing\\" + fx);
			write(ss, f);
		} catch (IOException e) {
			System.out.println("AppendTo Exception: Warning, Will Robinson!");
			e.printStackTrace();
		}
	}

	public static void write(String s, File f) throws IOException {
		FileWriter fw = new FileWriter(f, true);
		fw.write(s);
		fw.close();
	}

	public static int[] structure(int[] data) { // Structuring Run -
		int fstreak = 0;
		int rstreak = 0;
		
		int[] d = new int[data.length];
		for(int i=0;i<d.length;i++) d[i] = data[i];
		
		for (int i = 1; i < d.length + 1; i++) {
			if (rstreak > 1) {// reverse elements
				rstreak = 0;
				fstreak = 0;
			} else if (fstreak > 1 & d[i] < d[i - 1]) {
				//showSeg(d, i - fstreak - 1, i - 1);
				fstreak = 0;
				rstreak = 1;
			} else if (d[i] > d[i - 1]) {// Increasing Run - Skip ahead
				fstreak++;
				rstreak = 0;
			} else {
				fstreak = 0;
				while (d[i] < d[i - 1] & i < d.length - 1) {
					i++;
					rstreak++;
				} // When while loop exits, direction just changed
				int[] rev = reverseArray(d, (i - rstreak), i);
				showMe(rev);
			}
		} // Structuring runs complete
		return d;
	}

	public static int[] iSort(int[] data) { // Insertion Sort Algorithm
		int comparisons = 0;
		for (int k = 1; k < data.length; k++) {
			if (data[k] > data[k - 1]) {
				comparisons++;
				continue;
			}
			int j = 1;
			int cursor = data[k];
			while ((k - j) > (-1) & (cursor < data[k - j])) { //SHIFT & INSERT
				comparisons++;
				data[k - j + 1] = data[k - j];
				if (k - j == 0) {
					j++;
					comparisons++;
					break;
				}
				j++;
			}
			data[k - j + 1] = cursor; // INSERT temp value
		}
		System.out.println("iSort: N="+ data.length + " CMP=" + comparisons);
		return data;
	}

	public static void showMe(int[] a) {
		System.out.println(Arrays.toString(a));
		return;
	}

	public static void showSeg(int[] d, int a, int b) { // Display seg
		int diff = (b - a + 1);
		int[] displ = new int[diff];
		for (int i = 0; i < diff; i++) {
			displ[i] = d[i + a];
		}
		System.out.println("Run Segment: " + Arrays.toString(displ) + "  with start index " + a + " to " + b);
	}

	public static int[] reverseArray(int[] d, int a, int b) {
		int tmp;
		int flips = (b - a + 1) / 2;
		for (int i = 0; i < flips; i++) { // Reversal of values
			tmp = d[a + i]; // Save value at i
			d[a + i] = d[b - i]; // Copy end to i
			d[b - i] = tmp;
		}
		return d;
	}

	public static int[] generate(int N) { // Random number array gen
		Random ran = new Random();
		int[] array = new int[N];
		for (int i = 0; i < N; i++) {
			array[i] = ran.nextInt(10000);
		}
		return array;
	}
	public static void swap(int[] array, int a, int b){
		int tmp = a;
		array[a] = array[b];
		array[b] = tmp;
		return;
	}

 	public static long getTime() {
		long t = System.nanoTime();
		return t;
	}
//==================================
	
	public static void mergeSort(int[] data){
		int[] buff = new int[data.length];
		mSort(data, buff, 0, data.length-1);
		System.out.println("Reassigning values to data: mergeSort complete:");
	}
	public static void mSort(int[] data, int[] buff, int left, int rightExt){	//Recursive
		if(rightExt-left < 2){
			//System.out.println("MergeSort Return: Left>Right: ");
			return; //Do nothing - single element
		}
		int mid = (left + rightExt) / 2;
		mSort(data, buff, left, mid);
		mSort(data, buff, mid+1, rightExt);
		mergeArrays(data, buff,left,rightExt);
	}
	public static void mergeArrays(int[] data, int[] buff, int left, int rightExt){
		System.out.println("mergeArrays called: L="+left+" & R-end="+rightExt);
		int mid = (left+rightExt)/2;
		int leftExt = mid;
		int right = mid+1; 
		int L = left; //left array index counter
		int R = right;//right array index counter
		int i=0; 	//buffer[] counter
		
		while((L <= leftExt) && (R <= rightExt)){ //WHILE within bounds
			System.out.println("mergeArrays: While i="+i+"  L="+L+"  R="+R);
			showMe(buff);
			if(data[L] < data[R]){//Left < Right - do nothing
				buff[i]=data[L];
				i++;
				L++;				
			} else {//Left > Right -  
				buff[i] = data[R];
				i++;
				R++;
			}
		}
	}
	// ================MAIN METHOD====================
	public static void main(String[] args) {
		int[] data = new int[args.length];
		for (int i = 0; i < args.length; i++) data[i]=Integer.parseInt(args[i]);
		
		//int[] data = new int[1000];
		//data = generate(5);
		//System.out.println("Random Data Array: ");
		
		System.out.println("---Initialize Main---");
		System.out.println("N="+data.length+" elements in input:");
		showMe(data);
		System.out.println("---Setup Done---");
		showMe(data);
		mergeSort(data);


	}
}