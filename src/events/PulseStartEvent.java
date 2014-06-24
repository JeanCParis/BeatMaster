package events;

import java.util.EventObject;
import java.util.Set;

public class PulseStartEvent extends EventObject {
	protected Set<String> buttonSignatures;
	
	public PulseStartEvent(final Object source, Set<String> buttonSignatures) {
		super(source);
		this.buttonSignatures = buttonSignatures;
	}
	
	public Set<String> getButtonSignatures() {
		return buttonSignatures;
	}
}