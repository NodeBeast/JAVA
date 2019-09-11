import java.util.*;

public class MergeSort {
	// ===============================================HELPER METHODS
	public static int[] generate(int n) {
		int[] d = new int[n];
		Random r = new Random();
		for (int i = 0; i < n; i++)
			d[i] = r.nextInt(100);
		return d;
	}

	public static void show(int[] d) {
		System.out.print(" ");
		for (int i = 0; i < d.length; i++) {
			System.out.print(i + "  ");
		}
		System.out.println(" ");
		System.out.println(Arrays.toString(d));
	}

	public static void print(String s) {
		System.out.println(s);
	}

	public static void swap(int[] a, int ia, int[] b, int ib) {
		int tmp = a[ia];
		a[ia] = b[ib];
		b[ib] = tmp;
		return;
	}

	public static void swap(int[] a, int i1, int i2) {
		int tmp = a[i1];
		a[i1] = a[i2];
		a[i2] = tmp;
	}

	public static void compareAndPositionElements(int[] b, int low, int high, int[] d, int current) {
		print(current + "=current: Checking d[low]=" + d[low] + " VS d[high]=" + d[high]);
		if (d[low] <= d[high]) { // Place elements in without swapping
			b[current] = d[low];
			b[current + 1] = d[high];
			return;

		} else { // low > high --> Position element WITH swapping (values
					// inverted)
			b[current] = d[high];
			b[current + 1] = d[low];
			return;
		}
	}

	public static boolean cmp(int a, int b) {
		if (a < b)
			return true;
		else
			return false;
	}

	// ========================================SORTING METHODS
	// SMS: Create BUF[], call structure (return JL) & call SORT(d,jl)
	public static void structuredMergeSort(int[] d) {
		System.out.println("CALLED: StructuredMergeSort()");
		JumpList jl = structure(d);
		jl.showAll();
		
		sort(d, jl); // Calls Merge as needed
		print("Return From Sort()");
		show(d);
		jl.showAll();
		print("RETURN: StructuredMergeSort()");

	}

	public static void reverse(int[] d, int start, int length) {
		int end = start + length;
		int flips = (length + 1) / 2;
		for (int i = 0; i < flips; i++) {
			// System.out.println("Rev: Swapping " + (start + i) + " & " + (end
			// - i));
			swap(d, start + i, end - i);
		}
	}

	// STRUCTURING PASS (CALLS REV) & RETURNS LOADED JumpList
	public static JumpList structure(int[] data) { // Returns JL
		JumpList jl = new JumpList(data.length); // over-provisioned?
		int i = 0;
		int incr = 0; // Increasing run cnt
		int decr = 0; // Decreasing run cnt

		while (i < data.length - 1) { // Iterate through data[i]
			if (data[i] <= data[i + 1]) {// ===== INCREASING RUN
				incr++;
				if (incr == 1) {
					if (decr != 0) { // Ending a decr run - skip this one
						reverse(data, jl.getTailRun(), jl.getTailLength());
						i++;
						incr = 0;
						decr = 0;
						continue;
					}

					ARun ascRun = new ARun(i, 1); // create new ARun for insert
					jl.enqueue(ascRun); // Insert to tail of queue
				} else if (incr > 1) { // Continuing ascending run
					jl.incTailLength(jl.getTail()); // Increment run length
				} else {
					System.out.println("Struct: ERROR");
				}
				decr = 0;

			} else { // ====== DECREASING RUN
				if (incr != 0) {
					i++; // Ended incr run, skip ahead
					incr = 0;
					continue;
				}
				decr++;

				if (decr > 1) { // Continue desc run
					int tailPos = jl.getTail();
					jl.incTailLength(tailPos); // Incr run length
				} else if (decr == 1) {
					if (incr != 0) { // Ending a decr run - skip one
						i++;
						decr = 0;
						incr = 0;
						continue;
					}
					ARun descRun = new ARun(i, 1);
					jl.enqueue(descRun);
				} else {
					print("Structure: Error");
				}
				incr = 0;
			}
			i++;
		}
		if (decr != 0) {
			// just came off a decreasing run, reverse final run
			reverse(data, jl.getTailRun(), jl.getTailLength());
		}
		if (jl.getTailRun() + jl.getTailLength() < data.length - 1) { // Last
																		// element
																		// left
			if (incr == 0 || decr == 0) {
				// Starting new run length 0
				jl.enqueue(new ARun(data.length - 1, 0));
			} else {
				jl.incTailLength(jl.getTail());
			}
		}
		return jl;
	}

