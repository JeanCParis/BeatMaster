package actionlisteners;

import java.util.EventListener;

import beatbox.Beatbox;
import events.TickEvent;

public class TickListener implements EventListener {
	protected Beatbox beatbox;
	
	public TickListener(Beatbox beatbox) {
		this.beatbox = beatbox;
	}
	
	public void metronomeTicked(TickEvent e) {
		beatbox.metronomeTicked(e.getTickValue());
	}
}
