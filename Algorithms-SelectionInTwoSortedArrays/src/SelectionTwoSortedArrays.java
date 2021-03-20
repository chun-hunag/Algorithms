import java.util.Arrays;
import java.util.Random;

/**
 * Given two sorted arrays $a[]$ and $b[]$ ,  
 * of lengths $n_1$ and $n_2$ and an integer 0 ≤ $k$ < $n_1 + n_2$, 
 * design an algorithm to find a key of rank k. 
 * The order of growth of the worst case running time of your algorithm should be $logn$ , 
 * where $n = n_1 + n_2$ .
 * Version 1: $n_1 = n_2$ (equal length arrays) and $k = n / 2$ (median).
 * Version 2: $k = n/2$ (median).
 * Version 3: no restrictions.
 * @author ChunHuang
 *
 */
public class SelectionTwoSortedArrays {
	public static <T extends Comparable> T findKthItem(T[] a, T[] b, int k) {
		return findKthItem(a, 0, b, 0, k);
	}
	
	private static <T extends Comparable> T findKthItem(T[] a, int aStart, T[] b, int bStart, int k) {
			if (aStart >= a.length) { 
				return b[bStart + k - 1];
			}
			
			if (bStart >= b.length) {
				return a[aStart + k -1];
			}
			
			if (k == 1) {
				return (a[aStart].compareTo(b[bStart]) < 0) ? a[aStart] : b[bStart];
			}
			
			int indexAK2th = aStart + k/2 - 1; // k/2 th a array element
			int indexBK2th = bStart + k/2 - 1; // k/2 th b array element
			T k2thA = a[indexAK2th];
			T k2thB = b[indexBK2th];
			System.out.println(k2thA + ", " + k2thB);
			System.out.println(k2thA.compareTo(k2thB));

			if (k2thA.compareTo(k2thB) < 0) {
				System.out.println("-");
				return findKthItem(a, indexAK2th + 1, b, bStart, k - k/2);
			} else {
				System.out.println("+");
				return findKthItem(a, aStart, b, indexBK2th + 1, k - k/2);
			}
	}
	
	public static void main(String args[]) {
		Integer[] a = new Integer[10];
		Integer[] b = new Integer[10];
		Random random = new Random();
		
		for (int i = 0; i < 10; i++) {
			a[i] = random.nextInt(100);
			b[i] = random.nextInt(100);
		}
		
		Arrays.sort(a);
		Arrays.sort(b);
		System.out.println("a: ");
		System.out.println(Arrays.toString(a));
		System.out.println("b: ");
		System.out.println(Arrays.toString(b));
		Integer kth = SelectionTwoSortedArrays.findKthItem(a, b, 5);
		System.out.println("kth: " + kth);
	}
}
