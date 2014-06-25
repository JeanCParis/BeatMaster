package events;

import java.util.EventObject;
import java.util.Set;

public class PulseStartEvent extends EventObject {
	protected Set<String> buttonIDs;
	
	public PulseStartEvent(final Object source, Set<String> buttonIDs) {
		super(source);
		this.buttonIDs = buttonIDs;
	}
	
	public Set<String> getButtonIDs() {
		return buttonIDs;
	}
}