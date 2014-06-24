package beatbox;

import java.util.ArrayList;
import java.util.List;

import events.PulseEndEvent;
import events.UpdateEvent;
import actionlisteners.PulseEndListener;
import actionlisteners.UpdateListener;
import main.Game;

public class Pulse implements UpdateListener {
	protected Button button;
	protected long currentValue = 0;
	protected long speed;
	protected long maxValue;
	
	protected List<PulseEndListener> pulseEndListeners = new ArrayList<PulseEndListener>();

	public Pulse(final Button button, final int speed, final int maxValue) {
		this.button = button;
		this.speed = speed;
		this.maxValue = maxValue;
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
		//System.out.println("pulse " + currentValue);
		currentValue += speed * e.getElapsedTime();
		
		if (currentValue >= maxValue * Game.MICROSECONDS_PER_SECOND) {
			//e.getMetronome().removeUpdateListener(this);
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
