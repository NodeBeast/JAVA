public interface Dictionary<Key, E> {
/* Finding a record that matches a given key
using Java's 'Comparable' interface to build a generic Dictionary  
Keys must be comparable
*/
	public void clear();
	public void insert(Key k, E element);
	public int remove(Key k);
	public int removeAny();
	public int find(Key k);
	public int size();
}
