package actionlisteners;

import java.util.EventListener;

import events.UpdateEvent;

public interface UpdateListener extends EventListener {
	public void metronomeUpdated(UpdateEvent e);
}
