
public class BinaryHeapPriorityQueue<T extends Comparable<T>> {
	private T[] priorityQueue;
	private int count;
	
	public BinaryHeapPriorityQueue(int capacity) {
		priorityQueue = (T[]) new Comparable[capacity + 1];
		count = 0;
	}
	
	public boolean isEmpty() {
		return count == 0;
	}
	
	public void insert(T element) {
		priorityQueue[++count] = element;
		swim(count);
	}
	
	public T delMax() {
		T max = priorityQueue[1];
		exchange(1, count);
		count--;
		sink(1);
		priorityQueue[count + 1] = null;
		return max;
	}
	
	private void swim(int index) {
		while (index > 1 && less(index / 2, index)) {
			exchange(index / 2, index);
			index = index / 2;
		}
	}
	
	private void sink(int index) {
		while (index * 2 <= count) {
			int child = index * 2;
			if (less(child, child + 1)) { // find bigger child
				child++;
			}
			
			if(!less(index, child)) { // compare with bigger one child
				break;
			}
			exchange(index, child);
			index = child;
		}
	}
	
	private boolean less(int index0, int index1) {
		return priorityQueue[index0].compareTo(priorityQueue[index1]) < 0;
	}
	
	private void exchange(int index0, int index1) {
		T tmp = priorityQueue[index0];
		priorityQueue[index0] = priorityQueue[index1];
		priorityQueue[index1] = tmp; 
	}
	
	public static void main(String args[]) {
		BinaryHeapPriorityQueue<Integer> pq = new BinaryHeapPriorityQueue<Integer>(100);
		
		for (int i = 0; i < 100; i++) {
			pq.insert((int) (Math.random() * 100));
		}
		System.out.println("out");
		int count = 1;
		while (!pq.isEmpty()) {
			System.out.print(pq.delMax());
			if (count++ % 10 == 0) {
				System.out.println();
			} else {
				System.out.print(",");
			}
		}
	}

}
