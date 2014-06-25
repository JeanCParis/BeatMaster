package view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import main.Game;
import beatbox.Beatbox;
import beatbox.ClickButton;
import beatbox.PressButton;

public class GameView extends JFrame {
	protected Beatbox beatbox;	
	protected JPanel menuPanel;
	protected BeatboxView beatboxPanel;
	
	protected ImageIcon buttonIcon;
	
	public GameView() {
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
	
	protected class GameViewThread extends Thread {
		protected boolean stopThread = false;
		protected boolean paused = false;
		protected long updateTime;
		
		public GameViewThread(final int updatesPerSecond) {
			updateTime = Game.MICROSECONDS_PER_SECOND/updatesPerSecond;	
		}
		
		public void setPaused(final boolean paused) {
			this.paused = paused;
		}
		
		@Override
		public void run() {
			long time = 0;
			long previousTime = System.nanoTime();
			
			while(!stopThread) {
				final long nanoTime = System.nanoTime();
				final long elapsedTime = nanoTime - previousTime;
				previousTime = nanoTime;
				
				if (!paused) {
					time += elapsedTime;
					
					if(time>=updateTime) {
						time -= updateTime;
						//mettre a jour
					}
				}
			}
		}
	}
}
