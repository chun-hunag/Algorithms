import java.util.Random;

public class RandomizedPriorityQueue<T extends Comparable<T>> {
	private T[] array;
	private int count;
	
	public RandomizedPriorityQueue() {
		array = (T[]) new Comparable[3];
		count = 0;
	}
	
	public boolean isEmpty() {
		return count == 0;
	}
	
	public void insert(T t) {
		if (t == null) {
			throw new IllegalArgumentException();
		}
		if (count == array.length - 1) {
			grow();
		}
		array[++count] = t;
		swim(count);
	}
	
	public T sample() {
		Random random = new Random();
		return array[random.nextInt(count - 1) + 1];
	}
	
	public T delRandom() {
		Random random = new Random();
		int k = (int) (Math.random() * count) + 1;
		T element = array[k];
		exchange(k, count);
		sink(k);
		array[count] = null;
		count--;
		if (count == array.length / 4) {
			resize();
		}
		return element;
	}
	
	private void swim(int k) {
		while (k > 1) {
			if (less(array[k/2], array[k])) {
				exchange(k/2, k);
			}
			k = k/2;
		}
	}
	
	private void sink(int k) {
		while (k * 2 < count) {
			int j = 2 * k;
			if (less(array[j], array[j+1])) {
				j++;
			}
			
			if (less(array[k], array[j])) {
				exchange(k, j);
			}
			k = 2 * k;
		}
	}
	
	private boolean less(T a, T b) {
		return a.compareTo(b) < 0;
	}
	
	private void exchange(int a, int b) {
		T tmp = array[a];
		array[a] = array[b];
		array[b] = tmp;
	}
	
	
	private void grow() {
		int size = array.length;
		T[] tmpArray = (T[]) new Comparable[size*2];
		for (int i = 0; i < size; i++) {
			tmpArray[i] = array[i];
		}
		array = tmpArray;
	}
	
	private void resize() {
		int size = array.length/2;
		T[] tmpArray = (T[]) new Comparable[size];
		for (int i = 0; i < size; i++) {
			tmpArray[i] = array[i];
		}
		array = tmpArray;
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
		RandomizedPriorityQueue randomQueue = new RandomizedPriorityQueue();
		
		Random random = new Random();
		
		for (int i = 0; i < 10; i++) {
			randomQueue.insert(new Integer(random.nextInt(100)));
		}
		System.out.println(randomQueue.toString());
		while (!randomQueue.isEmpty()) {
			System.out.print(randomQueue.delRandom() + ",");
		}
		System.out.println();

		System.out.println();
		

	}
}
