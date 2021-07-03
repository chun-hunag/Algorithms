
/**
 * Given a binary tree where each Node contains a key, determine whether it is a binary search tree. 
 * Use extra space proportional to the height of the tree.
 * @author ChunHuang
 *
 */
public class BinaryTreeChecker {
	
	// °²³]¦¹¬° BST tree's node
	public class TreeNode {
		private TreeNode left;
		private TreeNode right;
		private long val;
		
		public TreeNode getLeft() {
			return left;
		}

		public void setLeft(TreeNode left) {
			this.left = left;
		}

		public TreeNode getRight() {
			return right;
		}

		public void setRight(TreeNode right) {
			this.right = right;
		}

		public long getVal() {
			return val;
		}

		public void setVal(long val) {
			this.val = val;
		}

		public TreeNode(long val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
		
		
	}
	
	
	public static boolean isValidBST(TreeNode root) {
		if (root == null) {
			return true;
		}
		return valid(root, Long.MIN_VALUE, Long.MAX_VALUE);
		
	}
	
	private static boolean valid(TreeNode node, long low, long height) {
		if (node == null) {
			return true;
		}
		
		if (node.getVal() <= low || node.getVal() >= height) {
			return false;
		}
		return valid(node.getLeft(), low, node.val) && valid(node.getRight(), node.val, height);
	}

}
