package actionlisteners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.Action;
import main.Game;

public class MyKeyListener implements KeyListener {
	protected Game game;
	
	public MyKeyListener(Game game) {
		this.game = game;
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		System.out.print("l");
		if(arg0.getKeyChar()==27){
			game.doAction(Action.LAUNCH_MENU);
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {		
	}

}
