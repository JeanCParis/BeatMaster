package view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import main.Game;
import actionlisteners.ClickButtonListener;
import actionlisteners.PressButtonListener;
import beatbox.Beatbox;
import beatbox.ClickButton;
import beatbox.PressButton;

public class BeatboxView extends JPanel {
	protected Beatbox beatbox;
	protected ImageIcon buttonIcon;
	
	public BeatboxView(Beatbox beatbox) {
		this.beatbox = beatbox;
		setBackground(Color.white);
		buttonIcon = new ImageIcon("images/button.png");
	}
	
	public void addClickButton(final ClickButton button) {
		final ButtonView buttonView = new ButtonView();
		buttonView.setIcon(buttonIcon);
		buttonView.setBorder(BorderFactory.createEmptyBorder());
		buttonView.setBounds(button.getxPosition(), button.getyPosition(), Game.BUTTON_SIZE, Game.BUTTON_SIZE);
		buttonView.addMouseListener(new ClickButtonListener(beatbox, button, buttonView));
		add(buttonView);
		repaint();
	}
	
	public void addPressButton(final PressButton button) {
		final ButtonView buttonView = new ButtonView();
		buttonView.setIcon(buttonIcon);
		buttonView.setBorder(BorderFactory.createEmptyBorder());
		buttonView.setBounds(button.getxPosition(), button.getyPosition(), Game.BUTTON_SIZE, Game.BUTTON_SIZE);
		buttonView.addMouseListener(new PressButtonListener(beatbox, button, buttonView));
		add(buttonView);
		repaint();
	}
}
