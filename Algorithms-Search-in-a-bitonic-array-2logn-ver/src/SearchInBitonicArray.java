/**
 * An array is bitonic if it is comprised of an increasing sequence of integers followed 
 * immediately by a decreasing sequence of integers. Write a program that, given a bitonic array of n
 * distinct integer values, determines whether a given integer is in the array.
 * Standard version: Use ∼3lg*n* compares in the worst case.
 * Signing bonus: Use ~ 2lg*n* compares in the worst case (and prove that no algorithm can guarantee to
 * perform fewer than ~ 2lg*n* compares in the worst case).
 * Time complex ~2lgn version. ( 2lgn for binary search for left and right side.)
 * @author user
 *
 */
import edu.princeton.cs.algs4.StdOut;
public class SearchInBitonicArray {
	
	private int[] array;
	public SearchInBitonicArray(int[] array) {
		this.array = array;
	}
	
	private int binarySearch(int key, int left, int right) {
		while (left <= right) {
			int mid = (left + right) / 2;
			if (key < array[mid]) {
				right = mid - 1;
			} else if (key > array[mid]) {
				left = mid + 1;
			} else {
				return mid;
			}
		}
		return -1;
	}
	
	private int binarySearchReversed(int key, int left, int right) {
		while (left <= right) {
			int mid = (left + right) / 2;
			if (key < array[mid]) {
				left = mid + 1;
			} else if (key > array[mid]) {
				right = mid - 1;
			} else {
				return mid;
			}
		}
		return -1;
	}
	public int bitonicSearch(int key, int left, int right) {

		if (left > right) {
			return -1;
		}
		int mid = (left + right) / 2;
		
		if (key > array[mid]) {
			if (array[mid] < array[mid + 1]) { // ascend area
				return bitonicSearch(key, mid + 1, right);
			} else { // descend area
				return bitonicSearch(key, left, mid - 1);
			}
		} else {
			int ascnedResult = binarySearch(key, left, mid);
			if (ascnedResult != -1) {
				return ascnedResult;
			}
			
			int descendResult = binarySearchReversed(key, mid, right);
			return descendResult;
		}
	}

	
	public static void main(String args[]) {
		int[] bitonicArray = {1, 3, 4, 6, 9, 14, 15, 85, 73, 11, 7, 2, -4, -9};
		SearchInBitonicArray searchBitonic = new SearchInBitonicArray(bitonicArray);
		StdOut.println("Number 11 index is " + searchBitonic.bitonicSearch(73, 0, bitonicArray.length - 1));
	}

}
