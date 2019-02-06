
import java.util.ArrayList;


	public class Label {
		public float time; 
		public float profit;
		public float weightCapacityUsed;
		public float volumeCapacityUsed;
		public Label predesessor;
		public Node node;
		public ArrayList<Integer> path;
		public ArrayList<Integer> unreachablePickupNodes;
		public ArrayList<Integer> openNodes; //pikcupnodes
		

	public String toString() {
		String string = "Time: " + time+", Profit: "+ profit +", Path: ";
		for (int  i : path) {
			string += i;
		}
		Label temp = predesessor;
		
		while(temp!=null) {
			string+=", Predessesor: "+temp.node;
			temp=temp.predesessor;
		}
		
		System.out.println(string);
		return string;
	}
}
