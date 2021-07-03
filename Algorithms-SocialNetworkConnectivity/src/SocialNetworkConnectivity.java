import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdOut;
/**
 * Given a social network containing n members and a log file containing mm timestamps
 * at which times pairs of members formed friendships, design an algorithm to determine
 * the earliest time at which all members are connected (i.e., every member is a friend of
 * a friend of a friend ... of a friend). Assume that the log file is sorted by timestamp 
 * and that friendship is an equivalence relation. The running time of your algorithm should
 * be m logn or better and use extra space proportional to n.
 * @author user
 *
 */
public class SocialNetworkConnectivity {
	
	private FileInputStream fileInputStream;
	private WeightedQuickUnionUF weightedUF;
	
	public SocialNetworkConnectivity (int n, FileInputStream fileInputStream) {
		weightedUF = new WeightedQuickUnionUF(n);
		this.fileInputStream = fileInputStream;
	}
	
	public String getEarliestConnectedTime() {
		Scanner scanner = new Scanner(fileInputStream);
		String earliestConnectedTime = null;
		while (scanner.hasNextLine()) {
			String[] splitedString = scanner.nextLine().split(" "); // split by space
			weightedUF.union(Integer.parseInt(splitedString[1]), Integer.parseInt(splitedString[2]));
			if(weightedUF.count() == 1) { // when only one tree left, mean all point has same root. mean all point connected
				earliestConnectedTime = splitedString[0];
				break;
			}
		}

		return earliestConnectedTime;
	}
	
	
	public static void main(String args[]) {
		try {

			File file = new File("socialNetworkLog.txt");
			FileInputStream fileInputStream = new FileInputStream(file);

			SocialNetworkConnectivity socialNetworkConnectivity = new SocialNetworkConnectivity(10, fileInputStream);
			String earliestConnectedTime = socialNetworkConnectivity.getEarliestConnectedTime();
			StdOut.println("Earliest connected time is " + earliestConnectedTime);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
      * socialNetworkLog.txt
      * 20170714001 0 1
      * 20170714002 4 5
      * 20170714003 8 9
      * 20170714004 2 4
      * 20170714005 5 6
      * 20170714006 7 8
      * 20170714007 2 5
      * 20170714008 6 7
      * 20170714009 1 2
      * 20170714010 0 3
      * 20170714011 1 9
      * 20170714012 3 7
      *
	*/
	
}
 