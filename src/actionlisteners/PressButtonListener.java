package actionlisteners;

import java.awt.event.MouseEvent;

import beatbox.Beatbox;

public class PressButtonListener extends ButtonListener {

	public PressButtonListener(final Beatbox beatbox, final String id) {
		super(beatbox, id);
	}
	
    @Override
	public void mouseReleased(final MouseEvent e) {
    }

    @Override
	public void mousePressed(final MouseEvent e) {
    }
}