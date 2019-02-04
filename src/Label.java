
import java.util.ArrayList;


	public class Label {
		public float time; 
		public float profit;
		public float weightCapacityUsed;
		public float volumeCapacityUsed;
		public Label predesessor;
		public Node node;
		public ArrayList<Integer> visitedPickupNodes;
		public ArrayList<Integer> openNodes;
		public ArrayList<Integer> path;
}

