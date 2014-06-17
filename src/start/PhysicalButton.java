package start;

public class PhysicalButton {

	int xPosition, yPosition;
	
	public PhysicalButton(int xPosition, int yPosition)
	{
		this.xPosition = xPosition;
		this.yPosition = yPosition;
	}
	
	public boolean isInside(int xPosition, int yPosition)
	{
		return true;
	}
}