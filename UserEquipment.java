
public class UserEquipment
{
	int userEquipmentId;
	boolean isDead;
	double locationX, locationY;
	double batteryLevel;
	boolean isD2DCapable;
	UserEquipment D2DConnected;	
	
	public UserEquipment(int UEId, double xBound, double xMin, double yBound, double yMin, 
						 double batteryLevel, boolean isD2DCapable
						)
	{
		this.userEquipmentId = UEId;
		this.locationY = (Math.random() * ((yBound - yMin) + 1)) + yMin; 
		this.locationX = (Math.random() * ((xBound - xMin) + 1)) + xMin;
		this.batteryLevel = batteryLevel;
		this.isD2DCapable = isD2DCapable;		
	}
}
