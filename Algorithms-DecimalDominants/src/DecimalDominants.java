import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.function.BiConsumer;

/**
 * Given an array with n keys, design an algorithm to find all values that occur more than n/10 times. 
 * The expected running time of your algorithm should be linear.
 * @author ChunHuang
 *
 */
public class DecimalDominants {
	public static Integer[] find(Integer[] array) {
		// find all occur n/10 times element mean, most have n/10 number
		
		int n = array.length;
		HashMap<Integer, Integer> apearMap = new HashMap<Integer, Integer>();
		for (int i = 0; i < n; i++) {
			int x = array[i];
			if (apearMap.containsKey(x)) {
				apearMap.replace(x, apearMap.get(x) + 1);
			} else {
				apearMap.put(x, 1);
			}
		}
		
		ArrayList<Integer> apearList = new ArrayList<Integer>();
		apearMap.forEach(new BiConsumer<Integer, Integer>(){

			@Override
			public void accept(Integer key, Integer value) {
				if (value > 9) {
					apearList.add(key);
				}
				
			}
			
		});
		Integer[] repeatArray = new Integer[apearList.size()];
		
		return apearList.toArray(repeatArray);
	}
	
	public static void main(String args[]) {
		Random random = new Random();
		Integer[] array = new Integer[50];
		for (int i = 0; i < 50; i++) {
			array[i] = new Integer(random.nextInt(10));
		}
		System.out.println(Arrays.toString(array));
		System.out.println(Arrays.toString(DecimalDominants.find(array)));
		
	}
}
  