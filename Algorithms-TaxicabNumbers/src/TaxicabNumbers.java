import java.util.HashMap;
import java.util.function.BiConsumer;

/**
 * A taxicab number is an integer that can be expressed as the sum of two cubes of positive integers 
 * in two different ways: $a^3 + b^3 = c^3 + d^3$. 
 * For example, $1729$ is the smallest taxicab number: $9^3 + 10^3 = 1^3 + 12^3$.
 * Design an algorithm to find all taxicab numbers with $a$, $b$, $c$, and $d$ less than $n$.
 *-Version 1: Use time proportional to $*n^2logn*$ and space proportional to $*n^2*$.
-* Version 2: Use time proportional to $n^2logn$ and space proportional to $n$ .
 * @author ChunHuang
 *
 */
public class TaxicabNumbers {
	public static HashMap<Integer, Integer> findTaxicabNumber(int n) {
		
		HashMap<Integer, Integer> taxicalNumberMap = new HashMap<Integer, Integer>();
		int maxTaxicalNumber = (int) Math.pow(n, 1.0/3); 
		System.out.println("max taxical number: " + maxTaxicalNumber);
		for (int i = 1; i <= maxTaxicalNumber; i++) { // check n^{1/3} time
			for (int j = i + 1; j <= maxTaxicalNumber; j++) {
				System.out.println("check: " + i + ", " + j);
				if (isTaxicalNumber(n, i, j)) {
					taxicalNumberMap.put(i, j);
				}
			}
		}
		return taxicalNumberMap;
	}
	
	private static boolean isTaxicalNumber(int n, int number1, int number2) {
		if (n == (Math.pow(number1, 3) + (Math.pow(number2, 3)))) {
			return true;
		}
		return false;
	}
	
	public static void main(String args[]) {
		int n = 87539319;
		HashMap<Integer, Integer> taxicalNumberMap = TaxicabNumbers.findTaxicabNumber(n);
		taxicalNumberMap.forEach(new BiConsumer<Integer, Integer>() {

			@Override
			public void accept(Integer taxicalNumber1, Integer vtaxicalNumber2) {
				System.out.println(taxicalNumber1 + ", " + vtaxicalNumber2);
			}
			
		});
	}
}
