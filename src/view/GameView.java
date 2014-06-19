package view;

import javax.swing.ImageIcon;
import javax.swing.JButton;
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
		final JButton jbutton = new JButton();
		jbutton.setIcon(buttonIcon);
		jbutton.setBounds(button.getxPosition(), button.getyPosition(), Game.BUTTON_SIZE, Game.BUTTON_SIZE);
		jbutton.addMouseListener(new ClickButtonListener(button));
		beatboxPanel.add(jbutton);
		beatboxPanel.repaint();
	}
	
	public void addPressButton(final PressButton button) {
		final JButton jbutton = new JButton();
		jbutton.setIcon(buttonIcon);
		jbutton.setBounds(button.getxPosition(), button.getyPosition(), Game.BUTTON_SIZE, Game.BUTTON_SIZE);
		jbutton.addMouseListener(new PressButtonListener(button));
		beatboxPanel.add(jbutton);
		beatboxPanel.repaint();
	}
}
