package actionlisteners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import main.Game;
import view.ButtonView;
import beatbox.Button;

public abstract class ButtonListener implements MouseListener {
	Game game;
	Button button;
	ButtonView buttonView;
	
	public ButtonListener(final Game game, final Button button, final ButtonView buttonView) {
		this.game = game;
		this.button = button;
		this.buttonView = buttonView;
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
