
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
public class TwoStackQueue<T> {
	private Stack<T> inStack;
	private Stack<T> outStack;
	private int count;
	public TwoStackQueue() {
		inStack = new Stack<T>();
		outStack = new Stack<T>();
		count = 0;
	}
	
	public boolean isEmpty() {
		return count == 0;
	}
	public void enqueue(T item) {
		inStack.push(item);
		count++;
	}
	
	public T dequeue() {
		if (count == 0) {
			throw new NoSuchElementException();
		}
		
		if (outStack.isEmpty()) {
			while (!inStack.isEmpty()) {
				T tmpItem = inStack.pop();
				outStack.push(tmpItem);
			}
		}
		
		count--;
		return outStack.pop();
	}
	
	public static void main(String args[]) {
		TwoStackQueue<Integer> queue = new TwoStackQueue<Integer>();
		for (int i = 0; i < 100; i++) {
			int random = (int) (Math.random() * 3);
			if (random == 0) {
				queue.enqueue(i);
				StdOut.println("queue enqueue: " + i);
			} else if (random == 1) {
				if (!queue.isEmpty()) {
					StdOut.println("queue dequeue: " + queue.dequeue());
				}
			} else {
				StdOut.println("queue is empty: " + queue.isEmpty());
			}
		}
	}
}
