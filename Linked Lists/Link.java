public class Link<E> { // Node class

// =================================PROPERTIES
	public E element;
	public Link next;
// ================================CONSTRUCTOR
	public Link(E it, Link<E> nextval) {
		element = it; next = nextval; 
	}
	public Link(Link<E> nextval) {next = nextval; }
	// =================================HELPERS
	Link<E> next() { return next; }
	Link<E> setNext(Link<E> nextval){
		return (next = nextval);
	}
	E element(){ return element; }
	E setElement(E it){ return (element = it); }

	public void display() {
		System.out.println("Name is: " + name);
	}

	// =======================================MAIN
	public static void main(String[] args) {
		System.out.println("Creating New LinkedList toto");
		int x = 4;
		LinkedList toto = new LinkedList();
		toto.insertFirstLink("Hello", x);
		toto.insertFirstLink("Hi", '2');
		
		toto.display();
	}
}
