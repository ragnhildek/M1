import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;

public class PathBuilder {
	
	public static ArrayList<Hashtable<Integer, Boolean>> feasibilityTest; 
	
	public static void PathBuilder(ArrayList<Node> pickupNodes, ArrayList<Node> deliveryNodes, ArrayList<Node> nodes, ArrayList<Node> depot, InstanceData inputdata) {
		feasibilityTest = new ArrayList<Hashtable<Integer, Boolean>>();
		
		System.out.println(nodes.get(2).locationName);
	}
	
	public Label LabelExtension(Node node, Label L, InstanceData inputdata, ArrayList<Node> pickupNodes, ArrayList<Node> deliveryNodes, ArrayList<Node> nodes, ArrayList<Node> depot) {
		if(L.path.contains(node.number)) {
			return null;
		}
		Label L2 = new Label();
		L2.node = node;
		L2.predesessor = L;
		L2.time = Math.max(L.time+InstanceData.getTime(L.node, node, inputdata)+node.weight*inputdata.timeTonService, node.earlyTimeWindow); 
		if(L2.time > node.lateTimeWindow){
			return null;
		}
		if(node.type=="PickupNode") {
			if(L.weightCapacityUsed + node.weight <= inputdata.weightCap){
			L2.weightCapacityUsed = L.weightCapacityUsed + node.weight;
			}
			else{
				return null;
			}
		}
		if(node.type == "DeliveryNode") {
			L2.weightCapacityUsed = L.weightCapacityUsed - node.weight;
		}
		if(node.type=="PickupNode") {
			if(L.volumeCapacityUsed + node.volume <= inputdata.volumeCap){
			L2.volumeCapacityUsed = L.volumeCapacityUsed + node.volume;
			}
			else{
				return null;
			}
		}
		if(node.type == "DeliveryNode") {
			L2.volumeCapacityUsed = L.volumeCapacityUsed - node.volume;
		}
		if(node.type == "PickupNode") {
			L2.profit = L.profit + (inputdata.revenue * node.weight * inputdata.getDistance(node, node, inputdata));
		}
		
		
		
		//int demand = 0;
		for(int i = 0; i < inputdata.nrFuelTypes; i++) {
			demand+=customers.get(node).demand[i];
//			if(customers.get(node).demand[i]>=1) {
//				L2.time+= (int) customers.get(node).demand[i]/L.vessel.pumpingRate+1;
//			}
		}
		L2.time+= demand/L.vessel.pumpingRate+1;
		
		//System.out.println("service time at node "+node+" is "+(demand/L.vessel.pumpingRate+1));
		
		if(customers.get(node).TWL<L2.time+1) {
			return null;
		}
		else if(L2.time>inputdata.nrTimePeriods) {
			return null;
		}
		
		L2.cost = L.cost+L.vessel.sailingCost*inputdata.ordinarySailingTime;
//		if(L.time%24 <L2.time%24) {
//			L2.cost+=L.vessel.fixedCost;
//		}
		L2.VisitedNodes = new Vector<Integer>();
		for(int i : L.VisitedNodes) {
			L2.VisitedNodes.add(i);
		}
		L2.VisitedNodes.add(node);
		
		if(!checkStowageFeasibility(L2)) {
			return null;
		}
		return L2;
	}
	
	
	

}
