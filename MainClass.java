
public class MainClass
{
	public static void main(String[] args) {
		Driver drvr = new Driver();
		drvr.buildSimulationScenario();
		drvr.sendDMSSOS(drvr.DMSList.get(1));
	}
}
