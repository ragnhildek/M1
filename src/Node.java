import java.util.ArrayList;


public class Node {
	public int number; 
	public String type;
	public int location;
	public String locationName;
	public int weight;
	public int volume;
	public float earlyTimeWindow;
	public float lateTimeWindow;
	
	
	public Node(int number) {
		this.number = number;
	}
	
	public void getLocation(int location){
	
		switch(location) {
		case 1: locationName = "Trondheim";
		break;
		case 2: locationName = "Oslo";
		break;
		case 3: locationName = "Hamar";
		break;
		case 4: locationName = "Oppdal";
		break;
		case 5: locationName = "Røros";
		break; 
		case 6: locationName = "Gjøvik";
		break;
		case 7: locationName = "Dombås";
		break;
		case 8: locationName = "Lillehammer";
		break;
		case 9: locationName = "Ringebu";
		break;
		case 10: locationName = "Ringebu";
		break;
		case 11: locationName = "End";
		}
	}
		
	

}