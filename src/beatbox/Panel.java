package beatbox;

import java.util.HashMap;
import java.util.Map;

public class Panel {
	
	protected Map<String, Button> buttons = new HashMap<String, Button>();
	
	public Button getButton(final String signature) {
		return buttons.get(signature);
	}
	public void addButton(final Button button) {
		buttons.put(button.getSignature(), button);
	}
}