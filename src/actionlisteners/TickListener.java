package actionlisteners;

import java.util.EventListener;

import events.TickEvent;

public interface TickListener extends EventListener {
	public void metronomeTicked(TickEvent e);
}
