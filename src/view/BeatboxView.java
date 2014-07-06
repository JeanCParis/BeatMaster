package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import main.Action;
import main.Game;
import actionlisteners.CheckListener;
import actionlisteners.MenuListener;
import actionlisteners.MyKeyListener;
import beatbox.Beatbox;

public class BeatboxView extends JPanel {
	protected Beatbox beatbox;
	protected ImageIcon buttonIcon;
	
	final protected Map<String,ButtonView> buttons = new HashMap<String,ButtonView>();
	
	public BeatboxView(final Game game) {
		this.beatbox = game.getBeatbox();
		setBackground(Color.white);
		setLayout(null);
		
		JButton check = new JButton();
		check.setBounds(330, 330, 20, 20);
		check.setBackground(Color.BLACK);
		check.addMouseListener(new CheckListener(beatbox));
		add(check);
		repaint();
		
		addKeyListener(new MyKeyListener(game));
		
		ButtonView.setButtonIcon(new ImageIcon("images/button.png"));
		ButtonView.setButtonIconHit(new ImageIcon("images/buttonHit.png"));
		ButtonView.setButtonIconLit(new ImageIcon("images/buttonLit.png"));
	}
	
	public void addClickButton(final ButtonView buttonView, final String id) {
		add(buttonView);
		buttons.put(id, buttonView);
		repaint();
	}
	
	public void addPressButton(final ButtonView buttonView, final String id) {
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
