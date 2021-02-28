
public class QuickSort {
	public static void sort(Comparable[] array) {
		sort(array, 0, array.length - 1);
	}
	
	public static void sort(Comparable[] array, int left, int right) {
		if (left >= right) {
			return;
		}
		
		int mid = partition(array, left, right);
		sort(array, left, mid - 1);
		sort(array, mid + 1, right);
	}
	
	public static int partition(Comparable[] array, int left, int right) {
		int leftPointer = left;
		int rightPointer = right + 1;
		while (true) { // array[left] as pivot
			while (less(array[++leftPointer], array[left])) {  // find bigger than array[left]
				if (leftPointer == right) {
					break;
				}
			}
			
			while (less(array[left], array[--rightPointer])) { // find smaller than array[left]
				if (rightPointer == left) {
					break;
				}
			}
			if (leftPointer >= rightPointer) { // while this momonet, the element bigger than array[left] is on rightPointer right side, and smaller one on rightPointer left side.
				break;
			}
			exchange(array, leftPointer, rightPointer); // switch leftPointer and rightPointer ( bigger than array[left] one move to right site, and smaller than array[left] one move to left side)
		}
		// switch left and rightPointer
		exchange(array, left, rightPointer);
		return rightPointer;
	}
	
	private static boolean less(Comparable a, Comparable b) {
		return a.compareTo(b) < 0;
	}
	
	private static void exchange(Comparable[] array, int a, int b) {
		Comparable tmp = array[a];
		array[a] = array[b];
		array[b] = tmp;
	}
	
	public static void main(String args[]) {
		Integer[] array = new Integer[100];
		for (int i = 0; i < array.length; i++) {
			array[i] = new Integer((int) (Math.random() * 100));
		}
		printIntegerArray(array);
		System.out.println("-------------");
		QuickSort.sort(array);
		printIntegerArray(array);

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
