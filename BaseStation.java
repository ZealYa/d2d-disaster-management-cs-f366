import java.util.*;

public class BaseStation
{
	int BSId;
	double locationX, locationY;
	int numOfUserEquipments;
	boolean isAlive;
	ArrayList<UserEquipment> connectedEquipmentsInfo;
	
	public BaseStation(int BSId, float xBound, float xMin, float yBound, float yMin)
	{
		this.BSId = BSId;
		this.isAlive = true;
		this.connectedEquipmentsInfo = new ArrayList<UserEquipment>();
		this.numOfUserEquipments = 0;
		this.locationY = (Math.random() * ((yBound - yMin) + 1)) + yMin; 
		this.locationX = (Math.random() * ((xBound - xMin) + 1)) + xMin; 
	}
	
	public DMS createDMS()
	{
		return new DMS(this.BSId, this.locationX, this.locationY);
	}
}