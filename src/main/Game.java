package main;

import java.io.File;

import javax.swing.ImageIcon;

import view.GameView;
import beatbox.Button;
import beatbox.ClickButton;
import beatbox.Metronome;
import beatbox.Mixer;
import beatbox.Panel;
import beatbox.PressButton;

public class Game {
	
	public static final int PULSE_SPEED = 1;
	public static final int PULSE_MAX_POSITION = 1;
	public static final int BUTTON_SIZE = 40;
	
	protected GameView view;
	
	protected final Metronome metronome = new Metronome();
	protected final Panel panel = new Panel();
	protected final Mixer mixer = new Mixer();
	
	public Game() {
		view = new GameView(this);
		view.setButtonImage(new ImageIcon("images/button.png"));
		
		metronome.addTickListener(mixer);
		
		init();
	}
	
	public void init() {
		
		final File soundFile = new File("sounds/beat.wav");	
		
		final ClickButton cButton = new ClickButton(0, 0, "cb1", soundFile);
		final PressButton pButton = new PressButton(50, 0, "pb1", soundFile);
		view.addClickButton(panel.addClickButton(cButton));
		view.addPressButton(panel.addPressButton(pButton));
	}
	
	public void ButtonClicked(final Button button) {
		
	}
	
	public void ButtonPressed(final Button button) {
		
	}
	
	public void ButtonReleased(final Button button) {
		
	}
}