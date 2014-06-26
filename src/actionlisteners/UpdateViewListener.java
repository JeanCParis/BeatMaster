package actionlisteners;

import java.util.EventListener;

import events.UpdateViewEvent;

public interface UpdateViewListener extends EventListener {
	public void updateView(UpdateViewEvent e);
}
