

/**
 * Write a generic data type for a deque and a randomized queue. 
 * The goal of this assignment is to implement elementary data structures using arrays and linked lists, 
 * and to introduce you to generics and iterators.
 * Dequeue. A double-ended queue or deque (pronounced "deck") is a generalization of a stack and a queue that supports adding and 
 * removing items from either the front or the back of the data structure. 
 * Create a generic data type Deque that implements the following API:
 * @author user
 *
 * @param <Item>
 */
import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdOut;
public class Deque<Item> implements Iterable<Item> {
    private Node<Item> first;
	private Node<Item> last;
	private int count;
	private class Node<Item> {
		private Item item;
		private Node<Item> previous;
		private Node<Item> next;
		
		public Node(Item item) {
			this.item = item;
		}
		public Item getItem() {
			return item;
		}
		public void setItem(Item item) {
			this.item = item;
		}
		public Node<Item> getPrevious() {
			return previous;
		}
		public void setPrevious(Node<Item> previous) {
			this.previous = previous;
		}
		public Node<Item> getNext() {
			return next;
		}
		public void setNext(Node<Item> next) {
			this.next = next;
		}
	}
	
    // construct an empty deque
    public Deque() {
    	first = null;
    	last = null;
    	count = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
    	return first == null;
    }

    // return the number of items on the deque
    public int size() {
    	return count;
    }

    // add the item to the front
    public void addFirst(Item item) {
    	validateItem(item);
    	Node<Item> node = new Node<Item>(item);
    	if (count == 0) {
    		first = node;
    		last = node;
    	} else {
    		Node<Item> oldFirst = first;
    		oldFirst.setPrevious(node);
    		node.setNext(oldFirst);
    		first = node;
    	}
    	count++;
    }

    // add the item to the back
    public void addLast(Item item) {
    	validateItem(item);
    	Node<Item> node = new Node<Item>(item);
    	if (count == 0) {
    		first = node;
    		last = node;
    	} else {
    		last.setNext(node);
    		node.setPrevious(last);
    		last = node;
    	}
    	count++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
    	if (count == 0) {
    		throw new NoSuchElementException();
    	}
    	Node<Item> odlFirstNode = first;
    	first = odlFirstNode.getNext();
    	count--;
    	if (count == 0) {
    		last = null;
    	} else {
        	first.setPrevious(null);
    	}
    	return odlFirstNode.getItem();
    }

    // remove and return the item from the back
    public Item removeLast() {
    	if (count == 0) {
    		throw new NoSuchElementException();
    	}
    	Node<Item> oldLastNode = last;
    	Node<Item> secondLastNode = oldLastNode.getPrevious();
    	count--;
    	if (count == 0) {
    		first = null;
    		last = null;
    	} else {
        	secondLastNode.setNext(null);
        	last = secondLastNode;
    		oldLastNode.setPrevious(null);
    	}
    	return oldLastNode.getItem();
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
    	return new DequeIterator<Item>(first);
    }
    
	private class DequeIterator<Item> implements Iterator<Item> {
    	private Node<Item> current;
    	
    	public DequeIterator(Node<Item> first) {
    		current = first;
    	}
		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public Item next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			Item item = current.getItem();
			current = current.getNext();
			return item;
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}
   
    }
    
    private boolean validateItem(Item item) {
    	if (item == null) {
    		throw new IllegalArgumentException();
    	}
    	return true;
    }
    

    // unit testing (required)
    public static void main(String[] args) {
    	Deque<String> deque = new Deque<String>();
    	StdOut.println("size:" + deque.size());
    	deque.addFirst("I");
    	deque.removeLast(); 	
    	deque.addFirst("good");
    	deque.removeFirst();
    	deque.addLast("good");
    	deque.addLast("good");
    	deque.removeLast();
    	StdOut.println("is Empty: " + deque.isEmpty());
    	StdOut.println("size:" + deque.size());
    	Iterator<String> iterator = deque.iterator();
    	while (iterator.hasNext()) {
    		StdOut.println(iterator.next());
    	}
    }

}