package actionlisteners;

import java.util.EventListener;

import beatbox.Beatbox;
import events.PulseEndEvent;

public class PulseEndListener implements EventListener {
	protected Beatbox beatbox;
	
	public PulseEndListener(Beatbox beatbox) {
		this.beatbox = beatbox;
	}
	
	public void pulseEnd(PulseEndEvent e) {
		beatbox.pulseEnd(e.getPulse());
	}
}
