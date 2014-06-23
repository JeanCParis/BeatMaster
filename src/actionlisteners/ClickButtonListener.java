package actionlisteners;

import java.awt.event.MouseEvent;

import main.Game;
import view.ButtonView;
import beatbox.Button;

public class ClickButtonListener extends ButtonListener {

	public ClickButtonListener(final Game game, final Button button, final ButtonView buttonView) {
		super(game, button, buttonView);
	}
	
    @Override
	public void mouseClicked(final MouseEvent e) {
    	//buttonView.Click
    	game.ButtonClicked(button);
    	
    }
}
