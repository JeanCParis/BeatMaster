package actionlisteners;

import java.awt.event.MouseEvent;

import beatbox.Button;

public class ClickButtonListener extends ButtonListener {

	public ClickButtonListener(final Button button) {
		super(button);
	}
	
    @Override
	public void mouseClicked(final MouseEvent e) {
    	button.hit();
    }
}
