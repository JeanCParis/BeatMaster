package view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import main.Game;
import events.UpdateViewEvent;
import actionlisteners.ClickButtonListener;
import actionlisteners.PressButtonListener;
import actionlisteners.UpdateViewListener;
import beatbox.Beatbox;
import beatbox.Button;

public class GameView extends JFrame {
	protected Beatbox beatbox;
	protected JPanel menuPanel;
	protected BeatboxView beatboxPanel;
	
	protected GameViewThread thread;
	
    protected List<UpdateViewListener> updateViewListeners = new ArrayList<UpdateViewListener>();
	
	public GameView(Beatbox beatbox) {
		this.setTitle("BeatMaster");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600,400);
		
		this.beatbox = beatbox;
		
		beatboxPanel = new BeatboxView(beatbox);	
		this.add(beatboxPanel);
		this.setVisible(true);
	}
	
	public void start() {
		thread = new GameViewThread(Game.ANIMATION_UPDATES_PER_SECOND);
		thread.start();
	}
	
	public void addClickButton(final Button button) {
		ButtonView buttonView = new ButtonView(button);
		buttonView.addMouseListener(new ClickButtonListener(beatbox, button.getID()));
		addViewUpdateListener(buttonView);
		beatboxPanel.addClickButton(buttonView, button.getID());
	}
	
	public void addPressButton(final Button button) {
		ButtonView buttonView = new ButtonView(button);
		buttonView.addMouseListener(new PressButtonListener(beatbox, button.getID()));
		addViewUpdateListener(buttonView);
		beatboxPanel.addPressButton(buttonView, button.getID());
	}
	
	public void setHit(String id) {
		beatboxPanel.setHit(id);
	}
	
	public void setNormal(String id) {
		beatboxPanel.setNormal(id);
	}
	
	 public void addViewUpdateListener(final UpdateViewListener listener) {
		 updateViewListeners.add(listener);
	 }
	    
	public void removeViewUpdateListener(final UpdateViewListener listener) {
	    updateViewListeners.remove(listener);
	}
	
	protected void fireUpdateViewEvent(long elapsedTime) {
		UpdateViewEvent event = new UpdateViewEvent(this, elapsedTime);
		for(UpdateViewListener listener : updateViewListeners) {
			listener.updateView(event);
		}
	}
	
	protected class GameViewThread extends Thread {
		protected boolean stopThread = false;
		protected boolean paused = false;
		protected long updateTime;
		
		public GameViewThread(final int updatesPerSecond) {
			updateTime = Game.NANOSECONDS_PER_SECOND/updatesPerSecond;	
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
						fireUpdateViewEvent(elapsedTime);
					}
				}
			}
		}
	}
}
