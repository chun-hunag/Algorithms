
public class Bolt implements Comparable<Bolt>{
	private int size;
	public Bolt(int size) {
		this.size = size;
	}
	
	public int getSize() {
		return size;
	}
	
	public int match(Nut nut) {
		return size - nut.getSize(); 
	}

	@Override
	public int compareTo(Bolt bolt) {
		return size - bolt.size;
	}
	
	@Override
	public String toString() {
		return size + "";
	}
}
