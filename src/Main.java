import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String datafile = "1D2R1V.txt";
		//ArrayList<PickupNode> pickupnodes = new ArrayList<PickupNode>();
		//ArrayList<DeliveryNode> deliverynodes = new ArrayList<DeliveryNode>();
		ArrayList<Node> nodes = new ArrayList<Node>();
		ArrayList<Node> depot = new ArrayList<Node>();
		ArrayList<Node> pickupNodes = new ArrayList<Node>();
		ArrayList<Node> deliveryNodes = new ArrayList<Node>();
		
		InstanceData inputdata = new InstanceData(datafile);

		InputReader.inputReader(datafile, nodes, inputdata, depot, pickupNodes, deliveryNodes) ;
		
		System.out.println(nodes.get(3).number);
		System.out.println(nodes.get(3).earlyTimeWindow);
		System.out.println(nodes.get(3).lateTimeWindow);
		System.out.println(nodes.get(3).type);
		System.out.println(nodes.get(3).location);
		System.out.println(nodes.get(3).weight);
		System.out.println(nodes.get(3).volume);
		System.out.println(nodes.get(3).locationName);
		InstanceData.getDistance(nodes.get(3), nodes.get(4), inputdata);

	}

}
