package actionlisteners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import beatbox.Button;

public abstract class ButtonListener implements MouseListener {
	Button button;
	
	public ButtonListener(final Button button) {
		this.button = button;
	}
	
    @Override
	public void mousePressed(final MouseEvent e) {
     }

     @Override
	public void mouseReleased(final MouseEvent e) {
     }

     @Override
	public void mouseEntered(final MouseEvent e) {
     }

     @Override
	public void mouseExited(final MouseEvent e) {
     }

     @Override
	public void mouseClicked(final MouseEvent e) {
     }
}
