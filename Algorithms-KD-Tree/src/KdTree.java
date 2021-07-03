import java.util.LinkedList;
import java.util.List;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree {
	
	private class KdNode {
		private KdNode leftNode;
		private KdNode rightNode;
		private final boolean isVertical;
		private final Point2D point;
		
		public KdNode(Point2D point, KdNode leftNode, KdNode rightNode, boolean isVertical) {
			this.point = point;
			this.leftNode = leftNode;
			this.rightNode = rightNode;
			this.isVertical = isVertical;
		}
		
		public double getX() {
			return point.x();
		}
		
		public double getY() {
			return point.y();
		}
		
		public boolean isVertical() {
			return isVertical;
		}
		
		public void setLeftNode(KdNode node) {
			leftNode = node;
		}
		
		public void setRightNode(KdNode node) {
			rightNode = node;
		}
	}
	private KdNode root;
	private int count;
	private final RectHV container;
	// construct an empty set of points 
    public KdTree() {
    	root = null;
    	count = 0;
    	container = new RectHV(0, 0, 1, 1);
    }
    
    // is the set empty? 
	public boolean isEmpty() {
		return count == 0;
	}
	
	// number of points in the set 
	public int size() {
		return count;
	}
	
	// add the point to the set (if it is not already in the set)
	public void insert(Point2D p) {
		if (p == null) {
			throw new IllegalArgumentException();
		}
		this.root = insert(p, root, true);
	}
	
	private KdNode insert(Point2D p, KdNode node, boolean isVertical) {
		if (node == null) { // add new Node
			count++;
			return new KdNode(p, null, null, isVertical);
		}
		
		if (p.x() == node.getX() && p.y() == node.getY()) { // filte same point
			return node;
		}
		
		if ((p.x() < node.getX() && node.isVertical()) || 
				(p.y() < node.getY() && !node.isVertical())) { // go leftside: 1.vertical and p.x < node.x 2. not vertical and p.y < node.y
			node.setLeftNode(insert(p, node.leftNode, !node.isVertical()));
		} else {
			node.setRightNode(insert(p, node.rightNode, !node.isVertical()));
		}
		return node;
	}
	
	// does the set contain point p? 
	public boolean contains(Point2D p) {
		if (p == null) {
			throw new IllegalArgumentException();
		}
		return contains(p, root);
	}
	
	private boolean contains(Point2D p, KdNode node) {
		if (node == null) { // not find
			return false;
		}
		
		if (p.x() == node.getX() && p.y() == node.getY()) { // same to node
			return true;
		}
		
		if ((p.x() < node.getX() && node.isVertical()) || 
				(p.y() < node.getY() && !node.isVertical())) { // go left side
			return contains(p, node.leftNode);
		} else { // go right side
			return contains(p, node.rightNode);
		}
	}
	
	// draw all points to standard draw 
	public void draw() {  
		StdDraw.setScale(0, 1);  
  
        StdDraw.setPenColor(StdDraw.BLACK);  
        StdDraw.setPenRadius();  
        container.draw();  
        draw(root, container);  
    }  
	
	private void draw(final KdNode node, final RectHV rect) {  
		if (node == null) {  
            return;  
        }  
  
        // draw the point  
        StdDraw.setPenColor(StdDraw.BLACK);  
        StdDraw.setPenRadius(0.01);  
        new Point2D(node.getX(), node.getY()).draw();  
  
        // get the min and max points of division line  
        Point2D min, max;  
        if (node.isVertical) {  
            StdDraw.setPenColor(StdDraw.RED);  
            min = new Point2D(node.getX(), rect.ymin());  
            max = new Point2D(node.getY(), rect.ymax());  
        } else {  
            StdDraw.setPenColor(StdDraw.BLUE);  
            min = new Point2D(rect.xmin(), node.getY());  
            max = new Point2D(rect.xmax(), node.getY());  
        }  
  
        // draw that division line  
        StdDraw.setPenRadius();  
        min.drawTo(max);  
  
        // recursively draw children  
        draw(node.leftNode, leftRect(rect, node));  
        draw(node.rightNode, rightRect(rect, node));  
	} 
	
	private RectHV leftRect(final RectHV rect, final KdNode node) {  
		if (node == null) {
			return null;
		}
		if (node.isVertical()) {  
			return new RectHV(rect.xmin(), rect.ymin(), node.getX(), rect.ymax());  
		} else {  
			return new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), node.getY());  
		}  
	}  
 
	private RectHV rightRect(final RectHV rect, final KdNode node) {  
		if (node == null) {
			return null;
		}
		if (node.isVertical()) {  
			return new RectHV(node.getX(), rect.ymin(), rect.xmax(), rect.ymax());  
		} else {  
			return new RectHV(rect.xmin(), node.getY(), rect.xmax(), rect.ymax());  
		}  
	}

 	// all points that are inside the rectangle (or on the boundary) 
	public Iterable<Point2D> range(RectHV rect) {
		if (rect == null) {
			throw new IllegalArgumentException();
		}
		List<Point2D> rangeList = new LinkedList<Point2D>();
		range(root, container, rect, rangeList);
		return rangeList;
	}
	
	private void range(KdNode node, RectHV nodeRect,  RectHV rect, List<Point2D> rangeList) {
		if (node == null) {
			return;
		}		
		if (rect.intersects(nodeRect)) {
			Point2D point = new Point2D(node.getX(), node.getY());
			if (rect.contains(point)) {
				rangeList.add(point);
			}
			range(node.leftNode, leftRect(nodeRect, node), rect, rangeList);
			range(node.rightNode, rightRect(nodeRect, node), rect, rangeList);
		}
	}
	

	// a nearest neighbor in the set to point p; null if the set is empty 
	public Point2D nearest(Point2D p) {
		if (p == null) {
			throw new IllegalArgumentException();
		}
		return nearest(root, container, p, null);
	}
	
	private Point2D nearest(KdNode node, RectHV rect, Point2D p, Point2D candidate) {
		if (node == null) {
			return candidate;
		}
		double distanceToCandidate = 0.0;  
        double distanceToRect = 0.0;  
        RectHV left = null;  
        RectHV rigt = null;  
  
        if (candidate != null) {  
        	distanceToCandidate = p.distanceSquaredTo(candidate);  
        	distanceToRect = rect.distanceSquaredTo(p);  
        }  
  
        if (candidate == null || distanceToCandidate > distanceToRect) {  
            final Point2D point = new Point2D(node.getX(), node.getY());  
            if (candidate == null || distanceToCandidate > p.distanceSquaredTo(point)) { // candidate is null or point to node's point distance less than point to candidate
            	candidate = point;  
            }
  
            if (node.isVertical) {  
                left = new RectHV(rect.xmin(), rect.ymin(), node.getX(), rect.ymax());  
                rigt = new RectHV(node.getX(), rect.ymin(), rect.xmax(), rect.ymax());  
  
                if (p.x() < node.getX()) {  
                	candidate = nearest(node.leftNode, left, p, candidate);  
                	candidate = nearest(node.rightNode, rigt, p, candidate);  
                } else {  
                	candidate = nearest(node.rightNode, rigt, p, candidate);  
                	candidate = nearest(node.leftNode, left, p, candidate);  
                }  
            } else {  
                left = new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), node.getY());  
                rigt = new RectHV(rect.xmin(), node.getY(), rect.xmax(), rect.ymax());  
  
                if (p.y() < node.getY()) {  
                	candidate = nearest(node.leftNode, left, p, candidate);  
                	candidate = nearest(node.rightNode, rigt, p, candidate);  
                } else {  
                	candidate = nearest(node.rightNode, rigt, p, candidate);  
                	candidate = nearest(node.leftNode, left, p, candidate);  
                }  
            }  
        }  
 
        return candidate; 
	}

	
	// unit testing of the methods (optional) 
	public static void main(String[] args) {
		/*
		String filename = args[0];
        In in = new In(filename);


        StdDraw.show(0);

        // initialize the data structures with N points from standard input
        PointSET brute = new PointSET();
        KdTree kdtree = new KdTree();
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            kdtree.insert(p);
            brute.insert(p);
        }

        double x0 = 0.0, y0 = 0.0;      // initial endpoint of rectangle
        double x1 = 0.0, y1 = 0.0;      // current location of mouse
        boolean isDragging = false;     // is the user dragging a rectangle

        // draw the points
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(.01);
        brute.draw();

        while (true) {
            StdDraw.show(40);

            // user starts to drag a rectangle
            if (StdDraw.mousePressed() && !isDragging) {
                x0 = StdDraw.mouseX();
                y0 = StdDraw.mouseY();
                isDragging = true;
                continue;
            }

            // user is dragging a rectangle
            else if (StdDraw.mousePressed() && isDragging) {
                x1 = StdDraw.mouseX();
                y1 = StdDraw.mouseY();
                continue;
            }

            // mouse no longer pressed
            else if (!StdDraw.mousePressed() && isDragging) {
                isDragging = false;
            }


            RectHV rect = new RectHV(Math.min(x0, x1), Math.min(y0, y1),
                                     Math.max(x0, x1), Math.max(y0, y1));
            // draw the points
            StdDraw.clear();
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(.01);
            brute.draw();

            // draw the rectangle
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius();
            rect.draw();

            // draw the range search results for brute-force data structure in red
            StdDraw.setPenRadius(.03);
            StdDraw.setPenColor(StdDraw.RED);
            for (Point2D p : brute.range(rect))
                p.draw();

            // draw the range search results for kd-tree in blue
            StdDraw.setPenRadius(.02);
            StdDraw.setPenColor(StdDraw.BLUE);
            for (Point2D p : kdtree.range(rect))
                p.draw();

            StdDraw.show(40);
        }
        */
	}
}
