import java.util.ArrayList;


public class InstanceData {
	public String instanceName;
	public float fuelPrice;
	public float fuelConsumptionEmptyTruckPerKm;
	public float fuelConsumptionPerTonKm;
	public int laborCostperHour;
	public float otherDistanceDependentCostsPerKm;
	public int otherTimeDependentCostsPerKm;
	public float timeTonService;
	public int revenue;
	public ArrayList<Integer> nodesDepot;
	public ArrayList<Integer> nodes;
	public int volumeCap;
	public int weightCap;
	public float[][] times;
	public static int[][] distances;
	public int numberOfCities;
	
	public InstanceData(String datafile) {
		this.instanceName = datafile;
	}
	
	public static int getDistance (Node i, Node j, InstanceData inputdata){
	int iLocation = i.location;
	int jLocation = j.location;
	int distance = inputdata.distances[iLocation][jLocation-1];
	System.out.println(distance);
	return distance;

	
	}
}