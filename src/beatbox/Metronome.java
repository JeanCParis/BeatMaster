package beatbox;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import main.Game;
import actionlisteners.TickListener;
import actionlisteners.UpdateListener;
import events.TickEvent;
import events.UpdateEvent;

public class Metronome {
	protected MetronomeThread thread;
	
	protected volatile long  totalTime = 0;
	protected int totalTicks = 0;
	protected long timePerTick;
    protected int bpm=1;
    protected int subdivision=1;
    protected long allowedTimeDifference;
    protected Mixer mixer;
    
    protected List<TickListener> tickListeners = new ArrayList<TickListener>();
    protected List<UpdateListener> updateListeners = new ArrayList<UpdateListener>();
    
    public void setMixer(final Mixer mixer) {
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
    
    public void setAllowedTimeDifference(final long allowedTimeDifference) {
    	this.allowedTimeDifference = allowedTimeDifference;
    }
    
    private void updateTimePerTick() {
    	timePerTick = ((60 * Game.NANOSECONDS_PER_SECOND) / bpm) / subdivision;
    	Pulse.setTimePerTick(timePerTick);
    }
    
    public long getTimePerTick() {
    	return timePerTick;
    }
    
    public int getTotalTicks() {
    	return totalTicks;
    }
    
    public void start() {
    	thread = new MetronomeThread();
    	thread.start();
    }
    
    public void setPaused(final boolean paused) {
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
	
	public boolean playClip(final File file, final String id) {
		boolean succeeded = true;
		
		if (totalTime >= timePerTick - allowedTimeDifference) {
			mixer.addUpcommingClip(file, id);
			System.out.println("before/on time");
		}
		else if (totalTime <= allowedTimeDifference) {
			mixer.playClip(file, id, totalTime/1000);
			System.out.println("after time");
		}
		else {
			succeeded = false;
			System.out.println("out of time");
		}
		
		return succeeded;
	}
	
   protected void fireTickEvent() {
    	final TickEvent event = new TickEvent(this, totalTicks);
    	for(int i=0 ; i<tickListeners.size() ; ++i) {
    		tickListeners.get(i).metronomeTicked(event);
    		//updateListeners.remove(i);--i;
    	}
    }
   
   protected void fireUpdateEvent(final long elapsedTime) {
	   	final UpdateEvent event = new UpdateEvent(this, elapsedTime);
	   	for(int i=0 ; i<updateListeners.size() ; ++i) {
    		updateListeners.get(i).metronomeUpdated(event);
    		//updateListeners.remove(i);--i;
    	}
   }
   
   protected class MetronomeThread extends Thread {
		protected boolean stopThread = false;
		protected boolean paused = false;
		
		public void setPaused(final boolean paused) {
			this.paused = paused;
		}
		
		@Override
		public void run() {
			long previousTime = System.nanoTime();
			
			while(!stopThread) {
				final long nanoTime = System.nanoTime();
				final long elapsedTime = nanoTime - previousTime;
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
