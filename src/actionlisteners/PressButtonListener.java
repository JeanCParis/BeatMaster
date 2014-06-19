package actionlisteners;

import java.awt.event.MouseEvent;

import beatbox.Button;

public class PressButtonListener extends ButtonListener {

	public PressButtonListener(final Button button) {
		super(button);
	}
	
    @Override
	public void mouseReleased(final MouseEvent e) {
    	button.release();
    }

    @Override
	public void mousePressed(final MouseEvent e) {
    	button.press();
    }
}