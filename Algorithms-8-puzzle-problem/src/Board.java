import java.util.ArrayList;
import java.util.Arrays;

public class Board {
	private int[][] tiles;
	private final int n;
	private final int hamming;
	private final int manhattan;
    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
    	n = tiles.length;

    	this.tiles = new int[n][n];
    	for (int i = 0; i < n; i++) {
    		for (int j = 0; j < n; j++) {
    			this.tiles[i][j] = tiles[i][j];
    		}
    	}
    	
    	int hammingSum = 0;
    	int manhattanSum = 0;
    	for (int i = 0; i < n; i++) {
    		for (int j = 0; j < n; j++) {
    			
    			int targetAt = tiles[i][j] - 1; // taget place
    			int nowAt = i * n + j;
    			if (tiles[i][j] != 0) {
    			
    				if (targetAt != nowAt) {
    					hammingSum++;
    				}
        			manhattanSum += (Math.abs((targetAt % n) - j) + Math.abs((targetAt / n) - i));  
    			}
    		}
    	}
    	hamming = hammingSum;
    	manhattan = manhattanSum;
    }
                                           
    // string representation of this board
    public String toString() {
    	StringBuilder strBuilder = new StringBuilder();
    	strBuilder.append(n).append("\n");
    	for (int i = 0; i < n; i++) {
    		for (int j = 0; j < n; j++) {
    			if (tiles[i][j] < 10) {
    				strBuilder.append(" ");
    			}
    			strBuilder.append(tiles[i][j]).append(" ");
    		}
    		strBuilder.append("\n");
    	}
    	return strBuilder.toString();
    }

    // board dimension n
    public int dimension() {
    	return n;
    }

    // number of tiles out of place
    public int hamming() {
    	return hamming;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
    	return manhattan;
    }

    // is this board the goal board?
    public boolean isGoal() {
    	return hamming() == 0;
    }

    /** does this board equal y?
	 *  Two boards are equal if they are have the same size and their corresponding tiles are in the same positions. 
	 *  The equals() method is inherited from java.lang.Object, so it must obey all of Java's requirements.
	 **/
    public boolean equals(Object o) {
    	if (o == null) {
    		return false;
    	}
    	if (o.getClass() != this.getClass()) {
    		return false;
    	}
    	if (this == o) {
    		return true;
    	}
    	Board comparedBoard = (Board) o;
    	return Arrays.deepEquals(this.tiles, comparedBoard.tiles);
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
    	ArrayList<Board> list = new ArrayList<Board>();
    	for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (tiles[i][j] == 0) {
					checkNeighbor(list, i, j);
				}
			}
		}
    	return list;
    }
    private boolean isInBoard(int row, int col) {
		if (row < 0 || row >= n || col < 0 || col >= n) {
			return false;
		}
		return true;
	}
	
	private void checkNeighbor(ArrayList<Board> list, int row, int col) {
		int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
		int tmpRow = row;
		int tmpCol = col;
		for (int[] direction : directions) {
			tmpRow = row + direction[0];
			tmpCol = col + direction[1];
			if (isInBoard(tmpRow, tmpCol)) {
				list.add(new Board(swap(row, col, tmpRow, tmpCol)));
			}
		}
	}
	
	private int[][] swap(int row1, int col1, int row2, int col2) {
		int[][] tmpArray = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				tmpArray[i][j] = tiles[i][j];
			}
		}
		
		int tmp = tmpArray[row1][col1];
		tmpArray[row1][col1] = tmpArray[row2][col2];
		tmpArray[row2][col2] = tmp;
		return tmpArray;
		
	}
    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
    	for (int i = 0; i < n * n - 1; i++) {
    		int row = i / n;
    		int col = i % n;
    		int twinRow = (i + 1) / n;
    		int twinCol = (i + 1) % n;
			if (tiles[row][col] != 0 && tiles[twinRow][twinCol] != 0) {
				Board tmp = new Board(swap(row, col, twinRow, twinCol));
				return tmp;
			}
    	}
    	return null;
    }

    // unit testing (not graded)
    public static void main(String[] args) {
    	int[][] array = {
    						{4, 5, 8},
    						{2, 1, 3},
    						{7, 6, 0}
    					};
    	Board border = new Board(array);
    	System.out.println(border);
        for (Board bb : border.neighbors()) {
            System.out.println(bb);
        }
    }

}