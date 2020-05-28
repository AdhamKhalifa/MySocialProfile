

/**
 *  @author Adham Khalifa
 *			Alejandro Garibo
 * 			Gabe Crane

 Implementation of Singly linked list 
 * - Inserting a element in the list - This can be at beginning, at end or at a given position. 
 * - Search an element by value.
 * - Check the size of the list.
 * - Check if list is empty.
 * - Delete an element from the list - This can again be at beginning, at end or at given position.
 * - Converting a Array from linked list.
 * - Traversing through linked list.
 * - Search an element by index.
 * 
 * @param <E>
 */
 public class SinglyLinkedList<E> {

 	/* Head is needed to keep track of first SNode */
 	private SNode<E> head;

	/* Size to keep track of number of elements in list. 
	 * This should be increased by 1 when a element is added
	 * and should be reduced by 1 when a element is deleted */
	private int size = 0;

	/**
	 * Inserts a element into a linked list at head position.
	 * This does not require traversal through entire list.
	 * 
	 * @param value
	 */
	public void insertAtHead(E value) {
		SNode<E> newSNode = new SNode<E>(value); 
		newSNode.next = head; 
		head = newSNode;
		size++;
	}

	/**
	 * Inserts a element into a linked list at tail position.
	 * This needs traversal through entire list before insertion happens.
	 * 
	 * <br> Complexity :
	 * Since, traversal through entire list is involved here before
	 * new SNode gets inserted, and let's assume list has n elements, 
	 * so insertion at tail will take O(n) time
	 * </br>
	 * 
	 * @param value
	 */
	public void insertAtTail(E value) {
		SNode<E> newSNode = new SNode<E>(value);
		newSNode.next = null; 
		/* Since this insertion is at tail, this new SNode will point to null */
		if (null == head) { 
			/* When list is empty */
			head = newSNode;
		} else {
			SNode<E> tempSNode = head;
			while (null != tempSNode.next) {
				tempSNode = tempSNode.next;
			}
			tempSNode.next = newSNode;
		}
		size++;
	}

	/**
	 * Inserts a element into a linked list at a given position.
	 * 
	 * 
	 * @param value
	 * @param position
	 */
	public void insertAtPosition(E value, int position) {
		if (position < 0 || position > size) {
			throw new IllegalArgumentException("Position is Invalid");
		} 
		/* Conditions check passed, let's insert the SNode */
		SNode<E> newSNode = new SNode<E>(value);
		if (position == 0) {
			newSNode.next = head;
		} else {
			SNode<E> tempSNode = head;
			for (int i = 0; i < position - 1; i++) {
				tempSNode = tempSNode.next;
			}
			SNode<E> SNodeNextToNewSNode = tempSNode.next;
			tempSNode.next = newSNode;
			newSNode.next = SNodeNextToNewSNode;
		}
		size++;
	}
	
	

	/**
	 * Traverse through the linked list and print the items
	 */
	public void traverse() {
		SNode<E> temp = head;
		while (temp != null) {
			System.out.println(temp.item);
			temp = temp.next;
		}
	}

	/**
	 * Returns size of the linked list
	 * 
	 * @return {@link int}
	 */
	public int size() {
		return size;
	}

	/**
	 * Returns true is list is empty
	 * 
	 * @return {@link boolean}
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Returns the SNode containing data item after searching
	 * for a given index. If invalid index is passed, proper
	 * exception is thrown. 
	 *  
	 * @param index
	 * @return {@link SNode<E>}
	 */
	public SNode<E> searchByIndex(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Invalid index passed while searching for a value");
		} 
		/* Validation passed, let's search for value using the index */
		SNode<E> temp = head;
		for (int i = 0; i < index; i++) { 
			/* Start from 0 and go till one less then index 
			 * because we are jumping to next SNode inside the loop */
			temp = temp.next;
		}
		return temp;
	}

	/**
	 * Returns the SNode containing data item after searching 
	 * for a given value. If there are multiple same values
	 * in linked list, first one will be returned.
	 * 
	 * @param value
	 * @return {@link SNode<E>}
	 */
	public SNode<E> searchByValue(E value) { 
		/* Traverse through each SNode until this value is found */
		SNode<E> temp = head;
		while (temp != null && temp.next != null && temp.item != value) {
			temp = temp.next;
		}
		if (temp!= null && temp.item == value) {
			return temp;
		}
		return null;
	}

	/**
	 * Delete's the element present at head SNode 
	 */
	public void deleteFromHead() {
		/* If list is empty, return */
		if (null == head) { 
			return;
		}
		/* Update head and reduce size */
		head = head.next;
		size--;
	}

	/**
	 * Delete's the element present at tail SNode
	 */
	public void deleteFromTail() {
		/* If head is null, nothing to delete */
		if (null == head) {
			return;
		}
		/* Keep a pointer on head SNode and next SNode.
		 * Keep going until next becomes null */
		SNode<E> currentSNode = head;
		SNode<E> nextSNode = currentSNode.next;
		while (currentSNode.next != null && nextSNode.next != null) {
			currentSNode = currentSNode.next;
			nextSNode = nextSNode.next;
		}
		/* Now we are removing from tail, so tail - 1 SNode will point to null */
		currentSNode.next = null;
		/* Reduce the size */
		size--;
	}

	/**
	 * return index of node whose value is value
	 * 
	 * @param value
	 */
	
	public int getIndexOf(E value) {
		int idx = 0;
		SNode curr = head;
		if(curr == null)
			return -1;
		
		while(curr != null && !curr.item.equals(value)) {
			curr = curr.next;
			idx++;
		}
		
		return curr == null ? - 1 : idx;
	}
	public void deleteFromPosition(int position) {
		if (position < 0 || position >= size) {
			throw new IllegalArgumentException("Position is Invalid");
		} 
		/* Conditions check passed, let's delete the SNode */
		SNode<E> SNodeToBeDeleted = head;
		for (int i = 0; i < position; i++) {
			SNodeToBeDeleted = SNodeToBeDeleted.next;
		}
		if (SNodeToBeDeleted.next == null) { 
			/* If this is a last SNode */
			deleteFromTail();
		} else {
			SNodeToBeDeleted.item = SNodeToBeDeleted.next.item;
			SNodeToBeDeleted.next = SNodeToBeDeleted.next.next;
		}
	}

	/**
	 * Returns a array containing each element 
	 * from the list from start to end
	 * 
	 * @return {@link Object[]}
	 */
	public Object[] toArray() {
		/* Create an array of object of same size */
		Object[] result = new Object[size];
		int i = 0;
		for (SNode<E> x = head; x != null; x = x.next) {
			/* Take each SNode and add it to the array
			 * at appropriate position*/
			result[i++] = x.item;
		}
		return result;
	}

	/**
	 * SNode class of a linked list
	 * This is needed since entire linked list is a collection 
	 * of SNodes connected to each other through links
	 * @author Adham Khalifa
	 *
	 * @param <T>
	 */
	public class SNode<T> {

		/* Data item in the SNode */
		T item;

		/* Pointer to next SNode */
		SNode<T> next;

		/* Constructor to create a SNode */
		public SNode(T item) {
			this.item = item;
		}

		/* toString implementation to print just the data */
		@Override
		public String toString() {
			return item.toString();
		}

	}

}