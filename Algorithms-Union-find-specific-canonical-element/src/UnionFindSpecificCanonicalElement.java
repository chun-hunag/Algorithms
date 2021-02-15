/**
 * Add a method find() to the union-find data type so that
 * find(i) returns the largest element in the connected component containing i.
 * The operations, union(), connected(), and find() should all take logarithmic time or better.
 * For example, if one of the connected components is {1, 2, 6, 9}, then thE find() method should 
 * return 99 for each of the four elements in the connected components.
 * @author user
 *
 */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
public class UnionFindSpecificCanonicalElement {
	private int[] parent;
	private int count;
	public UnionFindSpecificCanonicalElement(int n) {
		parent = new int[n];
		for (int i = 0; i < n; i++) {
			parent[i] = i;
		}
		count = n;
	}
	
	public int count() {
		return count;
	}

	public int find(int p) {
		while ( p != parent[p]) {
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
		
		if (pParent == qParent) {
			return;
		} else if (pParent < qParent) {
			parent[p] = qParent; // path compression
		} else {
			parent[q] = pParent; // path compression
		}
		count--;
	}
	
	public static void main(String args[]) {
		
		int n = StdIn.readInt();
		UnionFindSpecificCanonicalElement ufSpecial = new UnionFindSpecificCanonicalElement(n);
		
		while (!StdIn.isEmpty()) {
			int p = StdIn.readInt();
			int q = StdIn.readInt();
			
			ufSpecial.union(p, q);
			StdOut.println("union point: " + p + " " + q);
		}
		StdOut.println(ufSpecial.count() + " components");

	}
  }
