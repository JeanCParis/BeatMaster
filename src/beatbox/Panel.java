package beatbox;

import java.util.HashMap;
import java.util.Map;

public class Panel {
	
	protected Map<String, Button> buttons = new HashMap<String, Button>();
	
	public Button getButton(final String id) {
		return buttons.get(id);
	}
	public void addButton(final Button button) {
		buttons.put(button.getID(), button);
	}
}