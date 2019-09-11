import java.util.ArrayList;

/*Generic key/element finding using Key value pairs
 * O(1) insert time or O(n) if using sorted list
 * KVpairs allow different record types to be comapred
 * O(n) for remove in either case
 * Comparing strings - want to use alphabetized > or <
 * Can overload operators via 'Comparable'
 * 
 *  */
public class UALdict<Key, E> implements Dictionary<Key, E> {
	public static int defaultSize;
	public AList<KVpair<Key,E>> list; //Store dict.
	//CONSTRUCTORS
	public UALdict(int sz) { 
		list = new AList<KVpair<Key,E>>(sz);	
	}
	public void clear(){list.clear(); }
	public void insert(Key k, E element){
		KVpair<Key,E> tmp = new KVpair<Key,E>(k,e);
		list.append(tmp);
	}
	public E remove(Key k){
		E tmp = find(k);
		if(tmp != null)
			list.remove(tmp);
		return tmp;
	}
	public E removeAny(){
		if(size() != 0){
			list.moveToEnd();
			list.prev();
			KVpair<Key,E> e = list.remove();
			return e.value();
		} else return null;
	}
	public E find(Key k){ 
		for(list.moveToStart(); list.currPos() < list.length()); list.next()){
			KVpair<Key,E> tmp = list.getValue();
			if(k == tm.key()) return tmp.value();
		}
		return null;// K does not exist in dict.
		
	}
	public int size(){ return list.size(); }
}
