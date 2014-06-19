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
	
	public ClickButton addClickButton(final int xPosition, final int yPosition)
	{
		final ClickButton button = new ClickButton(xPosition, yPosition);
		clickButtons.add(button);
		return button;
	}
	
	public PressButton addPressButton(final int xPosition, final int yPosition)
	{
		final PressButton button = new PressButton(xPosition, yPosition);
		pressButtons.add(button);
		return button;
	}
}