import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;
import edu.princeton.cs.algs4.Stack;

public class Solver {
	
	private class Node implements Comparable<Node> {
		private final int moves;
		private final boolean isTwin;
		private final int priority;
		private final Board board;
		private final Node parent;
		
		public Node(Board board, boolean isTwin) {
			this.board = board;
			this.parent = null;
			this.moves = 0;
			this.priority = this.board.manhattan(); 
			this.isTwin = isTwin;
		}
		
		public Node(Board board, Node parent) {
			this.board = board;
			this.parent = parent;
			this.moves = parent.moves + 1;
			this.priority = this.parent.moves + this.board.manhattan(); 
			this.isTwin = parent.isTwin;
		}
		
		public int getMoves() {
			return moves;
		}
		
		public boolean isTwin() {
			return this.isTwin;
		}

		@Override
		public int compareTo(Solver.Node node) {
			return this.priority - node.priority;
		}
	}
	
	private Node current;
	private int moves;
	private final boolean isSolvable;
	private Iterable<Board> solution;
	// find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
    	if (initial == null) {
    		throw new IllegalArgumentException();
    	}
    	MinPQ<Node> minPQ = new MinPQ<Node>();
    	moves = 0;
    	// initail & initail's twin  one of them must be answer. if is twin is solvable, mean initail is unosolvable.
    	Node initialNode = new Node(initial, false);
    	Node twinNode = new Node(initial.twin(), true); 
    	minPQ.insert(initialNode);
    	minPQ.insert(twinNode);
    	Board tmpBoard = null;
    	current = minPQ.delMin();
    	while (!current.board.isGoal()) {
        	Iterator<Board> iter = current.board.neighbors().iterator();
        	while (iter.hasNext()) {
        		tmpBoard = iter.next();
        		if (current.parent == null || !current.parent.board.equals(tmpBoard)) { // filter parent Node
        			minPQ.insert(new Node(tmpBoard, current));
        		}
        	} 
    		current = minPQ.delMin();
    	}
    	isSolvable = !current.isTwin;
    	Stack<Board> stack = new Stack<Board>();
    	Node tmp = this.current;
    	while (tmp != null) {
    		stack.push(tmp.board);
    		tmp = tmp.parent;
    	}
    	this.solution = stack;
    	if (!isSolvable) {
    		moves = -1;
    		solution = null;
    	} else {
    		moves = stack.size() - 1;
    	}
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
    	return isSolvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
    	if (!isSolvable()) {
    		return -1;
    	}
    	return moves;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
    	if (!isSolvable()) {
    		return null;
    	}
    	return solution;
    }

    // test client (see below) 
    public static void main(String[] args) {
    
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
     
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
       
    }

}
