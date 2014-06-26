package actionlisteners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import beatbox.Beatbox;

public abstract class ButtonListener implements MouseListener {
	protected Beatbox beatbox;
	protected String id;
	
	public ButtonListener(final Beatbox beatbox, final String id) {
		this.beatbox = beatbox;
		this.id = id;
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
