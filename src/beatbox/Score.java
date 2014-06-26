package beatbox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import events.PulseStartEvent;
import actionlisteners.PulseStartListener;

public class Score {
	protected HashMap<Integer, Set<String>> score = new HashMap<Integer, Set<String>>();
	protected int shift = 0;
	
	protected List<PulseStartListener> pulseStartListeners = new ArrayList<PulseStartListener>();
	
	public void setShift(final int shift) {
		this.shift = shift;
	}
	
	public void addButton(final int tick, final String id) {
		final Integer key = Integer.valueOf(tick);
		final Set<String> buttonIDs = score.get(key);
		buttonIDs.add(id);
		score.put(key, buttonIDs);
	}
	
	public void setButtons(final int tick, final Set<String> ids) {
			score.put(Integer.valueOf(tick), ids);
	}
	
	public void addScoreListener(final PulseStartListener listener) {
		pulseStartListeners.add(listener);
    }
    
    public void removeScoreListener(final PulseStartListener listener) {
    	pulseStartListeners.remove(listener);
    }
	
	public void metronomeTicked(int tick) {
		final Set<String> buttonIDs = score.get(tick);
		if(buttonIDs != null) {
			final PulseStartEvent event = new PulseStartEvent(this, buttonIDs);
			for(final PulseStartListener listener : pulseStartListeners) {
				listener.pulseStart(event);
			}
		}
	}
}
