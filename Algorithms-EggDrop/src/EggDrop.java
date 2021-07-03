/**
 * Egg drop. Suppose that you have an n-story building (with floors 1 through n) and plenty of eggs. 
 * An egg breaks if it is dropped from floor T or higher and does not break otherwise. 
 * Your goal is to devise a strategy to determine the value of T given the following limitations on the number of eggs and tosses:
 * Version 0: 1 egg, ≤ T tosses.
 * Version 1: ∼1lgn eggs and ∼1lgn tosses.
 * Version 2: ∼lgT eggs and ∼2lgT tosses.
 * Version 3: 2 eggs and ∼ 2sqrt{n} tosses.
 * Version 4: 2 eggs and ≤ csqrt{T} tosses for some fixed constant c.
 * @author user
 *
 */
import edu.princeton.cs.algs4.StdOut;
public class EggDrop {
	
	public static final int T = 10;
	public static int tryCount = 0;
	public static int eggBrokenCount = 0;
	
	public static void init() {
		tryCount = 0;
		eggBrokenCount = 0;
	}
	
	public static void printInfo() {
		StdOut.println("try " + tryCount + " time !");
		StdOut.println("broken " + eggBrokenCount + " egg !");
	}
	
	public static boolean drop(int i ) {
		tryCount++;
		if (i < T) {
			StdOut.println("Test drop from floor " + i + " and egg not broken." );
			return false;
		} else {
			StdOut.println("Test drop from floor " + i + " and egg broken." );
			eggBrokenCount++;
			return true;
		}
	}
	/**
	 *  use 1 egg, give T try
	 */
	public static void testVerZero(int n) {
		for (int i = 1; i <= n; i++) {
			if (drop(i)) {
				return;
			}
		}
	}
	/**
	 *  use lg(n) egg, give lg(n) try
	 */
	public static void testVerOne(int n, int start, int end) {
		int mid = (start + end) / 2;
		
		while (start < end) {
			StdOut.println("start: " + start + " end: " + end);
			if (drop(mid)) { // egg drop from mid floor is broken
				end = mid - 1;
			} else {
				start = mid + 1;
			}
			mid = (start + end) / 2;
		}
	}
	/**
	 * 
	 *  use lg(T) egg, give 2lg(T) try
	 */
	public static void testVerTwo(int n) {
		int i = 1;
		int floor = 1;
		while (!drop(floor) && floor <= n) {
			floor = (int) Math.pow(2, (double)i);
			i++;
		}
		testVerOne(n, (int) Math.pow(2, (double) (i - 2)), floor);
	}
	
	/**
	 * 
	 *  use 2 egg, give 2sqrt{n} try
	 *  divde floor to 1 ~ sqrt{n}, sqrt{n} ~ 2 sqrt{n}, ... ,ksqrt{n} ~ (k+1)sqrt{n} interval
	 *  drop egg form first interval.(cost sqrt{2}) if egg broken 1 ~ sqrt{n} interval , drop egg from start to end in interval (cost sqrt{2}).
	 *  totally cost 2sqrt{2}
	 */
	public static void testVerThree(int n) {
		int k = (int) Math.sqrt((double) n);
		int intervalStart = 1;
		// decide which interval's first floor is higher enough to casue egg broken
		for (int i = 0; i * k <= n; i++) {
			if (drop((i * k))) {
				intervalStart = (i - 1) * k;
				break;
			}
		}
		StdOut.println("k: " + k + " intervalStart: " + intervalStart);
		for (int i = intervalStart; i < intervalStart + k; i++) {
			if (drop(i)) {
				StdOut.println("T is " + i);
				break;
			}
		}
	}
	
	/**
	 * 
	 *  use e egg, give 3sqart{T} try
	 *  Step 1 : start drop egg from floor 1, 4, 9 ,16 ... (k-1)^2, k^2. if egg broken when drop from floor k^2 floor, mean T is in (k-1)^2 <= T <= k^2.
	 *  		 this step cost k time ( sqrt{T} time) 
	 *  Step 2 : start drop egg from floor (k-1)^2 to floor k^2 floor by floor.
	 *  		 this step cost k^2 - (k^2 -2k +1) = 2k - 1  = 2 sqrt{T} - 1  about 2sqrt{T} time.
	 *  
	 */
	public static void testVerFour(int n) {
		int k = -1;
		for (int i = 1; i <= n; i++) {
			if (drop((int) Math.pow((double) i, 2))) {
				k = i;
				break;
			}
		}
		int kMinusOnePowerTwo = (int) (Math.pow((double) (k-1), 2));
		int kPowerTwo = (int) (Math.pow((double) k, 2));
		for (int i = kMinusOnePowerTwo; i <= kPowerTwo; i++) {
			if (drop(i)) {
				StdOut.println("T is " + i);
				break;
			}
		}
	}
	
	public static void main(String args[]) {
		// version 0
		StdOut.println("Version 0");
		init();
		testVerZero(40);
		printInfo();

		// version 1
		StdOut.println("Version 1");
		init();
		testVerOne(40, 1, 40);
		printInfo();

		// version 2
		StdOut.println("Version 2");
		init();
		testVerTwo(40);
		printInfo();
		
		// version 3
		StdOut.println("Version 3");
		init();
		testVerThree(40);
		printInfo();
		
		// version 4
		StdOut.println("Version 4");
		init();
		testVerFour(40);
		printInfo();
	}
}
