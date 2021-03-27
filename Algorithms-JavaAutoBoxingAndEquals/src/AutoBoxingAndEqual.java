/**
 * Consider  two $double$ values $a$ and $b$ and their corresponding $Double$ values $x$ and $y$.
 * Find values such that $(a==b)$ is true but $x.equals(y)$ is $false$.
 * Find values such that $(a==b)$ is false but $x.equals(y)$ is $true$.
 * @author ChunHuang
 *
 */
public class AutoBoxingAndEqual {
	
	public static void main(String args[]) {
		
		Double a = 0.0;
		double b = -0.0;
		System.out.println("(" + a + " == " + b + ") = " + (a == b));
		System.out.println("(" + a + ".equal(" + b +")) = " + (a.equals(b)));
		
		a = Double.NaN;
		b = Double.NaN;
		System.out.println("(" + a + " == " + b + ") = " + (a == b));
		System.out.println("(" + a + ".equal(" + b +")) = " + (a.equals(b)));


	}
}
