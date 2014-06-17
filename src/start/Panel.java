package start;

import java.util.ArrayList;

public class Panel {
	
	private ArrayList<Button> buttons;
	
	public void addButton(Button button)
	{
		buttons.add(button);
	}
	
	public ArrayList<Button> getButtons()
	{
		return buttons;
	}
}
