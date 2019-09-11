//Hash function should have equal probability to map to all addresses


import java.util.*;
import java.io.*;
public class Hash{
	//===MAIN METHOD
	public static void main(String[] args){
		String[] s = args;
		Hash h = new Hash(16);
		h.hashFunction2(s);
		h.printTable();
	}
	//===CLASS PROPERTIES	
	String[] tbl;
	int size;
	int itemsInTbl=0;
	//===CONSTRUCTOR
	
	Hash(int size){
		this.size = size;
		tbl = new String[size];
		Arrays.fill(tbl,"0");
	}
	
	//===HASHING FUNCTIONS
	public void hashFunction1(String[] input){
		//Simplest hashing function
		//value 1 maps to address 1, ..etc
		for(int i=0; i<input.length;i++){
			String sin = input[i];
			tbl[Integer.parseInt(sin)] = sin;
		}	
	}
	
	public void hashFunction2(String[] in){//Using % to limit max value magnitude
		
		for(int i=0;i<in.length;i++){
			String s = in[i];
			System.out.println("Insertion #"+(i+1)+": "+s);
			int index = Integer.parseInt(s) % size;
			
			//Collision 
			while(tbl[index] != "0"){//current index full
				System.out.print("Collision at "+index);
				index = (index*index)%size;
				System.out.print("  ===> Attempting "+index+"\n");
			}
			tbl[index] = s;
		}
	}
	public int nextIndex2(int index){//Quadratic probing
		while(tbl[index] != "0"){
				index = index*index;
				index %= size;
		}
		return index;
	}
	
	
	//Collision handler: probing function
		//Store a linked list from that point DONE
	
	//Function that mactches : hash code
	
	//compression
	//public int get(k){}
	//put(k,V) add v to key k 
	//remove(k) 
	//size() # elements in hash table
	//===HELPERS
	public void printTable(){
		for(int i=0;i<tblSize;i++){
			System.out.print(i+" ");
			if(i == i%10){
				System.out.print(" ");
			}
			if(i == i%100){
				System.out.print(" ");
			}
			if(i == i%1000){
				System.out.print(" ");
			}
		}
		
		System.out.print("\n");
		
		for(int n=0;n<tblSize;n++){
			System.out.print(tbl[n]);
			if(tbl[n] != tbl[n]%10){
				System.out.print(" ");
			}
			if(tbl[n] != tbl[n]%100){
				System.out.print(" ");
			}
			if(tbl[n] != tbl[n]%1000){
				System.out.print(" ");
			}
		}
		System.out.print("\n\n");
	}
	
}