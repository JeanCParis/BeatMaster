package beatbox;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import main.Game;

public abstract class Button {
	
	protected int xPosition, yPosition;
	protected File soundFile;
	protected String signature;

	protected boolean isHit = false;
	protected boolean wasHit = false;
	
	public Button(final int xPosition, final int yPosition, final String signature, final File soundFile) {
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.signature = signature;
		this.soundFile = soundFile;
	}
	
	public int getxPosition() {
		return xPosition;
	}

	public int getyPosition() {
		return yPosition;
	}
	
	public File getSoundFile() {
		return soundFile;
	}
	
	public String getSignature() {
		return signature;
	}
	
	public void hit() {};
	
	public void press() {};
	
	public void release() {};
}
