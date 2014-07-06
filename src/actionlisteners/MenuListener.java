package actionlisteners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import main.Action;
import main.Game;

public class MenuListener implements MouseListener {
	protected Game game;
	protected Action action;
	
	public MenuListener(Game game, Action action) {
		this.game = game;
		this.action = action;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		game.doAction(action);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
