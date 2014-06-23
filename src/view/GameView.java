package view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import main.Game;
import actionlisteners.ClickButtonListener;
import actionlisteners.PressButtonListener;
import beatbox.ClickButton;
import beatbox.PressButton;

public class GameView extends JFrame {
	protected Game game;	
	protected JPanel menuPanel;
	protected JPanel beatboxPanel;
	
	protected ImageIcon buttonIcon;
	
	public GameView(final Game game) {
		this.game = game;
		
		initBeatbox();
	}
	
	private void initBeatbox() {
		
		this.setTitle("BeatMaster");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(600,400);
		
		beatboxPanel = new JPanel();
		
		this.setContentPane(beatboxPanel);
		this.setVisible(true);
	}
	
	public void setButtonImage(final ImageIcon image) {
		buttonIcon = image;
	}
	
	public void addClickButton(final ClickButton button) {
		final ButtonView buttonView = new ButtonView();
		buttonView.setIcon(buttonIcon);
		buttonView.setBounds(button.getxPosition(), button.getyPosition(), Game.BUTTON_SIZE, Game.BUTTON_SIZE);
		buttonView.addMouseListener(new ClickButtonListener(game, button, buttonView));
		beatboxPanel.add(buttonView);
		beatboxPanel.repaint();
	}
	
	public void addPressButton(final PressButton button) {
		final ButtonView buttonView = new ButtonView();
		buttonView.setIcon(buttonIcon);
		buttonView.setBounds(button.getxPosition(), button.getyPosition(), Game.BUTTON_SIZE, Game.BUTTON_SIZE);
		buttonView.addMouseListener(new PressButtonListener(game, button, buttonView));
		beatboxPanel.add(buttonView);
		beatboxPanel.repaint();
	}
}
