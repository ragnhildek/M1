import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;


public class InputReader {
	public static void inputReader(String datafile,  ArrayList<Node> nodes, InstanceData inputdata, ArrayList<Node> depot, ArrayList<Node> pickupNodes, ArrayList<Node> deliveryNodes) {
		int noCustomers = 0;
		int noVessels = 0;
		try {
			File file = new File(datafile);
			//File file2 = file.getAbsoluteFile();
			FileReader reader = new FileReader(file);
			BufferedReader fr = new BufferedReader(reader);
			
			String line = fr.readLine();
			System.out.println(line);
			String[] list1 =line.split(",");
			for (int i = 1; i < list1.length; i++) {
				int number = Integer.parseInt(list1[i].trim());
				//System.out.println(number);
				Node hello = new Node(number);
				nodes.add(hello);
				if(number == 0 || number == 1) {
					hello.type = "Depot";
					depot.add(hello);
				}
				else if((number%2)==0) {
					hello.type = "PickupNode";
					pickupNodes.add(hello);
				}
				else {
					hello.type = "DeliveryNode";
					deliveryNodes.add(hello);
				}
			}
			
			//for(int i=0; i <nodes.size(); i++){
			//	System.out.println(nodes.get(i).number);
			//}
				
			
			
			line = fr.readLine();
			System.out.println(line);
			list1 = line.split(",");
			inputdata.volumeCap = Integer.parseInt(list1[1].trim());
			
			line = fr.readLine();
			System.out.println(line);
			list1 = line.split(",");
			inputdata.weightCap = Integer.parseInt(list1[1].trim());	
			
			line = fr.readLine();
			System.out.println(line);
			list1 = line.split(",");
			for(int i = 1; i < list1.length; i++){
				float number = Float.parseFloat(list1[i].trim());
				nodes.get(i-1).earlyTimeWindow = number;
				System.out.println(number);
			}
			System.out.println(nodes.get(2).earlyTimeWindow);
			
			line = fr.readLine();
			System.out.println(line);
			list1 = line.split(",");
			for(int i = 1; i < list1.length; i++){
				float number = Float.parseFloat(list1[i].trim());
				nodes.get(i-1).lateTimeWindow = number;
			}
			
			line = fr.readLine();
			//System.out.println(line);
			list1 = line.split(",");
			for(int i = 1; i < list1.length; i++){
				int number = Integer.parseInt(list1[i].trim());
				pickupNodes.get(i-1).weight = number;
				deliveryNodes.get(i-1).weight = number;
				//System.out.println(number);
			}
			
			line = fr.readLine();
			//System.out.println(line);
			list1 = line.split(",");
			for(int i = 1; i < list1.length; i++){
				int number = Integer.parseInt(list1[i].trim());
				pickupNodes.get(i-1).volume = number;
				deliveryNodes.get(i-1).volume = number;
				//System.out.println(number);
			}
			//System.out.println(pickupNodes.get(1).weight);
			
			line = fr.readLine();
			list1 = line.split(",");
			for(int i = 1; i < list1.length; i++){
				int number = Integer.parseInt(list1[i].trim());
				pickupNodes.get(i-1).location = number;
				pickupNodes.get(i-1).getLocation(number);
			}
			line = fr.readLine();
			list1 = line.split(",");
			for(int i = 1; i < list1.length; i++){
				int number = Integer.parseInt(list1[i].trim());
				deliveryNodes.get(i-1).location = number;
				deliveryNodes.get(i-1).getLocation(number);
			}
			// System.out.println(pickupNodes.get(1).locationName);
			line = fr.readLine();
			list1 = line.split(",");
			int number = Integer.parseInt(list1[1].trim());
			depot.get(0).location = number;
			depot.get(0).getLocation(number);
			
			line = fr.readLine();
			list1 = line.split(",");
			number = Integer.parseInt(list1[1].trim());
			depot.get(1).location = number;
			depot.get(1).getLocation(number);
			
			line = fr.readLine();
			System.out.println(line);
			list1 = line.split(",");
			inputdata.numberOfCities = Integer.parseInt(list1[1].trim());
			
			inputdata.times = new float[inputdata.numberOfCities][inputdata.numberOfCities];
			inputdata.distances = new int[inputdata.numberOfCities][inputdata.numberOfCities];
			
			fr.readLine();
			
			for(int i = 0; i < inputdata.numberOfCities; i++) {
				line = fr.readLine();
				for(int j = 0; j < inputdata.numberOfCities; j++){
				list1 = line.split(",");
				inputdata.times[i][j] = Float.parseFloat(list1[j].trim());
				}
			}
			
			fr.readLine();
	
			for(int i = 0; i < inputdata.numberOfCities; i++) {
				line = fr.readLine();
				for(int j = 0; j < inputdata.numberOfCities; j++){
				list1 = line.split(",");
				inputdata.distances[i][j] = Integer.parseInt(list1[j].trim());
				}
			}
			System.out.println(inputdata.distances[0][1]);
			
			line = fr.readLine();
			System.out.println(line);
			list1 = line.split(",");
			inputdata.fuelPrice = Float.parseFloat(list1[1].trim());
			
			line = fr.readLine();
			System.out.println(line);
			list1 = line.split(",");
			inputdata.fuelConsumptionEmptyTruckPerKm = Float.parseFloat(list1[1].trim());
			
			line = fr.readLine();
			System.out.println(line);
			list1 = line.split(",");
			inputdata.fuelConsumptionPerTonKm = Float.parseFloat(list1[1].trim());
			
			line = fr.readLine();
			System.out.println(line);
			list1 = line.split(",");
			inputdata.laborCostperHour = Integer.parseInt(list1[1].trim());
			
			line = fr.readLine();
			System.out.println(line);
			list1 = line.split(",");
			inputdata.otherDistanceDependentCostsPerKm = Float.parseFloat(list1[1].trim());
			
			line = fr.readLine();
			System.out.println(line);
			list1 = line.split(",");
			inputdata.otherTimeDependentCostsPerKm = Integer.parseInt(list1[1].trim());
			
			line = fr.readLine();
			System.out.println(line);
			list1 = line.split(",");
			inputdata.timeTonService = Float.parseFloat(list1[1].trim());
			
			line = fr.readLine();
			System.out.println(line);
			list1 = line.split(",");
			inputdata.revenue = Integer.parseInt(list1[1].trim());
			
			
			
			
			
			
		
			
			
			
			//System.out.println(Arrays.toString(list1));
			
			// System.out.println(pickupnodes.get(0).number);
			
			//line = fr.readLine();
			//System.out.println(line);
			//list1 =line.split(",");
			//for (int i = 1; i < list1.length; i++) {
			//	int number = Integer.parseInt(list1[i].trim());
			//	deliverynodes.add(new DeliveryNode(number));
			//}
			//System.out.println(Arrays.toString(list1));
			
			// System.out.println(deliverynodes);
			
			//line = fr.readLine();
			//System.out.println(line);
			//list1 =line.split(",");
			//for (int i = 1; i < list1.length; i++) {
			//	int number = Integer.parseInt(list1[i].trim());
			//	inputdata.nodesDepot.add(number);
			//}
			
			//line = fr.readLine();
			//System.out.println(line);
			//list1 =line.split(",");
			//for (int i = 1; i < list1.length; i++) {
			//	int number = Integer.parseInt(list1[i].trim());
			//	inputdata.nodes.add(number);
			//}
			
			//line = fr.readLine();
			//System.out.println(line);
			//list1 =line.split(",");
			
			
			
		
			/**
			
			fr.readLine();
			line = fr.readLine();
			System.out.println(line);
			list1 =line.split(":");
			inputdata.ordinarySailingTime = Integer.parseInt(list1[1].trim());
			
			line = fr.readLine();
			System.out.println(line);
			list1 =line.split(":");
			inputdata.nightExtraSailingTime = Integer.parseInt(list1[1].trim());
			fr.readLine();
			fr.readLine();
			line = fr.readLine();
			System.out.println(line);
			list1 =line.split(":");
			inputdata.depotOperatingTime = Integer.parseInt(list1[1].trim());
			
			System.out.println(inputdata.ordinarySailingTime+" "+inputdata.nightExtraSailingTime+" "+inputdata.depotOperatingTime);
			
			fr.readLine();
			line = fr.readLine();
			System.out.println(line);
			list1 =line.split(":");
			inputdata.nrFuelTypes = Integer.parseInt(list1[1].trim());
			
			fr.readLine();
			fr.readLine();
			fr.readLine();
			line = fr.readLine();
			System.out.println(line);
			list1 =line.split(":");
			inputdata.nrMaxVoyages = Integer.parseInt(list1[1].trim());
			fr.readLine();
			line = fr.readLine();
			System.out.println(line);
			list1 =line.split(":");
			inputdata.morningStartHour = Integer.parseInt(list1[1].trim());
			
			line = fr.readLine();
			System.out.println(line);
			list1 =line.split(":");
			inputdata.morningEndHour = Integer.parseInt(list1[1].trim());
			System.out.println(inputdata.morningEndHour);
			
			for(int i =0; i < 7; i++) {
				fr.readLine();
			}
			line = fr.readLine();
			System.out.println(line);
			line = line.trim();
			line = line.replace('[', ' ');
			line = line.replace(']', ' ');
			System.out.println(line);
			list1 =line.split(",");
			System.out.println(Arrays.toString(list1));
			for(int i = 0; i < noVessels; i++) {
				vessels.get(i).pumpingRate = Integer.parseInt(list1[i].trim());
			}
			System.out.println(vessels.get(2).pumpingRate);
			
			fr.readLine();
			fr.readLine();
			line = fr.readLine();
			System.out.println(line);
			line = line.trim();
			line = line.replace('[', ' ');
			line = line.replace(']', ' ');
			System.out.println(line);
			list1 =line.split(",");
			System.out.println(Arrays.toString(list1));
			for(int i = 0; i < noVessels; i++) {
				vessels.get(i).startTime = Integer.parseInt(list1[i].trim());
			}
			System.out.println(vessels.get(0).startTime);
			
			fr.readLine();
			fr.readLine();
			line = fr.readLine();
			System.out.println(line);
			line = line.trim();
			line = line.replace('[', ' ');
			line = line.replace(']', ' ');
			System.out.println(line);
			list1 =line.split(",");
			System.out.println(Arrays.toString(list1));
			for(int i = 0; i < noVessels; i++) {
				vessels.get(i).compartments = new int[Integer.parseInt(list1[i].trim())];
			}
			System.out.println(vessels.get(2).compartments.length);
			
			fr.readLine();
			fr.readLine();
			line = fr.readLine();
			System.out.println(line);
			line = line.trim();
			line = line.replace('[', ' ');
			line = line.replace(']', ' ');
			System.out.println(line);
			list1 =line.split(",");
			System.out.println(Arrays.toString(list1));
			for(int i = 0; i < noVessels; i++) {
				vessels.get(i).fixedCost = Integer.parseInt(list1[i].trim());
			}
			System.out.println(vessels.get(2).fixedCost);
			
			fr.readLine();
			fr.readLine();
			fr.readLine();
			line = fr.readLine();
			System.out.println(line);
			line = line.trim();
			line = line.replace('[', ' ');
			line = line.replace(']', ' ');
			System.out.println(line);
			list1 =line.split(",");
			System.out.println(Arrays.toString(list1));
			for(int i = 0; i < noVessels; i++) {
				vessels.get(i).sailingCost = Integer.parseInt(list1[i].trim());
			}
			System.out.println(vessels.get(1).sailingCost);
			
			for(int i =0; i < 6; i++) {
				fr.readLine();
			}
			for(int i = 0; i < noVessels; i++) {
				vessels.get(i).fuelCompartments = new int[inputdata.nrFuelTypes][vessels.get(i).compartments.length];
			}
			for(int i = 0; i < noVessels; i++) {
				for(int j = 0; j < inputdata.nrFuelTypes; j++) {
					line = fr.readLine();
					System.out.println(line);
					line = line.trim();
					line = line.replace('[', ' ');
					line = line.replace(']', ' ');
					System.out.println(line);
					list1 =line.split(",");
					System.out.println(Arrays.toString(list1));
					for(int k = 0; k < vessels.get(i).compartments.length; k++) {
						vessels.get(i).fuelCompartments[j][k] = Integer.parseInt(list1[k].trim());
					}
					System.out.println(Arrays.toString(vessels.get(i).fuelCompartments[j]));
				}
				fr.readLine();
			}
			
			fr.readLine();
			for(int i = 0; i < noVessels; i++) {
				
					line = fr.readLine();
					System.out.println(line);
					line = line.trim();
					line = line.replace('[', ' ');
					line = line.replace(']', ' ');
					System.out.println(line);
					list1 =line.split(",");
					System.out.println(Arrays.toString(list1));
					for(int k = 0; k < vessels.get(i).compartments.length; k++) {
						vessels.get(i).compartments[k] = Integer.parseInt(list1[k].trim());
					
						
					}
					System.out.println(Arrays.toString(vessels.get(i).compartments));
			}
			
			fr.close();
			reader.close();
			File file2 = new File(instance);
			//File file2 = file.getAbsoluteFile();
			reader = new FileReader(file2);
			fr = new BufferedReader(reader);
			
			fr.readLine();
			line = fr.readLine();
			System.out.println(line);
			list1 =line.split(":");
			//System.out.println(Arrays.toString(list1));
			noCustomers = Integer.parseInt(list1[1].trim());
			
			for(int i =0; i < noCustomers; i++) {
				customers.add(new CustomerShip(i));
				customers.get(i).demand = new int[inputdata.nrFuelTypes];
			}
			fr.readLine();
			line = fr.readLine();
			System.out.println(line);
			list1 =line.split(":");
			//System.out.println(Arrays.toString(list1));
			inputdata.nrTimePeriods = Integer.parseInt(list1[1].trim());
			fr.readLine();
			for(int i = 0; i < noCustomers; i++) {
				line = fr.readLine();
				System.out.println(line);
				list1 =line.split(",");
				System.out.println(Arrays.toString(list1));
				for(int k = 0; k < inputdata.nrFuelTypes; k++) {
					customers.get(i).demand[k] = Integer.parseInt(list1[k].trim());
				}
				System.out.println(Arrays.toString(customers.get(i).demand));
			}
			fr.readLine();
			line = fr.readLine();
			System.out.println(line);
			list1 =line.split(",");
			System.out.println(Arrays.toString(list1));
			for(int i = 0; i < noCustomers; i++) {
				customers.get(i).TWE = Integer.parseInt(list1[i].trim());
				customers.get(i).TWL = Integer.parseInt(list1[i].trim())+24;
			}
			fr.readLine();
			line = fr.readLine();
			System.out.println(line);
			list1 =line.split(",");
			System.out.println(Arrays.toString(list1));
			for(int i = 0; i < noCustomers; i++) {
				if(Integer.parseInt(list1[i].trim())==1) {
				customers.get(i).TWE+= inputdata.morningStartHour;
				customers.get(i).TWL+=(inputdata.morningEndHour-24);
				}
				System.out.println("customer "+i+" "+customers.get(i).TWE+" "+customers.get(i).TWL);
			}
			
//			System.exit(0);
//			String[] list2 =list1[1].split(",");
//			System.out.println(Arrays.toString(list2));
//			ship1 =Integer.parseInt(list2[0].trim()); 
//			ship2 =Integer.parseInt(list2[1]); 
//			System.out.println(ship1+" "+ship2);
////			System.exit(0);
//			for(int i = 0; i <9; i++) {
//				fr.readLine();
//			}**/
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
		


}
