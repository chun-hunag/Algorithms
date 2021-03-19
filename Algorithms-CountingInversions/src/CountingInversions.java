import java.util.Arrays;

/**
 * An inversion in an array a[] is a pair of entries a[i] and a[j] such that i < j but a[i] > a[j]. 
 * Given an array, design a linearithmic algorithm to count the number of inversions.
 * @author ChunHuang
 *
 */
public class CountingInversions {
	public static int CalculateInversions(Comparable[] array) {
		return sort(array);
	}
	
	private static int sort(Comparable[] array) {
		int insersionCount = 0;
		int N = array.length;
		Comparable[] auxArray = new Comparable[N];
		for (int sz = 1; sz < N; sz = sz*2) { // sub array size 
			for (int lo = 0; lo < N-sz; lo += 2*sz) {
				int left = lo;
				int mid = lo+sz-1;
				int right = Math.min(lo + sz + sz - 1, N -1);
				System.out.println(left + "," + mid + "," + right);
				insersionCount += merge(left, mid, right, array, auxArray);
			}
		}

		return insersionCount;
	}
	
	private static int merge(int lo, int mid, int hi, Comparable[] array, Comparable[] auxArray) {
		int i = lo;
		int j = mid + 1;
		int insersionCount = 0;
		for (int k = lo; k <= hi; k++) { // copy array
			auxArray[k] = array[k];
		};

		for (int k = lo; k <= hi; k++) {
			if (i > mid) { // left side sorted
				array[k] = auxArray[j++];
			} else if (j > hi) { // right side sorted
				array[k] = auxArray[i++];
			} else if (less(auxArray[i], auxArray[j])) {
				array[k] = auxArray[i++];
			} else { // left side element bigger than right side , is insersion.
				System.out.println(Arrays.toString(auxArray));
				System.out.println(Arrays.toString(array));
				insersionCount += mid + 1 - i; // element i to element mid is inversions
				array[k] = auxArray[j++];
			}
		};

		return insersionCount;
	}
	
	private static boolean less(Comparable a, Comparable b) {
		return a.compareTo(b) < 0;
	}
	
	public static void main(String args[]) {
		Integer[] array = {2, 4, 1, 3, 5};
		System.out.println("insersions number: " + CountingInversions.CalculateInversions(array));
		System.out.println(Arrays.toString(array));
	}


}
