
public class Heap {
	public static void sort(Comparable[] elements) {
		int n = elements.length;
		for (int i = n/2; i >= 1; i--) { // heap contruct, and n/2 is the last parent node
			sink(i, n, elements); // make sure every parent node bigger than their child node
		}
		while (n > 1) {
			exchange(1, n, elements); // move 1 (biggest element) to N position
			sink(1, --n, elements); // sink 1 to sure heap structure, and --n (heap 's N eliminate)
		}
	}
	
	private static void sink(int i, int n, Comparable[] elements) {

		while (i * 2  < n) { // < n because is 0 base
			int child = i * 2 ;
			if (less(child, child + 1, elements)) { // find bigger child
				child++;
			}
			if (!less(i, child, elements)) {
				break;
			}
			exchange(i, child, elements);
			i = child;
		}
	}
	
	private static void exchange(int i, int j, Comparable[] elements) {
		i--; j--;
		Comparable tmp = elements[i];
		elements[i] = elements[j];
		elements[j] = tmp;
	}
	
	private static boolean less(int i, int j, Comparable[] elements) {
		i--; j--;
		return elements[i].compareTo(elements[j]) < 0;
	}
	
	public static void main(String args[]) {
		
		Integer[] array = new Integer[100];
		int size = array.length;
		for (int i = 0; i < size; i++) {
			array[i] = ((int) (Math.random() * 100));
		}
		printIntegerArray(array);
		Heap.sort(array);
		printIntegerArray(array);
	}
	
	public static void printIntegerArray(Integer[] array) {
		int size = array.length;
		System.out.println("[");
		for (int i = 0; i < size; i++) {
			System.out.print(array[i]);
			if ((i + 1) % 10 == 0) {
				System.out.println();
			} else {
				System.out.print(",");
			}
		}
		System.out.println("]");

	}
}
