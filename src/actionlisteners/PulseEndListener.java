package actionlisteners;

import java.util.EventListener;

import events.PulseEndEvent;

public interface PulseEndListener extends EventListener {
	public void pulseEnd(PulseEndEvent e);
}
