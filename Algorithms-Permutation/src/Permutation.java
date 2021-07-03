import java.util.Arrays;

public class Permutation {
	public static boolean compare(Comparable[] array1, Comparable[] array2) {
		int size1 = array1.length;
		int size2 = array2.length;
		if (size1 != size2) {
			return false;
		}
		
		shellSort(array1);
		shellSort(array2);
		
		for (int i = 0; i < size1; i++) {
			if (array1[i].compareTo(array2[i]) != 0) {
				return false;
			}
		}
		return true;
		
	}
	
	public static void shellSort(Comparable[] array) {
		int size = array.length;
		// decide h
		int h = 1;
		while (h < size/3) {
			h = h * 3 + 1; // can use other pattern, but this pattern is most effetive
		}
		while (h >= 1) {
			for (int i = h; i < size; i++) {
				for (int j = i; j >= h; j-=h) {
					if (less(array, j, j-h)) {
						exchange(array, j, j-h);
					}
				}
			}
			h = h/3;
		}

	}
	
	private static boolean less(Comparable[] array, int x, int y) {
		return array[x].compareTo(array[y]) < 0;
	}
	
	private static void exchange(Comparable[] array, int x, int y) {
		Comparable tmp = array[x];
		array[x] = array[y];
		array[y] = tmp;
	}
	
	
	public static void main(String args[]) {
		Integer[] array = new Integer[100];
		Integer[] array2 = new Integer[100];
		for (int i = 0; i < array.length; i++) {
			array[i] = new Integer((int) (Math.random() * 100));
			array2[i] = new Integer((int) (Math.random() * 100));
		}
		Integer[] array3 = Arrays.copyOf(array, array.length);
		System.out.println("array");
		printIntegerArray(array);
		System.out.println("array2");
		printIntegerArray(array2);
		System.out.println("array3");
		printIntegerArray(array3);
		
		System.out.println("array & array3 is same after sort: " + Permutation.compare(array, array3) );
		System.out.println("array & array2 is same after sort: " + Permutation.compare(array, array2) );


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
