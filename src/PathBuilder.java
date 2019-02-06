import java.util.ArrayList;
//import java.util.Arrays;
import java.util.Hashtable;
//import java.util.Vector;

public class PathBuilder {
	public ArrayList<Node> nodes;
	public ArrayList<Node> pickupNodes;
	public ArrayList<Node> deliveryNodes;
	public ArrayList<Node> depot;
	public InstanceData inputdata;
	public ArrayList<Hashtable<Integer, Boolean>> feasibility;

	
	public static ArrayList<Hashtable<Integer, Boolean>> feasibilityTest; 
	
	public PathBuilder(ArrayList<Node> pickupNodes, ArrayList<Node> deliveryNodes, ArrayList<Node> nodes, ArrayList<Node> depot, InstanceData inputdata) {
		this.pickupNodes = pickupNodes;
		this.nodes = nodes;
		this.deliveryNodes = deliveryNodes;
		this.depot = depot;
		this.inputdata = inputdata;
		feasibilityTest = new ArrayList<Hashtable<Integer, Boolean>>();
		System.out.println(nodes.get(2).locationName);
}
	
	
	
	
	public static Label LabelExtension(Node node, Label L, InstanceData inputdata, ArrayList<Node> pickupNodes,  ArrayList<Node> nodes, ArrayList<Node>depot) {
		if(L.path.contains(node.number)) {
			return null;
		}
	
		Label L2 = new Label();
		L2.node = node;
		L2.predesessor = L;
		//L2.path = new ArrayList<Integer>();
		L2.path.add(node.number);
		L2.toString();
		
			
		if(node.type == "PickupNode" && !L.unreachablePickupNodes.contains(node)){
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
		else return null;
		
		// only arrive at a delivery node if it has been picked up
		if(node.type == "DeliveryNode" && L.openNodes.contains(node.getCorrespondingNode(node, nodes))){
			L2.openNodes.remove(node.getCorrespondingNode(node, nodes).number);
		}
		else return null;
		
		//not arrive at end depot without delivering every pickup
		if(node.number == 1 && !L.openNodes.isEmpty()){
		return null;	
		}
		
		//not return to start depot
		if(node.number == 0){
			return null;
		}
		
		//not leave end depot
		if (L.node.number == 1){
			return null;
		}
		
		
		L2.time = Math.max(L.time+InstanceData.getTime(L.node, node, inputdata)+L.node.weight*inputdata.timeTonService, node.earlyTimeWindow); 	
		if(L2.time > node.lateTimeWindow){
			return null;
		}
		if (L2.time < node.earlyTimeWindow){
			L2.time = node.earlyTimeWindow;
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
			L2.profit = L.profit - inputdata.fuelPrice*inputdata.fuelConsumptionEmptyTruckPerKm*inputdata.getDistance(L.node,node,inputdata)
						- inputdata.fuelPrice*inputdata.fuelConsumptionPerTonKm*L2.weightCapacityUsed*inputdata.getDistance(L.node,node,inputdata)
						- inputdata.otherDistanceDependentCostsPerKm * inputdata.getDistance(L.node, node, inputdata)
						- (inputdata.laborCostperHour + inputdata.otherTimeDependentCostsPerKm)* (L2.time - 0); 
			}
	
		return L2;
	}
	
	
	
	public static ArrayList<Label> BuildPaths(InstanceData inputdata, ArrayList<Node> pickupNodes, ArrayList<Node> nodes, ArrayList<Node>depot) {
		ArrayList<Label> list = new ArrayList<Label>();
		Label L = new Label();
		L.path = new ArrayList<Integer>();
		L.node = nodes.get(0);
		L.time = 0;
		L.profit = 0;
		L.weightCapacityUsed = 0;
		L.volumeCapacityUsed = 0;
		L.predesessor = null;
		L.unreachablePickupNodes = null;
		L.openNodes = null;		
		
		ArrayList<Label> unprocessed = new ArrayList<Label>();
		ArrayList<Label> processed = new ArrayList<Label>();
		unprocessed.add(L);
		
		while(!unprocessed.isEmpty()) { //Going through all unprocessed labels
			System.out.println("here");
			Label label = unprocessed.remove(0);
			for(int i = 0; i < nodes.size(); i++) {
			System.out.println("here 2");
				Label newLabel = LabelExtension(nodes.get(i), label, inputdata, pickupNodes, nodes, depot);
				if(newLabel!=null) {
					if(checkdominance(newLabel, unprocessed, processed)) {
						unprocessed.add(newLabel); 
					}
				}
			}
			processed.add(label); //the label removed from unprosessed is added here
			
		
		}
		
		System.out.println("Number of paths:" + processed.size());
		return list;
	}
	
	
	private static boolean dominateLabel(Label L1, Label L2) {
		if(L1.time<=L2.time && L1.profit>=L2.profit && L1.node == L2.node) {
			for (int i : L1.path ){
				if (!L2.path.contains(i)){
					return false;
				}
			}
			for (int i : L1.unreachablePickupNodes ){
				if (!L2.unreachablePickupNodes.contains(i)){
					return false;
				}
			}
			System.out.println("Label: " + L1.toString());
			System.out.println("Dominates Label: " + L2.toString());
			return true;
		}
		else return false; 
	}
	
	
	
	//Updates the prosessed and unprossessed lists according to the dominated labels.
	private static boolean checkdominance(Label newLabel, ArrayList<Label> unprocessed, ArrayList<Label> processed) {
		ArrayList<Label> remove = new ArrayList<Label>();
		
		for(Label oldLabel : unprocessed) {
			if(dominateLabel(oldLabel, newLabel)) {
				unprocessed.removeAll(remove);
				return false;
			}
			else if(dominateLabel(newLabel,oldLabel)) {
				remove.add(oldLabel);
			}
		}
		unprocessed.removeAll(remove);
		
		remove = new ArrayList<Label>();
		for(Label oldLabel : processed) {
			if(dominateLabel(oldLabel, newLabel)) {
				processed.removeAll(remove);
				return false;
			}
			else if(dominateLabel(newLabel,oldLabel)) {
				remove.add(oldLabel);
			}
		}
		processed.removeAll(remove);
		return true;
	}
	


}
