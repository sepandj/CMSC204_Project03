import java.util.Comparator;
import java.util.ListIterator;

public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T>{
	
	Comparator<T> comparator;
	
	/**
	 * constructor
	 * @param comparableObject
	 */
	public SortedDoubleLinkedList(Comparator<T> comparableObject) {
		head = null;
		tail = null;
		comparator = comparableObject;
	}
	
	
	
	/**
	 * adds data to the list in a sorted manner
	 * @param data
	 */
	public void add(T data) {
		Node newNode = new Node(data);
		Node pointer = head;
		if(head ==null) {
			head = newNode;
			tail = newNode;
			size++;
		}
		else if(comparator.compare(head.data, newNode.data) > 0) {
			head.prev = newNode;
			newNode.next = head;
			head = newNode;
			size++;
		}
		else {
			while(pointer.next != null && comparator.compare(pointer.data, newNode.data) <= 0)
				pointer = pointer.next;
			if(pointer.next != null) {
				newNode = pointer.prev;
				newNode.next = pointer;
			}
			else {
				pointer.next = newNode;
				newNode.prev = pointer;
				tail = newNode;
			}
			size++;
		}
		
		
			
	}
	
	
	
	/**
	 * does not support this method
	 * @throws UnsupportedOperationException
	 */
	@Override
	public void addToEnd(T data) {
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}
	
	
	
	/**
	 * does not support this method
	 * @throws UnsupportedOperationException
	 */
	@Override
	public void addToFront(T data) {
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}
	
	
	
	/**
	 * creates an iterator object by calling the super class
	 * @return iterator object
	 */
	@Override
	public ListIterator<T> iterator(){
		
		return super.iterator();
	}
	
	
	
	/**
	 * removes a node
	 * @return either null or the removed node
	 */
	@Override
	public Node remove(T data, Comparator<T> comparator) {
		return super.remove(data, comparator);
		
	}

}
