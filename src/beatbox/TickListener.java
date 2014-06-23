package beatbox;

import java.util.EventListener;

public interface TickListener extends EventListener {
	public void metronomeTicked(TickEvent e);
}
