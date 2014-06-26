package events;

import java.util.EventObject;

public class TickEvent extends EventObject {
	protected int tickValue;
	
	public TickEvent(final Object source, int tickValue) {
		super(source);
		this.tickValue = tickValue;
	}
	
	public int getTickValue() {
		return tickValue;
	}
}
