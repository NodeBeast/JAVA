//Container for key-value pair
public class KVpair<Key, E> {
	private Key k;
	private E e;
	//CONSTRUCTORS
	KVpair(){ k=null; e=null; }
	KVpair(Key kval, E eval){ k=kval; e=eval; }
	//GETTERS
	public Key key() { return k; }
	public E value() { return e; }
}
