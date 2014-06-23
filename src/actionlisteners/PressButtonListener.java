package actionlisteners;

import java.awt.event.MouseEvent;

import main.Game;
import view.ButtonView;
import beatbox.Button;

public class PressButtonListener extends ButtonListener {

	public PressButtonListener(final Game game, final Button button, final ButtonView buttonView) {
		super(game, button, buttonView);
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