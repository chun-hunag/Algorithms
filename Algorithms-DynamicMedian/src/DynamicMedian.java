import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class DynamicMedian<T extends Comparable<T>> {
	private Heap<T> minHeap;
	private Heap<T> maxHeap;
	private int count;
	
	public DynamicMedian() {
		minHeap = new Heap<T>(false);
		maxHeap = new Heap<T>(true);
		count = 0;
	}
	
	public boolean isEmpty() {
		return count == 0;
	}
	
	public void insert(T t) {
		if (count == 0) {
			maxHeap.insert(t);
			count++;
			return;
		}
		if (less(t, maxHeap.getRoot())) {
			maxHeap.insert(t);
		} else {
			minHeap.insert(t);
		}
		
		if (maxHeap.size() > minHeap.size() + 1) {
			minHeap.insert(maxHeap.delRoot());
		}
		
		if (minHeap.size() > maxHeap.size() + 1) {
			maxHeap.insert(minHeap.delRoot());
		}
		count++;
	}
	
	public T getMedian() {
		if (maxHeap.size() >= minHeap.size()) {
			return maxHeap.getRoot();
		} else {
			return minHeap.getRoot();
		}
	}
	
	private boolean less(T a, T b) {
		return a.compareTo(b) < 0;
	}
	
	public static void main(String args[]) {
		DynamicMedian<Integer> median = new DynamicMedian<Integer>();
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		Random random = new Random();
		Integer tmp = null;
		for (int i = 0; i < 9; i++) {
			tmp = new Integer(random.nextInt(100));
			arrayList.add(tmp);
			median.insert(tmp);
		}
		arrayList.sort(null);
		System.out.println(Arrays.toString(arrayList.toArray()));
		System.out.println("median: " + median.getMedian());
	}

}
