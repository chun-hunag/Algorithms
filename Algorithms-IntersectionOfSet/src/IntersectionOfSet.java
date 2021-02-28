import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

public class IntersectionOfSet {
	private Set<Point> set;
	private int sameCount;
	public IntersectionOfSet(Point[] points1, Point[] points2) {
		int size1 = points1.length;
		int size2 = points2.length;
		int size = (size1 > size2) ? size1 : size2;
		set = new HashSet<Point>();
		for (int i = 0; i < size; i++) {
			if (i < size1) {
				set.add(points1[i]);
			}
			
			if (i < size2) {
				set.add(points2[i]);
			}
		}
		sameCount = (size1 + size2) - set.size();
	}
	
	public int getSameCount() {
		return sameCount;
	}
	
	public static void main(String args[]) {
		Point[] points1 = new Point[100];
		Point[] points2 = new Point[50];
		for (int i = 0; i < 100; i++) {
			points1[i] = new Point(rand(-20, 20), rand(-20, 20));
		}
		for (int i = 0; i < 50; i++) {
			points2[i] = new Point(rand(-20, 20), rand(-20, 20));
		}
		IntersectionOfSet set = new IntersectionOfSet(points1, points2);
		System.out.println("Same point: " + set.getSameCount());
		
	}
	
	public static int rand(int start, int end) {
		return (int) (Math.random() * (end - start) + start);
	}
}
