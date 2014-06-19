package animation;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Animation {
	protected Image image;
	protected float speed;
	protected int maxSize;
	protected int currentSize = 0;
	
	public Animation(final Image image, final float speed, final int maxSize) {
		this.image = image;
		this.speed = speed;
		this.maxSize = maxSize;
	}
	
	public ImageIcon getImageIcon() {		
		return new ImageIcon(image.getScaledInstance( currentSize, currentSize,  java.awt.Image.SCALE_SMOOTH )) ;
	}
	
	public void Update(final float ElapsedTime) {
		currentSize += ElapsedTime * speed;
	}
}
