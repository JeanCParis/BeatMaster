package actionlisteners;

import java.awt.event.MouseEvent;

import main.Game;

public class ClickButtonListener extends ButtonListener {

	public ClickButtonListener(final Game game, final String id) {
		super(game, id);
	}
	
    @Override
	public void mouseClicked(final MouseEvent e) {
    	if (game.buttonClicked(id)) {
    		//mettre à jour l'icon du bouton
    	}
    }
}
