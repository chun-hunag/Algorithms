
import java.util.ArrayList;
import java.util.Arrays;
 
 
public class FastCollinearPoints {
    private final ArrayList<LineSegment> lineSegments;
 
    /**
     * Finds all line segments containing 4 pointsCopy or more pointsCopy.
     *
     * @throws IllegalArgumentException if the argument to the constructor is null if any
     *                                  point in the array is null, or if the argument to
     *                                  the constructor contains a repeated point.
     */
    public FastCollinearPoints(Point[] points) {
    	validate(points);
        Point[] pointsCopy = Arrays.copyOf(points, points.length);
        Arrays.sort(pointsCopy);
        lineSegments = new ArrayList<>();
 
        for (int i = 0; i < pointsCopy.length - 3; i++) {
            Point startPoint = pointsCopy[i];
            double[] preSlopes = new double[i];
            Point[] nextPoints = new Point[pointsCopy.length - i - 1];
 
            for (int j = 0; j < i; j++) {
                preSlopes[j] = startPoint.slopeTo(pointsCopy[j]);
            }
 
            for (int j = 0; j < pointsCopy.length - i - 1; j++) {
                nextPoints[j] = pointsCopy[i + j + 1];
            }
            //for binary search
            Arrays.sort(preSlopes);
            // sort after point by slope
            Arrays.sort(nextPoints, startPoint.slopeOrder());
            findLineSegments(preSlopes, startPoint, nextPoints);
        }
    }
 
    private void findLineSegments(double[] preSlopes, Point startPoint, Point[] nextPoints) {
        double currentSlope;
        double beforeSlope = Double.NEGATIVE_INFINITY;
        int countRepeat = 1;
 
        for (int i = 0; i < nextPoints.length; i++) {
            currentSlope = startPoint.slopeTo(nextPoints[i]);
            if (beforeSlope != currentSlope) {
                //beforeSlope != currentSlope and countRepeat>= 3
                if (countRepeat >= 3 && !isSubLine(beforeSlope, preSlopes)) {
                    lineSegments.add(new LineSegment(startPoint, nextPoints[i - 1]));
                }
                countRepeat = 1;
            } else {
                countRepeat++;
            }
            beforeSlope = currentSlope;
        }
        //record the rest of the situation.
        if (countRepeat >= 3 && !isSubLine(beforeSlope, preSlopes)) {
            lineSegments.add(new LineSegment(startPoint, nextPoints[nextPoints.length - 1]));
        }
    }
 
    private void validate(Point[] points) {
        if (points == null) {
            throw new java.lang.IllegalArgumentException();
        }
 
        for (Point point : points) {
            if (point == null) {
                throw new IllegalArgumentException();
            }
        }
 
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException();
                }
            }
        }
    }
 
    private boolean isSubLine(double tempSlope, double[] beforeSlope) {
        int lo = 0;
        int hi = beforeSlope.length - 1;
        // use binary search
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (tempSlope < beforeSlope[mid]) {
                hi = mid - 1;
            } else if (tempSlope > beforeSlope[mid]) {
                lo = mid + 1;
            } else {
                return true;
            }
        }
        return false;
    }
 
    /**
     * Returns the number of line segments.
     *
     * @return the number of line segments.
     */
    public int numberOfSegments() {
        return lineSegments.size();
    }
 
    /**
     * Returns a LineSegment array.
     *
     * @return a LineSegment array.
     */
    public LineSegment[] segments() {
        return lineSegments.toArray(new LineSegment[numberOfSegments()]);
    }
}

/*
public class FastCollinearPoints {
	private int segmentNumber;
	private LineSegment[] segments;
   public FastCollinearPoints(Point[] points) {
	   // finds all line segments containing 4 or more points
		if (points == null) {
			throw new IllegalArgumentException();
		}

		int pointNum = points.length;
		Point[] copy1 = new Point[pointNum];
		Point[] copy2 = new Point[pointNum];
		segments = new LineSegment[pointNum * pointNum];
        copy(points,copy1);
		Arrays.sort(copy1);
        copy(copy1,copy2);
        
		isDuplicate(copy1);
		
		for (int i = 0; i < pointNum; i++) {
			Arrays.sort(copy2, copy1[i].slopeOrder());
			Point min = copy2[0];
			Point max = copy2[0];
			int count = 1;
			for (int j = 1; j < pointNum; j++) {
				if (Double.compare(copy1[i].slopeTo(copy2[j]), copy1[i].slopeTo(copy2[j - 1])) == 0) { 
					
					if (copy2[j].compareTo(min) < 0) {
						min = copy2[j];
					} else if (copy2[j].compareTo(max) > 0) {
						max = copy2[j];
					}
					
					count++;
					if ((j == (pointNum - 1)) && (count >= 3) && (min.compareTo(copy1[i]) > 0)) {
						segments[segmentNumber++] = new LineSegment(copy1[i], max);
					}
				}
				if ((count >= 3) && (min.compareTo(copy1[i]) > 0)) {
					segments[segmentNumber++] = new LineSegment(copy1[i], max);
				}
				// because point is order by slope, if slope not correspond to first condition continuously, it mean not this slope.
				// so need to reset min point and max point
				min = copy2[j];
				max = copy2[j];
				count = 1;
			}
		}
		segments = Arrays.copyOf(segments, segmentNumber);
   }
   public int numberOfSegments() {
	   // the number of line segments
	   return segmentNumber;
   }
   public LineSegment[] segments() {
	   // the line segments
		LineSegment[] copySegments = new LineSegment[segmentNumber];
		for (int i = 0; i < segmentNumber; i++) {
			copySegments[i] = segments[i];
		}
		return copySegments;
   }
   
   private boolean isDuplicate(Point[] elements) {
	   for (int i = 0; i < segmentNumber - 1; i++) {
		   for (int j = i + 1; j < segmentNumber; j++) {
			   if (elements[i].compareTo(elements[j]) == 0) {
                   throw new IllegalArgumentException();
			   }
		   }
	   }
	   return false;
   }
   
   private void copy(Point[] origin, Point[] newarray)
   {
       int size = origin.length;
       for(int i = 0 ; i < size ; i++) 
       {
           if (origin[i] == null) {
               throw new IllegalArgumentException();
           }
           newarray[i] = origin[i];
       }
   }
   
   public static void main(String args[]) {
       // read the n points from a file
       In in = new In(args[0]);
       int n = in.readInt();
       Point[] points = new Point[n];
       for (int i = 0; i < n; i++) {
           int x = in.readInt();
           int y = in.readInt();
           points[i] = new Point(x, y);
       }

       // draw the points
       StdDraw.enableDoubleBuffering();
       StdDraw.setXscale(0, 32768);
       StdDraw.setYscale(0, 32768);
       for (Point p : points) {
           p.draw();
       }
       StdDraw.show();

       // print and draw the line segments
       FastCollinearPoints collinear = new FastCollinearPoints(points);
       for (LineSegment segment : collinear.segments()) {
           StdOut.println(segment);
           segment.draw();
       }
       StdDraw.show();
   }
}
*/