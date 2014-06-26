package events;

import java.util.EventObject;

import view.GameView;

public class UpdateViewEvent extends EventObject {
	protected long elapsedTime;
	
	public UpdateViewEvent(final GameView view, final long elapsedTime) {
		super(view);
		this.elapsedTime = elapsedTime;
	}
	
	public long getElapsedTime() {
		return elapsedTime;
	}
}
