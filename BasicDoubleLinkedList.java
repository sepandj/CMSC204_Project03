/**
 * @author Sepand Jahrominejad
 * 
 * CMSC 204
 * 10/16/2022
 */


import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;


/*
 * A generic class of doubly linked list
 * 
 * 
 */
public class BasicDoubleLinkedList<T> extends Object implements Iterable<T> {
	
	
	/*
	 * An inner class
	 */
	public class Node{
		T data;
		Node prev;
		Node next;
		
		/**
		 * 
		 * @param dataNode
		 */
		public Node(T dataNode){
			data = dataNode;
			
		}
	}
	
	
	
	/*
	 * An inner class
	 */
	public class DoubleLinkedListIterator implements ListIterator<T>{
		
		Node cursor;
		Node lastAcc;
		int index;
		
		
		/*
		 * Constructor
		 */
		public DoubleLinkedListIterator() {
			cursor = head;
			lastAcc = null;
			index = 0;
		}
		
		
		
		/**
		 * to check if there is an element after cursor
		 * @return boolean
		 */
		@Override
		public boolean hasNext() {
			return index+3 < size;
			
		}
		
		
		/**
		 * @return the next index
		 * @throws UnsupportedOperationException
		 */
		public int nextIndex() throws UnsupportedOperationException{
			return index;
		}
		
		
		/**
		 * to check if there is an element behind the cursor
		 * @return boolean
		 */
		public boolean hasPrevious() {
			return cursor != null;
		}
		
		
		/**
		 * @return the previous index
		 * @throws UnsupportedOperationException
		 */
		public int previousIndex() throws UnsupportedOperationException{
			return index-1;
		}
		
		
		/**
		 * goes to the next element
		 * @return the passed element
		 * @throws NoSuchElementException
		 */
		@Override
		public T next() {
			if(!hasNext())
				throw new NoSuchElementException();
			
			lastAcc = cursor;
			T result = cursor.data;
			if(cursor.next != null) cursor = cursor.next;
			else 
			index++;
			return result;
		}
		
		
		
		/**
		 * goes to previous element
		 * @return the passed element
		 * @throws NoSuchElementException
		 */
		@Override
		public T previous() {
			if(!hasPrevious())
				throw new NoSuchElementException();
			
			T result = cursor.data;
			cursor = cursor.prev;
			index--;
			lastAcc = cursor;
			return result;
		}
		
		
		
		/**
		 * removes the element after the cursor
		 * @throws UnsupportedOperationException
		 * 
		 */
		
		@Override
		public void remove() {
			if(lastAcc == tail)
				throw new UnsupportedOperationException();
			
			Node lastAccPrev = lastAcc.prev;
			Node lastAccNext = lastAcc.next;
			if(lastAccPrev != null)
				lastAccPrev.next = lastAccNext;
			if(lastAccNext != null)
				lastAccNext.prev = lastAccPrev;
			size--;
			
			if(cursor == lastAcc)
				cursor = lastAccNext;
			else
				index--;
			lastAcc = null;
		}
		
		
		
		/**
		 * sets the element at the cursor to the passed argument
		 * @param a generic type <T>
		 * @throws UnsupportedOperationException
		 */
		@Override
		public void set(T arg0) {
			if(lastAcc == null)
				throw new UnsupportedOperationException();
			
			lastAcc.data = arg0;
		}
		
		
		
		/**
		 * @param generic data type <T>
		 * @throws UnsupportedOperationException
		 */
		@Override
		public void add(T arg0) {
			
			if(cursor.prev == null)
				throw new UnsupportedOperationException();
			
			
			Node newNode = new Node(arg0);
			
			newNode.next = cursor.prev.next;
			cursor.prev.next = newNode;
			newNode.prev = cursor.prev;
			
			if(newNode.next != null)
				newNode.next.prev = newNode;
			
			size++;
			index++;
			lastAcc = null;
		}

	}
	
	
	
	Node head, tail;
	int size;
	
	
	/**
	 * constructor
	 */
	public BasicDoubleLinkedList(){
		head = null;
		tail = null;
		size = 0;
	}
	
	
	/**
	 * 
	 * @return returns size of the list
	 */
	public int getSize() {
		
		return size;
	}
	
	
	
	
	/**
	 * adds an element to the end of the list
	 * @param data
	 */
	public void addToEnd(T data) {
		Node newNode = new Node(data);
		
		if(head == null) {
			head = newNode;
			tail = newNode;
			size++;
		}
		else {
			Node lastNode = head;
			while(lastNode.next != null)
				lastNode = lastNode.next;
			
			lastNode.next = newNode;
			newNode.prev = lastNode;
			newNode.next = null;
			tail = newNode;
			size++;
		}
		
	}
	
	
	/**
	 * adds an element to the front of the list
	 * @param data
	 */
	public void addToFront(T data) {
		Node newNode = new Node(data);
		
		newNode.next = head;
		newNode.prev = null;
		
		if (head != null)
			head.prev = newNode;
		
		head = newNode;
		size++;
	}
	
	
	
	
	/**
	 * gets the fist element on the list
	 * @return the first element
	 */
	public T getFirst() {
		return head.data;
		
	}
	
	
	
	/**
	 * the last element on the list
	 * @return the last element
	 */
	public T getLast() {
		return tail.data;
	}
	
	
	
	/**
	 * creates an iterator object
	 * @return dll iterator
	 */
	@Override
	public ListIterator<T> iterator(){
		DoubleLinkedListIterator dllIterator = new DoubleLinkedListIterator();
		return dllIterator;
		
	}
	
	
	
	/**
	 * 
	 * @param targetData
	 * @param comparator
	 * @return
	 */
	public Node remove(T targetData, Comparator<T> comparator){
		if(head==null) return null;
		
		
		Node pointer = head;
		
		while(comparator.compare(pointer.data, targetData) != 0) {
			pointer = pointer.next;
			if(pointer==tail) {
				if(comparator.compare(tail.data, targetData) != 0)
					return null;
			}
		}
		
		if(pointer.next!=null && pointer.prev!=null) {
			pointer.next = pointer.next.prev;
			pointer.prev = pointer.prev.next;
			size--;
			return pointer;
			}
		if(pointer.next==null)
			tail = pointer.prev;
		if(pointer.prev==null)
			head = pointer.next;
		size--;
		
		return pointer;
	}
	
	
	
	/**
	 * 
	 * @return
	 */
	public T retrieveFirstElement() {
		T result = head.data;
		
		head = head.next;
		return result;
		
	}
	
	
	/**
	 * 
	 * @return
	 */
	public T retrieveLastElement() {
		T result = tail.data;
		
		tail = tail.prev;
		return result;
	}
	
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<T> toArrayList(){
		
		ArrayList<T> dllArrayList = new ArrayList<T>();
		Node aNode = head;
		while(aNode != null) {
			dllArrayList.add(aNode.data);
			aNode = aNode.next;
		}
		return dllArrayList;
	}

}
