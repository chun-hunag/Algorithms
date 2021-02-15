import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class MaxStack {
	private Stack<Integer> stack;
	private Stack<Integer> maxStack;
	private int count;
	
	public MaxStack() {
		stack = new Stack<Integer>();
		maxStack = new Stack<Integer>();
		count = 0;
	}
	
	public boolean isEmpty() {
		return count == 0;
	}
	public void push(Integer number) {
		validateItem(number);
		stack.push(number);
		if (count == 0) {
			maxStack.push(number);
		} else {
			if ((int) number >= (int) maxStack.peek()) {
				maxStack.push(number);
			} else {
				maxStack.push(maxStack.peek());
			}
		}
		count++;
	}
	
	public Integer pop() {
		if (count == 0) {
			throw new NoSuchElementException();
		}
		Integer value = stack.pop();
		maxStack.pop();
		count--;
		return value;
	}
	
	public Integer top() {
		return stack.peek();
	}
	
	public Integer peekMax() {
		return maxStack.peek();
	}
	
	public Integer popMax() {
		Integer value = maxStack.pop();
		Stack<Integer> tempStack = new Stack<Integer>();
		while (!stack.isEmpty()) {
			if (!value.equals(stack.peek())) {
				tempStack.push(stack.pop());
			}
		}
		count++;
		return value;
	}
	
	
	private boolean validateItem(Integer number) {
		if (number == null) {
			throw new IllegalArgumentException();
		}
		return true;
	}
	
	public static void main(String args[]) {
		MaxStack maxStack = new MaxStack();
		
		for (int i = 0; i < 100; i++) {
			int random = (int) (Math.random() * 3);
			if (random == 0) {
				maxStack.push(i);
				StdOut.println("maxStack push: " + i);
			} else if (random == 1) {
				if (!maxStack.isEmpty()) {
					StdOut.println("maxStack pop: " + maxStack.pop());
				}

			} else {
				if (!maxStack.isEmpty()) {
					StdOut.println("maxStack top: " + maxStack.top());
				}
			}
		}
	}
}
