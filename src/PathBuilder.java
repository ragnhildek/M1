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
		L2.path.add(node.number);		
		
		if(node.type == "PickupNode"){
			L2.unreachablePickupNodes.add(node.number);
			L2.openNodes.add(node.number);
			for (int i=0 ; i < pickupNodes.size(); i++){
				if (!L2.path.contains(pickupNodes.get(i).number)){
					if (L2.time + InstanceData.getTime(node, pickupNodes.get(i), inputdata) + node.weight *inputdata.timeTonService > pickupNodes.get(i).lateTimeWindow){
						L2.unreachablePickupNodes.add(pickupNodes.get(i).number);
					}
				}
			}
		}
		
		if(node.type == "DeliveryNode"){
			L2.openNodes.remove(node.getCorrespondingNode(node, nodes).number);
		}
		
		
		
		L2.time = Math.max(L.time+InstanceData.getTime(L.node, node, inputdata)+L.node.weight*inputdata.timeTonService, node.earlyTimeWindow); 	
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
			L2.profit = L.profit + (inputdata.revenue * node.weight * inputdata.getDistance(node, node.getCorrespondingNode(node, nodes), inputdata))
						- inputdata.fuelPrice*inputdata.fuelConsumptionEmptyTruckPerKm*inputdata.getDistance(L.node,node,inputdata)
						- inputdata.fuelPrice*inputdata.fuelConsumptionPerTonKm*L2.weightCapacityUsed*inputdata.getDistance(L.node,node,inputdata)
						- inputdata.otherDistanceDependentCostsPerKm * inputdata.getDistance(L.node, node, inputdata)
						- (inputdata.laborCostperHour + inputdata.otherTimeDependentCostsPerKm)* (L2.time - 0);
			}
		
		if(node.type == "Depot" || node.type == "DeliveryNode") {
			L2.profit = - inputdata.fuelPrice*inputdata.fuelConsumptionEmptyTruckPerKm*inputdata.getDistance(L.node,node,inputdata)
						- inputdata.fuelPrice*inputdata.fuelConsumptionPerTonKm*L2.weightCapacityUsed*inputdata.getDistance(L.node,node,inputdata)
						- inputdata.otherDistanceDependentCostsPerKm * inputdata.getDistance(L.node, node, inputdata)
						- (inputdata.laborCostperHour + inputdata.otherTimeDependentCostsPerKm)* (L2.time - 0); 
			}
	
		return L2;
	}
	
	
	

}
