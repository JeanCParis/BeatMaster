package actionlisteners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import view.ButtonView;
import beatbox.Beatbox;
import beatbox.Button;

public abstract class ButtonListener implements MouseListener {
	Beatbox beatbox;
	Button button;
	ButtonView buttonView;
	
	public ButtonListener(final Beatbox beatbox, final Button button, final ButtonView buttonView) {
		this.beatbox = beatbox;
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
