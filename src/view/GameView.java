package view;

import main.Game;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameView extends JFrame {
	protected Game game;	
	protected JPanel menuPanel;
	
	public GameView(Game game) {
		this.game = game;
	}
}
