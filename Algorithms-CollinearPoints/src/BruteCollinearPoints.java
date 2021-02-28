import java.util.Arrays;

public class BruteCollinearPoints {
	private int segmentNumber;
	private LineSegment[] segments;
	public BruteCollinearPoints(Point[] points) {
		// finds all line segments containing 4 points
		validate(points);

		int pointNum = points.length;
		Point[] tmpPoints = Arrays.copyOf(points, pointNum);		
		segmentNumber = 0;
		segments = new LineSegment[pointNum];
		Arrays.sort(tmpPoints);
		
		for (int i = 0; i < pointNum; i++) {
			for (int j = i + 1; j < pointNum; j++) {
				for (int k = j + 1; k < pointNum; k++) {
					for (int m = k + 1; m < pointNum; m++) {
						if ((Double.compare(tmpPoints[i].slopeTo(tmpPoints[j]), tmpPoints[j].slopeTo(tmpPoints[k])) == 0)
							&& (Double.compare(tmpPoints[j].slopeTo(tmpPoints[k]), tmpPoints[k].slopeTo(tmpPoints[m])) == 0)) {
							segments[segmentNumber++] = new LineSegment(tmpPoints[i], tmpPoints[m]);
						}
						
						
					}
				}
			}
		}
		segments = Arrays.copyOf(segments, segmentNumber);
	}
	
	public int numberOfSegments() {
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
}
