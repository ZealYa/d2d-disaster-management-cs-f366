import java.util.*;

public class DMS
{
	int DMSId;
	double locationX, locationY;
	ArrayList<UserEquipment> DMSDatabase;
	
	public DMS(int DMSId, double locationX, double locationY)
	{
		this.DMSId = DMSId;
		this.locationX = locationX;
		this.locationY = locationY;
		DMSDatabase = new ArrayList<UserEquipment>();
	}
	
	public DMS()
	{
		
	}
}
