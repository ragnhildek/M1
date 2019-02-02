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
	public int[][] distances;
	public int numberOfCities;
	
	public InstanceData(String datafile) {
		this.instanceName = datafile;
	}
}