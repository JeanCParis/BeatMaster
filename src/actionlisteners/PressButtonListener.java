package actionlisteners;

import java.awt.event.MouseEvent;

import view.ButtonView;
import beatbox.Beatbox;
import beatbox.Button;

public class PressButtonListener extends ButtonListener {

	public PressButtonListener(final Beatbox beatbox, final Button button, final ButtonView buttonView) {
		super(beatbox, button, buttonView);
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