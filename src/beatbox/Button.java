package beatbox;

import java.util.ArrayList;
import java.util.List;

import main.Game;

public abstract class Button {
	
	protected int xPosition, yPosition;

	protected boolean isHit = false;
	protected boolean wasHit = false;
	
	protected List<Pulse> pulses = new ArrayList<Pulse>();
	
	public Button(final int xPosition, final int yPosition) {
		this.xPosition = xPosition;
		this.yPosition = yPosition;
	}
	
	public int getxPosition() {
		return xPosition;
	}

	public int getyPosition() {
		return yPosition;
	}
	
	public void hit() {}
	
	public void press() {}
	
	public void release() {}
	
	public void addPulse(final float elapsedTime) {
		final Pulse pulse = new Pulse(this, Game.PULSE_SPEED, Game.PULSE_MAX_POSITION);
		pulses.add(pulse);
		pulse.Update(elapsedTime);
	}
}