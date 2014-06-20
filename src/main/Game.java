package main;

import java.io.File;

import javax.swing.ImageIcon;

import view.GameView;
import beatbox.*;

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
		
		File soundFile = new File("sounds/beat");
		
		ClickButton cButton = new ClickButton(0, 0, soundFile);
		PressButton pButton = new PressButton(50, 0, soundFile);
		view.addClickButton(panel.addClickButton(cButton));
		view.addPressButton(panel.addPressButton(pButton));
	}
	
	public void ButtonClicked(Button button) {
		
	}
	
	public void ButtonPressed(Button button) {
		
	}
	
	public void ButtonReleased(Button button) {
		
	}
}