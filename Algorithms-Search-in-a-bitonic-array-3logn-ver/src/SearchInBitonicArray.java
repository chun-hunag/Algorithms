/**
 * An array is bitonic if it is comprised of an increasing sequence of integers followed 
 * immediately by a decreasing sequence of integers. Write a program that, given a bitonic array of n
 * distinct integer values, determines whether a given integer is in the array.
 * Standard version: Use ∼3lg*n* compares in the worst case.
 * Signing bonus: Use ~ 2lg*n* compares in the worst case (and prove that no algorithm can guarantee to
 * perform fewer than ~ 2lg*n* compares in the worst case).
 * Time complex ~3lgn version. (lgn for search max key value, 2lgn for binary search for left and right side.)
 * @author user
 *
 */
import edu.princeton.cs.algs4.StdOut;
public class SearchInBitonicArray {
	private int[] array;
	private int maxKey;
	public SearchInBitonicArray(int[] array) {
		this.array = array;
		maxKey = searchMaxNumber();
	}
	
	public int getMaxNumber() {
		return array[maxKey];
	}
	
	private int searchMaxNumber() {
		int head = 0;
		int tail = array.length - 1;
		while (head <= tail) {
			int mid = (head + tail) / 2;
			if (array[mid] < array[mid - 1]) {
				tail = mid;
			} else if (array[mid] < array[mid + 1]){
				head = mid;
			} else {
				return mid;
			}
		}
		return tail;
	}
	
	private int binarySearch(int key, int head, int tail) {
		while (head <= tail) {
			int mid = (head + tail) / 2;
			if (key < array[mid]) {
				tail = mid - 1;
			} else if (key > array[mid]) {
				head = mid + 1;
			} else {
				return mid;
			}
		}
		return -1;
	}
	
	private int binarySearchReverse(int key, int head, int tail) {
		while (head <= tail) {
			int mid = (head + tail) / 2;
			if (key < array[mid]) {
				head = mid + 1;
			} else if (key > array[mid]) {
				tail = mid - 1;
			} else {
				return mid;
			}
		}
		return -1;
	}
	public int bitonicSearch(int key) {
		
		if (getMaxNumber() == key) {
			return maxKey;
		}
		
		int leftSideResult = binarySearch(key, 0, maxKey - 1);
		if (leftSideResult != -1) {
			return leftSideResult;
		}
		
		int rightSideResult = binarySearchReverse(key, maxKey + 1, array.length - 1);
		
		if (rightSideResult != -1) {
			return rightSideResult;
		}
		
		return -1;
		
	}
	
	public static void main(String args[]) {
		int[] bitonicArray = {1, 3, 4, 6, 9, 14, 15, 85, 73, 11, 7, 2, -4, -9};
		SearchInBitonicArray searchBitonic = new SearchInBitonicArray(bitonicArray);
		StdOut.println("Max Number is " + searchBitonic.getMaxNumber());
		StdOut.println("Number 4 index is " + searchBitonic.bitonicSearch(11));
	}
}
