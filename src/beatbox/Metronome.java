package beatbox;

import java.util.ArrayList;
import java.util.List;

public class Metronome implements Runnable {
	protected long previousTime;
	protected long elapsedTime, totalTime;
	protected int totalTicks;
	protected long timePerTick;
    protected int bpm;
    protected int subdivision;
    protected long allowedDifference;
    
    protected List<TickListener> listeners = new ArrayList<TickListener>();
    
    protected final long nanoSecondsPerMinute = 60000000000l;  
    
    public Metronome() {
    	this.bpm = 60;
    	this.subdivision = 1;
    	timePerTick = (nanoSecondsPerMinute/bpm) / subdivision;
    }
    
    public void setBPM(final int bpm) {
    	this.bpm = bpm;
    	updateTimePerTick();
    }
    
    public void setSubdivision(final int subdivision) {
    	this.subdivision = subdivision;
    	updateTimePerTick();
    }
    
    private void updateTimePerTick() {
    	timePerTick = (nanoSecondsPerMinute / bpm) / subdivision;
    }
    
    public void initialize() {
    	elapsedTime = 0;
    	totalTime = 0;
    	totalTicks = 0;
    	previousTime = System.nanoTime();
    }
    
    public synchronized void addTickListener(final TickListener listener) {
    	listeners.add(listener);
    }
    
    public synchronized void removeTickListener(final TickListener listener) {
    	listeners.remove(listener);
    }
    
    protected void fireTickEvent() {
    	final TickEvent event = new TickEvent(this);
    	for(final TickListener listener : listeners) {
    		listener.metronomeTicked(event);
    	}
    }

	@Override
	public void run() {
		elapsedTime = System.nanoTime() - previousTime;
		totalTime += elapsedTime;
		
		if(elapsedTime >= timePerTick) {
			elapsedTime -= timePerTick;
			++totalTicks;
			fireTickEvent();
		}	
	}
}
