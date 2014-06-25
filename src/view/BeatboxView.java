package view;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import main.Game;
import actionlisteners.ClickButtonListener;
import actionlisteners.PressButtonListener;

public class BeatboxView extends JPanel {
	protected Game game;
	protected ImageIcon buttonIcon;
	
	final protected Map<String,ButtonView> buttons = new HashMap<String,ButtonView>();
	
	public BeatboxView(final Game game) {
		this.game = game;
		setBackground(Color.white);
		
		ButtonView.setButtonIcon(new ImageIcon("images/button.png"));
		ButtonView.setButtonIconHit(new ImageIcon("images/buttonHit.png"));
		ButtonView.setButtonIconLit(new ImageIcon("images/buttonLit.png"));
	}
	
	public void addClickButton(final int xPosition, final int yPosition, final String id) {
		final ButtonView buttonView = new ButtonView(xPosition, yPosition);
		buttonView.addMouseListener(new ClickButtonListener(game, id));
		add(buttonView);
		buttons.put(id, buttonView);
		repaint();
	}
	
	public void addPressButton(final int xPosition, final int yPosition, final String id) {
		final ButtonView buttonView = new ButtonView(xPosition, yPosition);
		buttonView.addMouseListener(new PressButtonListener(game, id));
		add(buttonView);
		buttons.put(id, buttonView);
		repaint();
	}
	
	public void setHit(String id) {
		buttons.get(id).setHitIcon();
	}
	
	public void setNormal(String id) {
		buttons.get(id).setNormalIcon();
	}
	
	public ButtonView getButton(final String id) {
		return buttons.get(id);
	}
}
