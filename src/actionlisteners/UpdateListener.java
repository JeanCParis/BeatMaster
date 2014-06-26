package actionlisteners;

import java.util.EventListener;

import beatbox.Beatbox;
import events.UpdateEvent;

public class UpdateListener implements EventListener {
	protected Beatbox beatbox;
	
	public UpdateListener(Beatbox beatbox) {
		this.beatbox = beatbox;
	}
	
	public void metronomeUpdated(UpdateEvent e) {
		beatbox.metronomeUpdated(e.getElapsedTime());
	}
}
