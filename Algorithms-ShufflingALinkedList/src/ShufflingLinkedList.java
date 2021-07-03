import java.util.LinkedList;
import java.util.Random;

/**
 * Given a singly-linked list containing $n$ items, rearrange the items uniformly at random. 
 * Your algorithm should consume a logarithmic (or constant) amount of extra memory and run in time proportional to $n$ to $nlogn$ in the worst case.
 * @author ChunHuang
 *
 */
public class ShufflingLinkedList<T extends Comparable<T>> {
	private class Node {
		Node next;
		Comparable value;
		public Node(Comparable value, Node next) {
			this.next = next;
			this.value = value;
		}
	}
	
	private Node root;
	private int count;
	
	public ShufflingLinkedList() {
		root = null;
		count = 0;
	}
	
	public void add(T t) {
		if (root == null) {
			root = new Node(t, null);
			count++;
			return;
		}
		
		Node node = new Node(t, null);
		Node tmpNode = root;
		while (tmpNode.next != null) {
			tmpNode = tmpNode.next;
		}
		tmpNode.next = node;
		count++;
	}
	
	public void insert(T t, int index) {
		if (index >= count) {
			throw new IllegalArgumentException();
		}
		Node tmpNode = root;
		for (int i = 0; i < index; i++) {
			tmpNode = tmpNode.next;
		}
		
		Node next = tmpNode.next;
		tmpNode.next = new Node(t, next);
	}
	
	public void remove(int index) {
		if (index >= count) {
			throw new IllegalArgumentException();
		}
		
		Node tmpNode = root;
		for (int i = 0; i < index - 1; i++) {
			tmpNode = tmpNode.next;
		}
		
		Node target = tmpNode.next;
		tmpNode.next = target.next;
	}
	/**
	 *  sort linkedlist
	 */
	public void sort() {
		root = sort(root);
	}
	/**
	 *  divide linkedlist to center
	 */
	private Node sort(Node head) {
		if (head.next == null) {
			return head;
		}
		
		Node slow = head;
		Node fast = head;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		Node left = head;
		Node right = slow.next;
		slow.next = null; // make linklist to two part
		
		left = sort(left);
		right = sort(right);
		return merge(left, right);
	}
	
	/**
	 * Merge two linked node to one, and sort them
	 * @param left
	 * @param right
	 * @return
	 */
	private Node merge(Node left, Node right) {
		Node head = new Node(null, null);
		Node tail = head;
		// linked two linked node, until left or right is sorted.
		while  (left != null && right != null) {
			if (less(left, right)) {
				tail.next = left;
				left = left.next;
			} else {
				tail.next = right;
				right = right.next;
			}
			tail = tail.next;
		}
		// if left linked node not sorted yet
		while (left != null) {
			tail.next = left;
			left = left.next;
			tail = tail.next;
		}
		// if right linked node not sorted yet
		while (right != null) {
			tail.next = right;
			right = right.next;
			tail = tail.next;
		}
		
		head = head.next;
		return head;
	}
	
	/**
	 * Shuffle linkedlist
	 */
	public void shuffle() {
		root = shuffle(root);
	}
	
	/**
	 * recursive call shuffle. Divde LinkedList to two parts and shuffle then merge them.
	 * @param head
	 * @return
	 */
	private Node shuffle(Node head) {
		if (head.next == null) {
			return head;
		}
		
		Node slow = head;
		Node fast = head;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		Node left = head;
		Node right = slow.next;
		slow.next = null; // make linklist to two part
		
		left = shuffle(left);
		right = shuffle(right);
		return shuffleMerge(left, right);
	}
	/**
	 * ÀH¾÷¿ï¾Ü¥ª¥kNode¦ê³s
	 * @param left
	 * @param right
	 * @return Node
	 */
	private Node shuffleMerge(Node left, Node right) {
		Node head = new Node(null, null);
		Node tail = head;
		Random random = new Random();
		while  (left != null && right != null) {
			if (random.nextInt(2) == 1) { // 1/2 posibility
				tail.next = right;
				right = right.next;
			} else {
				tail.next = left;
				left = left.next;
			}
			tail = tail.next;
		}
		
		while (left != null) {
			tail.next = left;
			left = left.next;
			tail = tail.next;
		}
		
		while (right != null) {
			tail.next = right;
			right = right.next;
			tail = tail.next;
		}
			
			
		head = head.next;
		return head;
	}
	
	private boolean less(Node a, Node b) {
		return a.value.compareTo(b.value) < 0;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		Node tmp = root;
		int index = 0;
		while (tmp.next != null) {
			builder.append(tmp.value);
			tmp = tmp.next;
			index++;
			if (index != count - 1) {
				builder.append(',');
			}
		}
		return builder.toString();
	}
	
	public static void main(String args[]) {
		ShufflingLinkedList<Integer> list = new ShufflingLinkedList<Integer>();
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			list.add(random.nextInt(100));
		}
		System.out.println("Origin:");
		System.out.println(list.toString());
		list.sort();
		System.out.println("Sorted:");
		System.out.println(list.toString());
		list.shuffle();
		System.out.println("Suffle:");
		System.out.println(list.toString());
	}
}
