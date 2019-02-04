public class PickupNode {
	public int number; // Even number
	public int pickupLocation;
	public int weight;
	public int volume;
	public float earlyTimeWindow;
	public float lateTimeWindow;
	public DeliveryNode deliveryNode; 
	
	public PickupNode(int number) {
		this.number = number;
	}
}
