package main;

import javax.swing.ImageIcon;

import view.GameView;
import beatbox.Panel;

public class Game {
	
	public static final int PULSE_SPEED = 1;
	public static final int PULSE_MAX_POSITION = 1;
	public static final int BUTTON_SIZE = 40;
	
	protected GameView view;
	
	protected Panel panel = new Panel();
	
	public Game() {
		view = new GameView(this);
		view.setButtonImage(new ImageIcon("images/button.png"));
		
		init();
	}
	
	public void init() {
		view.addClickButton(panel.addClickButton(0, 0));
		view.addPressButton(panel.addPressButton(50, 0));
	}
}
