
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private final WeightedQuickUnionUF sites;
    private final WeightedQuickUnionUF sitesBackwash; // without virtual bottom

	private final boolean[] sitesState; // false is closed, ture is open
	private final int gridLength;
	private final int columnNumber;
	private final int virtualTop;
	private int openCount;
	
	// creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
    	if (n <= 0) {
    		throw new IllegalArgumentException();
    	}
    	this.gridLength = n;
    	this.columnNumber = (n * n) + 2; // add point on top and bottom to simplify problem
    	this.virtualTop = (n * n);
    	this.sites = new WeightedQuickUnionUF(this.columnNumber);
    	this.sitesBackwash = new WeightedQuickUnionUF(this.columnNumber - 1);
    	this.sitesState = new boolean[this.columnNumber];
    	this.openCount = 0;
    	// initailize array
    	for (int i = 0; i < this.columnNumber; i++) {
    		this.sitesState[i] = false;
    	}
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
    	validateRowAndCol(row, col);
    	int self = rowColToColumnNumber(row, col);
    	this.sitesState[self] = true;
    	this.openCount++;
    	if (row == 1) { // first row
    		this.sites.union(self, this.virtualTop);
    		this.sitesBackwash.union(self, this.virtualTop);
    	}
    	
    	if (row == this.gridLength) { // last row
    		this.sites.union(self, this.virtualTop + 1);
    	}
    	
    	// top
    	if (row > 1) {
        	if (isOpen(row - 1, col)) {
            	int topColumnNumber = rowColToColumnNumber(row - 1, col);
        		this.sites.union(self, topColumnNumber);
        		this.sitesBackwash.union(self, topColumnNumber);
        	}
    	}
    	
    	// bottom
    	if (row < this.gridLength) {
    		if (isOpen(row + 1, col)) {
            	int bottomColumnNumber = rowColToColumnNumber(row + 1, col);
        		this.sites.union(self, bottomColumnNumber);
        		this.sitesBackwash.union(self, bottomColumnNumber);
    		}
    	}
    	
    	// left
    	if (col > 1) {
    	   	if (isOpen(row, col -1)) {
            	int leftColumnNumber = rowColToColumnNumber(row, col - 1);
        		this.sites.union(self, leftColumnNumber);
        		this.sitesBackwash.union(self, leftColumnNumber);
        	}
    	}
 
    	// right
    	if (col < this.gridLength) {
           	if (isOpen(row, col + 1)) {
            	int rightColumnNumber = rowColToColumnNumber(row, col + 1);
        		this.sites.union(self, rightColumnNumber);
        		this.sitesBackwash.union(self, rightColumnNumber);
        	}
    	}
    	
    }
    
    private int rowColToColumnNumber(int row, int col) {
    	if (!validateRowAndCol(row, col)) {
    		throw new IllegalArgumentException("row or col argument illegal");
    	}
    	return (row - 1) * this.gridLength + col -1;
    	
    }
    
    private boolean validateRowAndCol(int row, int col) {
    	if (row  <= 0 || row > this.gridLength) {
    		throw new IllegalArgumentException("row index out of bounds");
    	}
    	
    	if (col <= 0 || col > this.gridLength) {
    		throw new IllegalArgumentException("col index out of bounds");
    	}
    	return true;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
    	return this.sitesState[rowColToColumnNumber(row, col)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
    	return this.sitesBackwash.find(rowColToColumnNumber(row, col)) == this.sitesBackwash.find(virtualTop);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
    	return this.openCount;
    }

    // does the system percolate?
    public boolean percolates() {
    	
    	return this.sites.find(this.virtualTop) == this.sites.find(this.virtualTop + 1);
    }

    // test client (optional)
    public static void main(String[] args) {
        int n = StdIn.readInt();
        Percolation percolation = new Percolation(n);
        while (!StdIn.isEmpty()) {
            int row = StdIn.readInt();
            int col = StdIn.readInt();
            percolation.open(row, col);
        }
        StdOut.println("percolation is " + percolation.percolates());
        
    }

}
