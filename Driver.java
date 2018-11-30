import java.util.*;

public class Driver
{
	public ArrayList<BaseStation> BSList;
	public ArrayList<DMS> DMSList;
	public ArrayList<UserEquipment> userEquipmentList;
	public int xLength=20, yLength=20, bsRange=6, ueRange = 2;
	public int numOfBaseStations=5, numOfUserEquipments=15;
	
	public void buildSimulationScenario()
	{
		BSList = new ArrayList<BaseStation>();
		DMSList = new ArrayList<DMS>();
		userEquipmentList = new ArrayList<UserEquipment>();
		
		int BSId = 100;
		for(int i=0;i<numOfBaseStations;i++)
		{
			BaseStation bs = new BaseStation(BSId+i, xLength, 0, yLength, 0);
			BSList.add(bs);
			DMSList.add(bs.createDMS());
		}
		
		int userId = 0;
		for(int i=0;i<numOfUserEquipments;i++)
		{
			UserEquipment ueq = new UserEquipment(userId+i, xLength, 0, yLength, 0, 100, true);
			userEquipmentList.add(ueq);
		}
		
		//Add user equipments to the simulation, need to add delays in connection, neglect handover
		for(UserEquipment ue : userEquipmentList)
		{
			double ueX = ue.locationX, ueY = ue.locationY;
			for(int i=0;i<numOfBaseStations;i++)
			{
				double bsX = BSList.get(i).locationX, bsY = BSList.get(i).locationY;
				double dist = Math.sqrt((ueX-bsX)*(ueX-bsX) + (ueY-bsY)*(ueY-bsY));
				if(dist<=bsRange)
				{
					BSList.get(i).connectedEquipmentsInfo.add(ue);
					DMSList.get(i).DMSDatabase.add(ue);
					break;
				}
			}
		}
		//print the simulation environment
		for(DMS dms : DMSList)
		{
			System.out.println("DMS Info " + dms.DMSId + " " + dms.locationX + " " + dms.locationY);
			for(UserEquipment ue : dms.DMSDatabase)
			{
				System.out.println("UE Info " + ue.userEquipmentId + " " + ue.locationX + " " + ue.locationY);
			}
			System.out.println("--------------------------------------------------------------------------------------------\n");
		}
	}
	
		
	//Build the scenario placing BSs and UEs at random places DONE
	//D2D algorithm
	//Send DMS information to another DMS
	//Network delay
	//Time for d2d resolution
	//Battery discharge code
}
