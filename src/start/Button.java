package start;

public class Button {

	private boolean isHit = false;
	private boolean wasHit = false;
	
	public boolean isHit()
	{
		return isHit;
	}
	
	public boolean wasHit()
	{
		return wasHit;
	}
	
	public void hit()
	{
		isHit = true;
	}
}