	// STRUCTURED VERSION: Call
	public static void sort(int[] d, JumpList jl) {
		// At Least 2 runs left
		// CALLS MERGE, MODIFIES JUMPLIST, CREATES BUFFER ARRAY
		int[] buf = new int[d.length];
		int head, startIndex, lsum, length1, length2;
		int i = 0;
		
		while (jl.getCount() > 1) { // More than one run left
			for(int l=0; l< d.length;l++){
				buf[l] = 0; // reset buffer
			}
			
			merge(d, buf, jl.dequeue(), jl.dequeue());
			// Enqueue merged ARun1 + ARun2
			if(jl.getCount() == 1){
				print("merge done, count = 1, EXITING");
				return;
			}
			//jl.showAll();
			head = jl.getHead();
			startIndex = jl.getRun(head);
			ARun mergedRun = new ARun(startIndex);
			
			jl.enqueue(mergedRun);
			if(jl.getCount() == 1){
				print("   ---COUNT == 1 ---");
				return;
			};
			
			length1 = jl.jlist[head].getLength();
			length2 = jl.jlist[head + 1].getLength();
			lsum= length1 + length2 + 1;
			

		}

		// Generate new merged ARun & Enqueue
		if (jl.getCount() == 1) {
			System.out.println("Sort: Count dropped to 1, appending last element");
			return;
		}
	}

	public static void mergeSort(int[] data) {// Prep buffer, call sort
		int[] buf = new int[data.length];
		sort(data, buf);
	}

	public static void sort(int[] data, int[] buf) { // UNSTRUCTURED VERSION

		// Make an instance of JumpList
		// Create ARun[] with all ones
		// Load ARuns into instance of JumpList
		// for each Arun : Call merge with data, buf, arun1, arun2

		int m = 0;
		int repeats = 1;
		int arrLength = data.length;
		int compares = (arrLength) / 2;
		int comparesRemaining = compares;
		System.out.println("Compares: " + compares + "  Array L=" + data.length);
		show(data);
		while (Math.pow(2, m) < data.length) { // for every merge number
												// (m=0,1,2,3,...)
			repeats = (int) Math.pow(2, m); // Number of consecutive lefts &
											// grouping=rpt
			int chunkLength = repeats;
			int skip = repeats * 2; // values to skip from every 1st left to the
									// next
			int left, right;
			int current_position = 0;
			print(" ");
			print("======> M=" + m + " Skip=" + skip + " Repeat=" + repeats);
			show(data);
			show(buf);
			for (int i = 0; i < compares; i++) { // for 'compares' times
				for (int j = 0; j < repeats; j++) { // 'repeats' number of
													// consecutive cmp's
					left = i * skip + j; // Left index to compares in data
					right = left + repeats;
					System.out.println("i=" + i + " j=" + j + " && Left=" + left + " Right=" + right);
					compareAndPositionElements(buf, left, right, data, current_position);
					show(buf);
					current_position = current_position + 2;
					comparesRemaining--;
				}
				if (chunkLength * compares < arrLength) { // Process EVEN
															// elements, Copy
															// over remaining
															// element if app
					print("Copy Remaining Element:" + data[arrLength - 1] + " at arrLength");
					buf[arrLength - 1] = data[arrLength - 1];
				}
				if (comparesRemaining <= 0) {
					System.out.println("Breaking: no more compares left");
					break;
				}
			}
			for (int k = 0; k < data.length; k++) {
				data[k] = buf[k]; // Duplicate data
				buf[k] = 0; // ******************clear buffer
			}
			comparesRemaining = compares; // Reset allowed compares
			m++;
		}
		System.out.print("Terminating Sort: ");
		show(data);
	}

	public static void merge(int[] data, int[] buf, ARun run1, ARun run2) { // shared
		int pos = 0; // Current Position of buf
		int r1 = run1.getRun(); // Starting index
		int rl1 = run1.getLength(); // length of ascend. run
		int runEnd1 = r1 + rl1;
		int r2 = run2.getRun();
		int rl2 = run2.getLength();
		int runEnd2 = r2 + rl2;
		int i = 0; // index counter r1
		int j = 0; // index counter r2
		int runEnd = r1 + rl1 + rl2 + 1;
		
		while ((i <= rl1) && j <= rl2) {
			int x1 = r1 + i;
			int x2 = r2 + j;
			if (data[r1 + i] <= data[r2 + j]) { // True: [1] < [2]
				// Assign run1 element to buf
				buf[pos] = data[r1 + i];
				pos++;
				i++;
			} else { // False: [1] > [2]
				// Assign [2] to buf
				buf[pos] = data[r2 + j];
				pos++;
				j++;
			}
		}
		
		while (i < rl1 + 1) { // Leftovers in Run1
			print("i="+i+" \n Leftovers in Run1: buf[" + pos + "]=" + buf[pos]+
					" = Data[" + (r1 + i) + "]=" + data[r1 + i]);
			buf[pos] = data[r1 + i];
			pos++;
			i++;
		}

		while (j < rl2) { // Leftovers in Run2
			print("Leftovers in Run2: Assigning: buf[" + pos + "]=" + buf[pos] + " = Data[" + (r1 + j) + "]="
					+ data[r1 + j]);
			buf[pos] = data[r2 + j];
			pos++;
			j++;
		}

		// Merge done, Updating Data[] from r1 to rl1
		for (int k = 0; k <= rl1+rl2+1; k++) {
			data[r1 + k] = buf[k]; // Copy over modified runs to arr[]
		} // NOTE: ASSUMES ADECENT RUNS*

	}
}
