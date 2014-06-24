package actionlisteners;

import java.awt.event.MouseEvent;

import view.ButtonView;
import beatbox.Beatbox;
import beatbox.Button;

public class ClickButtonListener extends ButtonListener {

	public ClickButtonListener(final Beatbox beatbox, final Button button, final ButtonView buttonView) {
		super(beatbox, button, buttonView);
	}
	
    @Override
	public void mouseClicked(final MouseEvent e) {
    	beatbox.ButtonClicked(button);
    }
}
