package beatbox;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import events.TickEvent;
import events.UpdateEvent;
import actionlisteners.TickListener;
import actionlisteners.UpdateListener;
import main.Game;

public class Metronome {
	protected MetronomeThread thread;
	
	protected long previousTime;
	protected volatile long  totalTime = 0;
	protected int totalTicks = 0;
	protected long timePerTick;
    protected int bpm=1;
    protected int subdivision=1;
    protected long allowedTimeDifference;
    protected Mixer mixer;
    
    protected List<TickListener> tickListeners = new ArrayList<TickListener>();
    protected List<UpdateListener> updateListeners = new ArrayList<UpdateListener>();
    
    public void setMixer(Mixer mixer) {
    	this.mixer = mixer;
    }
    
    public void setBPM(final int bpm) {
    	this.bpm = bpm;
    	updateTimePerTick();
    }
    
    public void setSubdivision(final int subdivision) {
    	this.subdivision = subdivision;
    	updateTimePerTick();
    }
    
    public void setAllowedTimeDifference(long allowedTimeDifference) {
    	this.allowedTimeDifference = allowedTimeDifference;
    }
    
    private void updateTimePerTick() {
    	timePerTick = ((60 * Game.MICROSECONDS_PER_SECOND) / bpm) / subdivision;
    }
    
    public int getTotalTicks() {
    	return totalTicks;
    }
    
    public void start() {
    	thread = new MetronomeThread();
    	thread.start();
    }
    
    public void setPaused(boolean paused) {
    	thread.setPaused(paused);
    }
    
    public void addTickListener(final TickListener listener) {
    	tickListeners.add(listener);
    }
    
    public void removeTickListener(final TickListener listener) {
    	tickListeners.remove(listener);
    }
    
    public void addUpdateListener(final UpdateListener listener) {
    	updateListeners.add(listener);
    }
    
    public void removeUpdateListener(final UpdateListener listener) {
    	updateListeners.remove(listener);
    }
	
	public boolean playClip(File file, String signature) {
		boolean succeeded = true;
		
		if (totalTime >= timePerTick - allowedTimeDifference) {
			mixer.addUpcommingClip(file, signature);
			System.out.println("before/on time");
		}
		else if (totalTime <= allowedTimeDifference) {
			mixer.playClip(file, signature, totalTime/1000);
			System.out.println("after time");
		}
		else {
			succeeded = false;
			System.out.println("out of time");
		}
		
		return succeeded;
	}
	
   protected void fireTickEvent() {
    	final TickEvent event = new TickEvent(this);
    	for(final TickListener listener : tickListeners) {
    		listener.metronomeTicked(event);
    	}
    }
   
   protected void fireUpdateEvent(long elapsedTime) {
	   	final UpdateEvent event = new UpdateEvent(this, elapsedTime);
	   	for(final UpdateListener listener : updateListeners) {
	   		listener.metronomeUpdated(event);
	   	}
   }
   
   protected class MetronomeThread extends Thread {
		protected boolean stopThread = false;
		protected boolean paused = false;
		
		public MetronomeThread() {
			previousTime = System.nanoTime();
		}
		
		public void setPaused(boolean paused) {
			this.paused = paused;
		}
		
		@Override
		public void run() {
			while(!stopThread) {
				long nanoTime = System.nanoTime();
				long elapsedTime = nanoTime - previousTime;
				previousTime = nanoTime;
				
				if (!paused) {
					fireUpdateEvent(elapsedTime);
					totalTime += elapsedTime;
					
					if(totalTime >= timePerTick) {
						totalTime -= timePerTick;
						++totalTicks;
						fireTickEvent();
					}
				}
			}
		}
	}
}
