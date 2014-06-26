package beatbox;

import java.util.ArrayList;
import java.util.List;

import main.Game;
import actionlisteners.PulseEndListener;
import actionlisteners.TickListener;
import actionlisteners.UpdateListener;
import events.PulseEndEvent;
import events.TickEvent;
import events.UpdateEvent;

public class Pulse {
	protected static long timePerTick;
	
	protected Button button;
	protected long currentValue = 0;
	protected float currentFactor = 0;
	protected int numberOfTicks, ticksLeft;
	
	protected List<PulseEndListener> pulseEndListeners = new ArrayList<PulseEndListener>();

	public Pulse(final Button button, final int numberOfTicks) {
		this.button = button;
		this.numberOfTicks = numberOfTicks;
		this.ticksLeft = numberOfTicks;
	}
	
	public static void setTimePerTick(final long timePerTick) {
		Pulse.timePerTick = timePerTick;
	}
	
	public Button getButton() {
		return button;
	}
	
	public synchronized void addPulseEndedListener(final PulseEndListener listener) {
		pulseEndListeners.add(listener);
    }
    
    public synchronized void removePulseEndedListener(final PulseEndListener listener) {
    	pulseEndListeners.remove(listener);
    }
	
	public float getCurrentFactor() {
		return currentFactor;
	}

	public void metronomeUpdated(final long elapsedTime) {
		currentValue += elapsedTime;
		currentFactor = (float)currentValue/(float)timePerTick/numberOfTicks;
	}

	public void metronomeTicked() {
		if(--ticksLeft==0) {
			firePulseEndedEvent();
		}
	}
	
	protected void firePulseEndedEvent() {
		final PulseEndEvent event = new PulseEndEvent(this);
	   	for(final PulseEndListener listener : pulseEndListeners) {
	   		listener.pulseEnd(event);
	   	}
	}
}
