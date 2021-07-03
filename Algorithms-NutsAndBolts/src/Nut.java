
public class Nut implements Comparable<Nut>{
	private int size;
	public Nut(int size) {
		this.size = size;
	}
	
	public int getSize() {
		return size;
	}
	
	public int matchBolt(Bolt bolt) {
		return size - bolt.getSize();
	}

	@Override
	public int compareTo(Nut nut) {
		return size - nut.size;
	}
	
	@Override
	public String toString() {
		return size + "";
	}

}
