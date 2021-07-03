import java.util.Arrays;
import java.util.Random;

public class InOrderTraversal<Key extends Comparable<Key>, Value> {
	
	private class Node {
		
		private Key key;
		private Value val;
		private Node left;
		private Node right;
		private int childCount;
		
		public Node(Key key, Value val, Node left, Node right, int childCount) {
			this.key = key;
			this.val = val;
			this.left = left;
			this.right = right;
			this.childCount = childCount;
		}

		public Key getKey() {
			return key;
		}

		public void setKey(Key key) {
			this.key = key;
		}

		public Value getVal() {
			return val;
		}

		public void setVal(Value val) {
			this.val = val;
		}

		public Node getLeft() {
			return left;
		}

		public void setLeft(Node left) {
			this.left = left;
		}

		public Node getRight() {
			return right;
		}

		public void setRight(Node right) {
			this.right = right;
		}
		
		public int getChildCount() {
			return childCount;
		}

		public void setChildCount(int childCount) {
			this.childCount = childCount;
		}
		
	}
	
	private Node root;
	
	public InOrderTraversal() {
		root = null;
	}
	
	public int size() {
		return size(root);
	}
	
	private int size(Node node) {
		if (node == null) {
			return 0;
		}
		
		return node.childCount;
	}
	
	public boolean isEmpty() {
		if (root == null) {
			return true;
		}
		return root.childCount == 0;
	}
	
	public void put(Key k, Value val) {
		root = put(root, k, val);
	}
	
	private Node put(Node node, Key k, Value val) {
		if (node == null) {
			return new Node(k, val, null, null, 1);
		}
		
		int compared = k.compareTo(node.getKey());
		if (compared < 0) { // left node;
			node.left = put(node.left, k, val);
		} else if (compared > 0){ // right node
			node.right = put(node.right, k, val);
		} else { // same key
			node.val = val;
		}
		// update childCount
		node.childCount = size(node.left) + size(node.right) + 1;
		return node;
	}
	
	public Key min() {
		return min(root);
	}
	
	private Key min(Node node) {
		if (node == null) {
			return null;
		}
		
		if (node.left == null) {
			return node.key;
		} else {
			return min(node.left);
		}
	}
	
	
	
	public Key[] traversal() {
		Key[] keys = (Key[]) new Comparable[size()];
		traversal(root, keys, 0);
		return keys;
	}
	
	private int traversal(Node node, Key[] keys, int index) {
		if (node == null) {
			return index;
		}
		
		if (node.left != null) {
			index = traversal(node.left, keys, index);
		}

		keys[index++] = node.key;
		
		if(node.right != null) {
			index = traversal(node.right, keys, index);
		}
		
		return index;
	}
	
	public static void main(String args[]) {
		Random random = new Random();
		InOrderTraversal<Integer, Integer> traversal = new InOrderTraversal<Integer, Integer>();
		for (int i = 0; i < 100; i++) {
			traversal.put(random.nextInt(100), random.nextInt(100));
		}
		Comparable[] keys = traversal.traversal();
		
		System.out.println(Arrays.toString(keys));
		
	}
}
