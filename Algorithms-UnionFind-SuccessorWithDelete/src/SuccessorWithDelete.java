/**
 * Successor with delete. Given a set of n integers S={0,1,...,n−1} and a sequence of requests of the following form:
 * Remove x from S
 * Find the successor of x: the smallest y in S such that y >= x.
 * design a data type so that all operations (except construction)  take logarithmic time or better in the worst case.
 * @author user
 *
 */
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
public class SuccessorWithDelete {
	private int[] parent;
	private boolean[] remove; 
	private int count;
	
	public SuccessorWithDelete(int n) {
		parent = new int[n];
		remove = new boolean[n];
		for (int i = 0; i < n; i++) {
			parent[i] = i;
			remove[i] = false;
		}
		count = n;
	}
	
	public int count() {
		return count;
	}
	
	public int find(int p) {
		while (p != parent[p]) {
			p = parent[p];
		}
		return p;
	}
	
	public boolean connected(int p, int q) {
		return find(p) == find(q);
	}
	
	public void union(int p, int q) {
		int pParent = find(p);
		int qParent = find(q);
		
		if(pParent == qParent) {
			return;
		} else if (pParent < qParent) {
			parent[p] = qParent;
		} else {
			parent[q] = pParent;
		}
	}
	
	public void remove(int p) {
		if (p < 0 || p >= parent.length) {
			throw new IllegalArgumentException();
		}
		
		remove[p] = true;
		if (p > 0 && remove[p-1]) { // if ajacent element is removed, union it
			union(p, p-1);
		}
		
		if (p < parent.length - 1 && remove[p+1]) {  // if ajacent element is removed, union it
			union(p, p+1);
		}
	}
	
	public int getSuccessor(int p) {
		if (p < 0 || p >= parent.length) {
			throw new IllegalArgumentException();
		}
		
		if (remove[p]) {
			int pParent = find(p);
			if (find(p) + 1  > parent.length - 1) { // mean all element bigger than p were removed
				return -1;
			}
			return pParent + 1;
		} else { // element p is not removed
			return p;
		}
	}
	
	public static void main(String args[]) {
		int n = StdIn.readInt();
		SuccessorWithDelete successor = new SuccessorWithDelete(n);
		int p = 0;
		while (!StdIn.isEmpty()) {
			p = StdIn.readInt();
			successor.remove(p);
			StdOut.println("The successor of " + p + " is " + successor.getSuccessor(p));
		}
	}
}
