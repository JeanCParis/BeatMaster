package actionlisteners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import main.Game;

public abstract class ButtonListener implements MouseListener {
	protected Game game;
	protected String id;
	
	public ButtonListener(final Game game, final String id) {
		this.game = game;
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
