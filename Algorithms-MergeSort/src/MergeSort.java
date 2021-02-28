
public class MergeSort {
	public static void sort(Comparable[] array) {
		Comparable[] auxArray = new Comparable[array.length];
		sort(array, auxArray, 0, array.length - 1);
		printIntegerArray((Integer[])array);

	}
	
	private static void sort(Comparable[] array, Comparable[] auxArray, int left, int right) {
		if (left >= right) {
			return;
		}
		int mid = (left + right) / 2;
		sort(array, auxArray, left, mid);
		sort(array, auxArray, mid + 1, right);
		merge(array, auxArray, left, mid, right);
	}
	
	public static void merge(Comparable[] array, Comparable[] auxArray, int left, int mid, int right) {
		for (int i = left; i <= right ; i++) {
			auxArray[i] = array[i];
		}
		int leftPointer = left;
		int rightPointer = mid + 1;
		for (int i = left; i <= right; i++) {
			if (leftPointer > mid) { // mean left side is all merge
				array[i] = auxArray[rightPointer++];
			} else if (rightPointer > right) { // mean right side is all merge
				array[i] = auxArray[leftPointer++];
			} else if (less(auxArray[leftPointer], auxArray[rightPointer])) {
				array[i] = auxArray[leftPointer++];
			} else {
				array[i] = auxArray[rightPointer++];
			}
		}
	}
	
	public static boolean less(Comparable a, Comparable b) {
		return a.compareTo(b) < 0;
	}
	
	public static void main(String args[]) {
		Integer[] array = new Integer[100];
		for (int i = 0; i < array.length; i++) {
			array[i] = new Integer((int) (Math.random() * 100));
		}
		printIntegerArray(array);
		System.out.println("-------------");
		MergeSort.sort(array);
//		printIntegerArray(array);

	}
	
	public static void printIntegerArray(Integer[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]);
			if ((i + 1) % 10 == 0) {
				System.out.println();
			} else {
				System.out.print(", ");
			}
		}
	}
}
