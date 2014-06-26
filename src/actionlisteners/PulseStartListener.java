package actionlisteners;

import java.util.EventListener;

import beatbox.Beatbox;
import events.PulseStartEvent;

public class PulseStartListener implements EventListener {
	protected Beatbox beatbox;
	
	public PulseStartListener(Beatbox beatbox) {
		this.beatbox = beatbox;
	}
	
	public void pulseStart(PulseStartEvent e) {
		beatbox.pulseStart(e.getButtonIDs());
	}
}
