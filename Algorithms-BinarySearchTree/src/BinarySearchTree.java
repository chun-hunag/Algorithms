
public class BinarySearchTree<Key extends Comparable<Key>, Value> {
	private class Node {
		private Key key;
		private Value value;
		private Node left, right;
		private int count; // children tree count
		
		public Node(Key key, Value value) {
			this.key = key;
			this.value = value;
		}
	}
	private Node root;
	
	public BinarySearchTree() {
		root = null;
	}
	
	public int size() {
		return size(root);
	}
	
	private int size(Node node) {
		if (node == null) {
			return 0;
		}
		return node.count;
	}
	
	public void put(Key key, Value value) {
		if (key == null || value == null) {
			throw new IllegalArgumentException();
		}
		
		root = put(key, value, root);
	}
	
	private Node put(Key key, Value value, Node node) {
		if (node == null) {
			return new Node(key, value);
		}
		int compare = key.compareTo(node.key);
		if (compare < 0) { // left node
			node.left = put(key, value, node.left);
		} else if (compare > 0) { // right node
			node.right = put(key, value, node.right);
		} else { // same key
			node.value = value;
		}
		node.count = size(node.left) + size(node.right);
		return node;
	}
	/**
	 * recursive version
	 */
	public Value get(Key key) {
		if (key == null) {
			throw new IllegalArgumentException();
		}
		return get(key, root);
	}
	
	private Value get(Key key, Node node) {
		if (node == null) {
			return null;
		}
		int compare = key.compareTo(node.key);
		if (compare < 0) { // left node
			return get(key, node.left);
		} else if (compare> 0) { // right node
			return get(key, node.right);
		} else { // same key
			return node.value;
		}
		
	}
	
	public void delete(Key key) {
		if (key == null) {
			throw new IllegalArgumentException();
		}
		root = delete(key, root);
	}
	
	private Node delete(Key key, Node node) {
		if (node == null) {
			return node;
		}
		int compare = key.compareTo(node.key);
		if (compare < 0) { // left node
			node.left = delete(key, node.left);
		} else if (compare > 0) { // right node
			node.right = delete(key, node.right);
		} else { // same key
			if (node.right == null) { // no right child
				return node.left;
			}
			
			if (node.left == null) { // no left child
				return node.right;
			}
			Node origin = node;
			node = min(node); // replace with successor
			node.right = deleteMin(origin.right);
			node.left = origin.left;
		}
		node.count = 1 + size(node.left) + size(node.right);
		return node;
	}
	
	public Key min() {
		return min(root).key;
	}
	
	private Node min(Node node) {
		if (node.left == null) {
			return node;
		}
		return node.left;
	}
	
	public void deleteMin() {
		root = deleteMin(root);
	}
	
	private Node deleteMin(Node node) {
		if (node == null) {
			return null;
		}
		
		if (node.left == null) {
			return node.right; // delete this node
		}
		node.left = deleteMin(node.left);
		node.count = 1 + size(node.left) + size(node.right);
		return node;
	}
	
	public Key floor(Key key) {
		Node floorNode = floor(key, root);
		if (floorNode == null) {
			return null;
		}
		return floorNode.key;
	}
	
	private Node floor(Key key, Node node) {
		if (node == null) {
			return null;
		}
		
		int compare = key.compareTo(node.key);
		
		if (compare == 0) {
			return null;
		} else if (compare < 0) {
			return floor(key, node.left);
		} else {
			Node tmp = floor(key, node.right);
			if (tmp == null) { // mean no other node's key  less than key and bigger than this node's key 
				return node;
			}
			return tmp;
		}

	}
	
	public Key ceiling(Key key) {
		Node ceilingNode = ceiling(key, root);
		if (ceilingNode == null) {
			return null;
		}
		return ceilingNode.key;
	}
	
	private Node ceiling(Key key, Node node) {
		if (node == null) {
			return null;
		}
		
		int compare = key.compareTo(node.key);
		if (compare == 0) {
			return null;
		} else if (compare < 0) {
			Node tmpNode = ceiling(key, node.left);
			if (tmpNode == null) { 
				return node;
			}
			return tmpNode;
		} else { // compare > 0
			return ceiling(key, node.right);
		}
	}
	
	public static void main(String args[]) {
		
	}
}
