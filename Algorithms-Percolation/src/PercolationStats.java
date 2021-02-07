import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] trailsResult;
    private double resultMean;
    private double resultStddev;
    private double resultconfidenceLo;
    private double resultconfidenceHi;
    
    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
    	if (n <= 0 || trials <= 0) {
    		throw new IllegalArgumentException();
    	}
    	
		int gridLength = n;
	    if (gridLength == 1) {
	        resultMean = 1;
	        resultStddev = Double.NaN;
	        resultconfidenceLo = Double.NaN;
	        resultconfidenceHi = Double.NaN;
	    } else {
	        trailsResult = new double[trials];
	        for (int i = 0; i < trials; i++) {
	            trailsResult[i] = oneTrial(gridLength);
	        }
	        resultMean = StdStats.mean(trailsResult);
	        resultStddev = StdStats.stddev(trailsResult);
	        double diff = (1.96 * resultStddev) / Math.sqrt(trials);
	        resultconfidenceLo = resultMean - diff;
	        resultconfidenceHi = resultMean + diff;
	    }
    }
    
    private double oneTrial(int length) {
        int openedCount = 0;
        Percolation percolation = new Percolation(length);
        while (!percolation.percolates()) {
            int row = StdRandom.uniform(length) + 1;
            int col = StdRandom.uniform(length) + 1;
            if (!percolation.isOpen(row, col)) {
                percolation.open(row, col);
                openedCount++;
            }
        }
        return (double) openedCount / (length * length);
    }

    // sample mean of percolation threshold
    public double mean() {
        return resultMean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return resultStddev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return resultconfidenceLo;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return resultconfidenceHi;
    }

   // test client (see below)
   public static void main(String[] args) {
       int length = Integer.parseInt(args[0]);
       int trials = Integer.parseInt(args[1]);
       PercolationStats percolations = new PercolationStats(length, trials);
       StdOut.println("mean                    = " + percolations.mean());
       StdOut.println("stddev                  = " + percolations.stddev());
       StdOut.println("95% confidence interval = "
                          + percolations.confidenceLo() + ", "
                          + percolations.confidenceHi());
   }
}
