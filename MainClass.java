
public class MainClass
{
	public static void main(String[] args) {
		Driver drvr = new Driver();
		drvr.buildSimulationScenario();
		drvr.createD2DGraph(drvr.DMSList.get(0));
	}
}
