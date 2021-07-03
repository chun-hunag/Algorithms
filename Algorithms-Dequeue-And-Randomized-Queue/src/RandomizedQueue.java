import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Randomized queue. A randomized queue is similar to a stack or queue,
 * except that the item removed is chosen uniformly at random among items in the data structure. 
 * Create a generic data type RandomizedQueue that implements the following API:
 * @author user
 *
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
	private Object[] items;
	private int count;
	 // construct an empty randomized queue
    public RandomizedQueue() {
    	items = new Object[2];
    	count = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
    	return count == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
    	return count;
    }

    // add the item
    public void enqueue(Item item) {
    	validateItem(item);
    	if (isArrayMax()) {
    		resize(count * 2);
    	}
    	items[count++] = item;
    }

    // remove and return a random item
	public Item dequeue() {
    	if (count == 0) {
    		throw new NoSuchElementException();
    	}
    	int random = StdRandom.uniform(0, count);
    	Item item = (Item) items[random];
    	
    	if (random != count - 1) {
    		items[random] = items[count - 1];
    	}
    	items[count - 1] = null; // clear last element;
    	
    	count--;
    	if (count > 0 && count == items.length/4) {
    		resize(items.length/2);
    	}
    	return item;
    }

    // return a random item (but do not remove it)
	public Item sample() {
    	if (count == 0) {
    		throw new NoSuchElementException();
    	}
    	Item item = (Item) items[(int) (Math.random() * count)];
		return item;
    }
    
    private boolean validateItem(Item item) {
    	if (item == null) {
    		throw new IllegalArgumentException();
    	}
    	return true;
    }
    
    private boolean isArrayMax() {
    	return count == items.length;
    }
    
    private void resize(int size) {
    	Object[] tmpItems = new Object[size];
    	for (int i = 0; i < count; i++) {
    		tmpItems[i] = items[i];
    	}
    	items = tmpItems;
    }
    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
    	return new RandomizedQueueIterator<Item>();
    }
    
	private class RandomizedQueueIterator<Item> implements Iterator<Item> {
    	
    	private final Object[] iteratorItems;
    	private final int iteratorCount;
    	private int pointer;
    	
    	public RandomizedQueueIterator() {
    		iteratorItems = new Object[count];
    		iteratorCount = count;
    		pointer = 0;
    		for (int i = 0; i < iteratorCount; i++) {
    			iteratorItems[i] = items[i];
    		}
    		StdRandom.shuffle(iteratorItems);
    		
    	}
		@Override
		public boolean hasNext() {
			return iteratorCount != pointer;
		}

		@Override
		public Item next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			Item item = (Item) iteratorItems[pointer++];
			return item;
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}
    	
    }

    // unit testing (required)
    public static void main(String[] args) {
    	RandomizedQueue<String> randomQueue = new RandomizedQueue<String>();
    	randomQueue.enqueue("A");
    	randomQueue.enqueue("B");
    	randomQueue.enqueue("C");
    	randomQueue.enqueue("D");
    	randomQueue.enqueue("E");
    	randomQueue.enqueue("F");
    	randomQueue.enqueue("G");
    	StdOut.println("size: " + randomQueue.size());
    	StdOut.println("is Empty: " + randomQueue.isEmpty());
    	StdOut.println("deque test: " + randomQueue.dequeue());
    	Iterator<String> iter = randomQueue.iterator();
    	while (iter.hasNext()) {
    		StdOut.println("iter test: " + iter.next());
    	}

    }
}
