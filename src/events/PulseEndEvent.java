package events;

import java.util.EventObject;

import beatbox.Pulse;

public class PulseEndEvent extends EventObject {

	protected Pulse pulse;
	
	public PulseEndEvent(final Pulse source) {
		super(source);
		this.pulse = source;
	}
	
	public Pulse getPulse() {
		return pulse;
	}
}
