import java.util.Arrays;
import java.util.Random;

/**
 * A disorganized carpenter has a mixed pile of $n$ nuts and $n$ bolts. 
 * The goal is to find the corresponding pairs of nuts and bolts. 
 * Each nut fits exactly one bolt and each bolt fits exactly one nut. 
 * By fitting a nut and a bolt together, 
 * the carpenter can see which one is bigger (but the carpenter cannot compare two nuts or two bolts directly). 
 * Design an algorithm for the problem that uses at most proportional to $nlogn$ compares (probabilistically).
 * @author ChunHuang
 *
 */
public class NutsAndBolts {

	public static void match(Nut[] nuts, Bolt[] bolts) {
		System.out.println("Before sorted: ");
		System.out.println(Arrays.toString(nuts));
		sort(nuts);
		System.out.println("After sorted: ");
		System.out.println(Arrays.toString(nuts));
		
		System.out.println("Before matched: ");
		System.out.println(Arrays.toString(bolts));
		int index = -1;
		Bolt[] auxBolts = new Bolt[bolts.length];
		for (int i = 0; i < bolts.length; i++) {
			index = binarySearchNut(nuts, bolts[i]);
			System.out.println("searched: " + index);
			if (index == -1) {
				continue;
			}
			auxBolts[index] = bolts[i];
		}
		bolts = auxBolts;
		System.out.println("After matched: ");
		System.out.println(Arrays.toString(bolts));
	}
	
	private static void sort(Nut[] nuts) {
		int head = 0;
		int tail = nuts.length - 1;
		sort(nuts, head, tail);
	}
	
	private static void sort(Nut[] nuts, int head, int tail) {
		if (head >= tail) { // recursive end condition
			return;
		}
		
		int pivot = head;
		int left = head ;
		int right = tail + 1;
		Nut tmp = null;
		while (true) 
		{
			// find element larger than pivot in left side
			while (nuts[++left].compareTo(nuts[pivot]) < 0) 
			{
				if (left == tail) {
					break;
				}
			}
			// find element smaller than pivot in right side
			while (nuts[--right].compareTo(nuts[pivot]) > 0) 
			{
				if (right == head) {
					break;
				}
			}
			// break point
			if (left >= right) {
				break;
			}
			tmp = nuts[left];
			nuts[left] = nuts[right];
			nuts[right] = tmp;			
		}
		// swap pivot and right
		tmp = nuts[pivot];
		nuts[pivot] = nuts[right];
		nuts[right] = tmp;
		sort(nuts, head, right - 1);
		sort(nuts, right + 1, tail);
		return;
	}
	
	private static int binarySearchNut(Nut[] nuts, Bolt bolt) {
		int head = 0;
		int tail = nuts.length - 1;
		int mid = (head + tail) / 2;
		while (head <= tail) {
			mid = (head + tail) / 2;
			if (nuts[mid].matchBolt(bolt) < 0) { // this nut is smaller than bolt, so need to searh right side
				head = mid + 1;
			} else if (nuts[mid].matchBolt(bolt) > 0) { // this nut is bigger than bolt, so need to search left side
				tail= mid - 1;
			} else {// this nut is exactly match bolt
				return mid;
			}
		}
		return -1;
	}
	
	public static void main(String args[]) {
		Nut[] nuts = new Nut[10];
		Bolt[] bolts = new Bolt[10];
		for (int i = 0; i < 10; i++) {
			nuts[i] = new Nut(i + 1);
			bolts[i] = new Bolt(i + 1);
		}
		
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			int nutRandom = random.nextInt(nuts.length);
			int boltRandom = random.nextInt(bolts.length);
			Nut tmpNut = nuts[i];
			nuts[i] = nuts[nutRandom];
			nuts[nutRandom] = tmpNut;
			
			Bolt tmpBolt = bolts[i];
			bolts[i] = bolts[boltRandom];
			bolts[boltRandom] = tmpBolt;
		}
		NutsAndBolts.match(nuts, bolts);
	}
	
}
