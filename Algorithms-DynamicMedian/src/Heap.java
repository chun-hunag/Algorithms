import java.util.Random;

public class Heap<T extends Comparable<T>> {
	private T[] array;
	private boolean isMaxHeap;
	private int count;
	public Heap(boolean isMaxHeap) {
		array = (T[]) new Comparable[3];
		this.isMaxHeap = isMaxHeap;
		count = 0;
	}
	
	public boolean isEmpty() {
		return count == 0;
	}
	
	public int size() {
		return count;
	}
	
	public void insert(T t) {
		if (array.length - 1 == count) {
			grow();
		}
		array[++count] = t; // using ++count because start from 1 is easier to calculate
		swim(count);
	}
	
	public T delRoot() {
		T root = array[1];
		exchange(1, count--);
		sink(1);
		array[count + 1] = null;
		if (count == array.length / 4) {
			resize();
		}
		return root;
	}
	
	public T getRoot() {
		return array[1];
	}
	/**
	 * new insert element compare to parent until root
	 * @param k
	 */
	private void swim(int k) {
		while(k > 1) {
			if (isMaxHeap && less(array[k/2], array[k])) { // max heap
				exchange(k/2, k);
			}
			
			if (!isMaxHeap && less(array[k], array[k/2])) { // min heap
				exchange(k, k/2);
			}
			k = k/2; // compare to parent node
		}
	}
	/**
	 * root compare child(most smaller(or larger one) until bottom
	 * @param k
	 */
	private void sink(int k) {
		while (2*k <= count) {
			
			int j = 2*k;
			// max heap
			if (isMaxHeap && less(array[j], array[j+1])) {
				j++;
			}
			
			if (isMaxHeap && less(array[k], array[j])) { 
				exchange(k, j);
			}
			// min heap
			if (!isMaxHeap && less(array[j+1], array[j])) {
				j++;
			}
			
			if (!isMaxHeap && less(array[j], array[k])) {
				exchange(k, j);
			}
			k = j;
		}
	}
	
	private void exchange(int a, int b) {
		T tmp = array[a];
		array[a] = array[b];
		array[b] = tmp;
	}
	
	private boolean less(T a, T b) {
		return a.compareTo(b) < 0;
	}
	
	private boolean larger(T a, T b) {
		return a.compareTo(b) > 0;
	}
	
	
	
	/**
	 * extends array size to 2 * size
	 */
	private void grow() {
		int size = array.length;
		T[] newArray = (T[]) new Comparable[size * 2];
		for (int i = 0; i < size; i++) {
			newArray[i] = array[i];
		}
		array = newArray;
	}
	
	/**
	 * shrink array size to size / 2
	 */
	private void resize() {
		int size = array.length;
		int newSize = size/2;
		T[] newArray = (T[]) new Comparable[newSize];
		for (int i = 0; i < newSize; i++) {
			newArray[i] = array[i];
		}
		array = newArray;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int i = 1; i < count; i++) {
			builder.append(array[i]);
			if (i != count - 1) {
				builder.append(",");
			}
		}
		return builder.toString();
	}
	
	public static void main(String args[]) {
		Heap minHeap = new Heap(false);
		Heap maxHeap = new Heap(true);
		Random random = new Random();
		
		for (int i = 0; i < 10; i++) {
			minHeap.insert(new Integer(random.nextInt(100)));
			maxHeap.insert(new Integer(random.nextInt(100)));
		}
		System.out.println("Max: ");
		System.out.println(minHeap.toString());
		while (!minHeap.isEmpty()) {
			System.out.print(minHeap.delRoot() + ",");
		}
		System.out.println();
		System.out.println("Min: ");
		System.out.println(maxHeap.toString());
		while (!maxHeap.isEmpty()) {
			System.out.print(maxHeap.delRoot() + ",");
		}
		System.out.println();
		

	}

}
