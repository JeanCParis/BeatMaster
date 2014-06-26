package actionlisteners;

import java.awt.event.MouseEvent;

import beatbox.Beatbox;

public class ClickButtonListener extends ButtonListener {
	public ClickButtonListener(final Beatbox beatbox, final String id) {
		super(beatbox, id);
	}
	
    @Override
	public void mouseClicked(final MouseEvent e) {
    	if (beatbox.buttonClicked(id)) {
    		//mettre à jour l'icon du bouton?>>>non, depend du depqrt du son !
    	}
    }
}
