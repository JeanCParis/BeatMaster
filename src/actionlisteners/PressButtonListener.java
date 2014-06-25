package actionlisteners;

import java.awt.event.MouseEvent;

import main.Game;

public class PressButtonListener extends ButtonListener {

	public PressButtonListener(final Game game, final String id) {
		super(game, id);
	}
	
    @Override
	public void mouseReleased(final MouseEvent e) {
    }

    @Override
	public void mousePressed(final MouseEvent e) {
    }
}