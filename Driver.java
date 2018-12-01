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
	
	public void transferInfoDMS(DMS d1, DMS d2)
	{																
		d2.DMSDatabase.addAll(d1.DMSDatabase);
	}
	
	public ArrayList<DMS> sendDMSSOS(DMS d1)
	{
		ArrayList<Integer> dmsIDList = new ArrayList<Integer>();
		ArrayList<DMS> dmsHelpers = new ArrayList<DMS>();
		
		double nearest = 0.0;
		for(int i=0;i<2;i++)
		{
			DMS d3 = new DMS();
			nearest = 1000.0;
			for(DMS d2 : DMSList)
			{
				if(d2.DMSId!=d1.DMSId && !dmsIDList.contains(d2.DMSId))
				{
					double dist = Math.sqrt((d1.locationX-d2.locationX)*(d1.locationX-d2.locationX)
									+ (d1.locationY-d2.locationY)*(d1.locationX-d2.locationY));
					if(dist<=nearest)
					{
						nearest = dist;
						d3 = d2;
					}
				}				
			}
			dmsIDList.add(d3.DMSId);
			dmsHelpers.add(d3);
		}
		
		for(DMS d : dmsHelpers)
		{
			System.out.println("DMS " + d.DMSId);
		}
		
		return dmsHelpers;
	}
	
	public void createD2DGraph(DMS d)
	{
		ArrayList<DMS> dmsHelpers = sendDMSSOS(d);
		for(DMS d1 : dmsHelpers)
		{
			transferInfoDMS(d, d1);
			
		}
	}
	
	//Build the scenario placing BSs and UEs at random places DONE
	//D2D algorithm
	//Send DMS information to another DMS DONE

	//Network delay
	//Time for d2d resolution
	//Battery discharge code
}
