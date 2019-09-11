class LinkedList {
	public Link firstLink;

	// ==========================================CONSTRUCTOR
	LinkedList() {
		firstLink = null; // first link always as a null;
	}

	// ==========================================HELPERS
	public boolean isEmpty() { // CHECK IF EMPTY
		return (firstLink == null);
	}

	public void insertFirstLink(String name, int count) {// INSERT
		Link newLink = new Link(name, count);
		newLink.next = firstLink;
		firstLink = newLink;
	}

	public Link removeLink() {
		Link linkRef = firstLink;
		if (!isEmpty()) {
			firstLink = firstLink.next;
		} else {
			System.out.println("List was empty!");
		}
		return linkRef;
	}

	public void display() { // Cycling through
		Link myLink = firstLink;
		while (myLink != null) {
			myLink.display();
			System.out.println("Next Link: " + myLink.next);
			myLink = myLink.next;
		}
	}

	public Link find(String name) { // Find a Link
		Link myLink = firstLink;
		if (!isEmpty()) {
			while (myLink.name != name) {
				if (myLink.next == null) {
					return null;
				} else {
					myLink = myLink.next;
				}
			}
		} else {
			System.out.println("List was empty!");
		}
		return myLink;
	}

}
