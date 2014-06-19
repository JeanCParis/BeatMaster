package beatbox;

public class Pulse {
	protected Button button;
	protected int currentPosition = 0;
	protected int speed;
	protected int maxPosition;
	
	public Pulse(final Button button, final int speed, final int maxPosition) {
		this.button = button;
		this.speed = speed;
		this.maxPosition = maxPosition;
	}
	
	public void Update(final float elapsedTime) {
		currentPosition += speed * elapsedTime;
		
		if (currentPosition >= maxPosition) {
			//trigger event
		}
	}
}
