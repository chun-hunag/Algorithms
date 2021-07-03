import java.util.Arrays;

import edu.princeton.cs.algs4.StdRandom;

/**
 * Suppose that the subarray a[0] to a[n-1]] is sorted and the subarray a[n-1] to a[2∗n−1] is sorted. 
 * How can you merge the two subarrays so that a[0] to a[2∗n−1] is sorted using an auxiliary array of length n (instead of 2n)?
 * @author ChunHuang
 *
 * @param <T>
 */
public class MergeSortedSubArray {
	
	public static void merge(Comparable[] array) {
		int n = array.length / 2;
		int n2 = array.length;

		Comparable[] auxArray = new Comparable[n];
		
		// moving 0 - n element to aux array
		for (int i = 0; i < n; i++) {
			auxArray[i] = array[i];
		}
		
		int auxIndex = 0;
		int arrayIndex = n;
		for (int i = 0; i < n2; i++) {
			if (auxIndex >= n) { // mean 0 - (n-1) is all sorted, n - (2n -1) is sorted before
				break;
			} else if (arrayIndex >= n2) {
				array[i] = auxArray[auxIndex++];
			} else if (less(auxArray[auxIndex], array[arrayIndex]) < 0) { // mean 0 - (n-1) is smaller
				array[i] = auxArray[auxIndex++];
			} else { // mean n - (2n - 1 ) is smaller
				array[i] = array[arrayIndex++];
			}
		}
	}
	
	private static int less(Comparable a, Comparable b) {
		return a.compareTo(b);
	}
	public static void main(String[] args){
		int n = 10;
		int[] subarray1 = new int[n];
		int[] subarray2 = new int[n];
		for (int i = 0; i < n; i++) {
		     subarray1[i] = StdRandom.uniform(100);
		     subarray2[i] = StdRandom.uniform(100);
		}
		Arrays.sort(subarray1);
		Arrays.sort(subarray2);
		Integer[] array = new Integer[2*n];
		for(int i = 0; i<n;i++){
		     array[i] = subarray1[i];
		     array[n+i] = subarray2[i];
		}
		System.out.println(Arrays.toString(array));
		 MergeSortedSubArray.merge(array);
		System.out.println(Arrays.toString(array));
	}
	
}
