package actionlisteners;

import java.util.EventListener;

import events.PulseStartEvent;

public interface PulseStartListener extends EventListener {
	public void pulseStart(PulseStartEvent e);
}
