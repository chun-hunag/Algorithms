
public class ThreeWayPartition {
	public static final int COLOR_RED = 0;
	public static final int COLOR_WHILTE = 1;
	public static final int COLOR_BLUE = 2;
	
	public static void threeWayQuickSort(Comparable[] array) {
		sort(array, 0, array.length - 1);
	}

	
	private static void sort(Comparable[] array, int left, int right) {
		if (left >= right) { // end condition
			return;
		}
		Comparable pivot = array[left];
		int i = left;
		int leftPointer = left;
		int rightPointer = right;
		while (i <= rightPointer) {
			if (compare(array[i], pivot) < 0) {
				exchange(array, leftPointer++, i++); // left and i exchange, and i now is compared, so  i should i++
			} else if (compare(array[i], pivot) > 0) {
				exchange(array, rightPointer--, i);// right and i exchange, and i now is not compared, so i should be i not i++
			} else { // array[i] equal to pivot
				i++;
			}
		}
		// now array be split to 3 part  [ less pivot | equal pivot | larger pivot  ]
		sort(array, left, leftPointer - 1);
		sort(array, rightPointer + 1, right);
	}
	private static int compare(Comparable colorX, Comparable colorY) {
		return colorX.compareTo(colorY);
	}
	
	private static void exchange(Comparable[] array, int x, int y) {
		Comparable tmp = array[x];
		array[x] = array[y];
		array[y] = tmp;
	}
	
	public static void main(String args[]) {
		Integer[] colors = new Integer[100];
		for (int i = 0; i < colors.length; i++) {
			colors[i] = (int) (Math.random() * 3);
		}
		printIntegerArray(colors);
		System.out.println("-------------");
		ThreeWayPartition.threeWayQuickSort(colors);
		printIntegerArray(colors);
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
