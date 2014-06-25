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

public class Pulse implements UpdateListener, TickListener {
	protected Button button;
	protected long currentValue = 0;
	protected long maxValue;
	protected int ticksLeft;
	protected long speed;
	
	protected List<PulseEndListener> pulseEndListeners = new ArrayList<PulseEndListener>();

	public Pulse(final Button button, final int numberOfTicks, final int speed) {
		this.button = button;
		this.maxValue = numberOfTicks * speed * Game.MICROSECONDS_PER_SECOND;
		this.ticksLeft = numberOfTicks;
		this.speed = speed;
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
		return currentValue/maxValue;
	}

	@Override
	public void metronomeUpdated(final UpdateEvent e) {
		currentValue += speed * e.getElapsedTime();
		
		if (currentValue >= maxValue) {
			currentValue = maxValue;
		}
	}

	@Override
	public void metronomeTicked(final TickEvent e) {
		System.out.println("pulse " + ticksLeft);
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
