import edu.princeton.cs.algs4.StdOut;
/**
 * 3-SUM in quadratic time. Design an algorithm for the 3-SUM problem that takes time proportional to n^2
 * in the worst case. You may assume that you can sort the n integers in time proportional to n^2 or better.
 * Note: these interview questions are ungraded and purely for your own enrichment. To get a hint, submit a solution.
 * @author user
 *
 */
public class ThreeSum {
	
	private int[] array;
	
	public ThreeSum(int[] array){
		this.array = array;
		insertionSort(array);
	}
	
	private void insertionSort(int[] array){
		int size = array.length;
		for (int i = 1; i < size; i++) {
			for (int j = i; j > 0; j--) {
				if (array[j] < array[j - 1]) {
					int tmp = array[j];
					array[j] = array[j - 1];
					array[j - 1] = tmp;
				}
			}
		}
	}
	
	private int binarySearch(int key){
		int head = 0;
		int tail = array.length - 1;
		while ( head <= tail) {
			int mid = (head + tail) / 2;
			if (array[mid] > key) {
				tail = mid - 1;
			} else if (array[mid] < key) {
				head = mid + 1;
			} else {
				return mid;
			}
		}
		return -1;
	}
	
	public int getThreeSumCount(){
		int size = array.length;
		int threeSumCount = 0;
		for (int i = 0; i < size - 2; i++) {
			for(int j = i + 1; j < size - 1; j++) {
				int twoSum = array[i] + array[j];
				int target = -twoSum;
				int targetIndex = binarySearch(target);
				if (targetIndex <= j || targetIndex == -1) { // targetIndex must less than j and i, all it will be a repeat combination.
					continue;
				}
				threeSumCount++;
			}
		}
		return threeSumCount;
	}
	
	public static void main(String args[]) {
		int[] array = {4, 5, 9, 7, 1, 42, 4, 3, 1, 455, 21, 39, -9};
		ThreeSum threeSum = new ThreeSum(array);
		
		for (int i = 0; i < array.length; i++) {
			StdOut.print(array[i]);
			if (i != array.length - 1) {
				StdOut.print(",");
			}
		}
		System.out.println();

		System.out.println("Three sum count: " + threeSum.getThreeSumCount());
	}
}
