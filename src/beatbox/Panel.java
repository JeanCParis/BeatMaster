package beatbox;

import java.util.ArrayList;

public class Panel {
	
	protected ArrayList<ClickButton> clickButtons = new ArrayList<ClickButton>();
	protected ArrayList<PressButton> pressButtons = new ArrayList<PressButton>();
		
	public ArrayList<ClickButton> getClickButtons()
	{
		return clickButtons;
	}
	
	public ArrayList<PressButton> getPressButtons()
	{
		return pressButtons;
	}
	
	public ClickButton addClickButton(final ClickButton button) {
		clickButtons.add(button);
		return button;
	}
	
	public PressButton addPressButton(final PressButton button) {
		pressButtons.add(button);
		return button;
	}
}