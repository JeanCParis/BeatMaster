package beatbox;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public abstract class Button {
	
	protected int xPosition, yPosition;
	protected File soundFile;
	protected String id;

	protected boolean isHit = false;
	protected boolean wasHit = false;
	
	protected List<Pulse> pulses = new ArrayList<Pulse>();
	
	public Button(final int xPosition, final int yPosition, final String id, final File soundFile) {
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.id = id;
		this.soundFile = soundFile;
	}
	
	public int getXPosition() {
		return xPosition;
	}

	public int getYPosition() {
		return yPosition;
	}
	
	public File getSoundFile() {
		return soundFile;
	}
	
	public String getID() {
		return id;
	}
	
	public List<Pulse> getPulses() {
		return pulses;
	}
	
	public void addPulse(Pulse pulse) {
		pulses.add(pulse);
	}
	
	public void removePulse(Pulse pulse) {
		pulses.remove(pulse);
	}
	
	public void hit() {};
	
	public void press() {};
	
	public void release() {};
}
