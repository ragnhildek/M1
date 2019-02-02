import java.util.ArrayList;


public class InstanceData {
	public String instanceName;
	public float fuelPrice;
	public float fuelConsumptionEmptyTruckPerKm;
	public float fuelConsumptionPerTonkm;
	public int laborCostperHour;
	public int otherDistanceDependentCostsPerKm;
	public int otherTimeDependentCostsPerKm;
	public float timeTonService;
	public int revenue;
	public ArrayList<Integer> nodesDepot;
	public ArrayList<Integer> nodes;
	public int volumeCap;
	public int weightCap;
	
	public InstanceData(String datafile) {
		this.instanceName = datafile;
	}
}