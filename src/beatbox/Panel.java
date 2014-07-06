package beatbox;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class Panel {
	
	protected int nextID=0;
	protected Map<String, Button> buttons = new HashMap<String, Button>();
	
	public int getNextID() {
		return nextID++;
	}
	
	public Button getButton(final String id) {
		return buttons.get(id);
	}
	public void addButton(final Button button) {
		buttons.put(button.getID(), button);
	}
	
	public void metronomeUpdated(final long elapsedTime) {	
		Iterator<String> iter = buttons.keySet().iterator();
		while (iter.hasNext()) {
		  buttons.get(iter.next()).metronomeUpdated(elapsedTime);
		}
	}
	
	public void metronomeTicked() {
		Iterator<String> iter = buttons.keySet().iterator();
		while (iter.hasNext()) {
		  buttons.get(iter.next()).metronomeTicked();
		}
	}
}