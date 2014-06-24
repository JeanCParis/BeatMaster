package view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import beatbox.Beatbox;
import beatbox.ClickButton;
import beatbox.PressButton;

public class GameView extends JFrame {
	protected Beatbox beatbox;	
	protected JPanel menuPanel;
	protected BeatboxView beatboxPanel;
	
	protected ImageIcon buttonIcon;
	
	public GameView(final Beatbox beatbox) {
		this.beatbox = beatbox;
		this.setTitle("BeatMaster");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600,400);
		
		initBeatbox();
	}
	
	private void initBeatbox() {		
		beatboxPanel = new BeatboxView(beatbox);	
		this.add(beatboxPanel);
		this.setVisible(true);
	}
	
	public void setButtonImage(final ImageIcon image) {
		buttonIcon = image;
	}
	
	public void addClickButton(final ClickButton button) {
		beatboxPanel.addClickButton(button);
	}
	
	public void addPressButton(final PressButton button) {
		beatboxPanel.addPressButton(button);
	}
}
