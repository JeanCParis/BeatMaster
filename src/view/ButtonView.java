package view;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import main.Game;
import actionlisteners.ClickButtonListener;

public class ButtonView extends JButton {
	static protected ImageIcon buttonIcon;
	static protected ImageIcon buttonIconHit;
	static protected ImageIcon buttonIconLit;
	
	public ButtonView(int xPosition, int yPosition) {
		setIcon(buttonIcon);
		setBorder(BorderFactory.createEmptyBorder());
		setBounds(xPosition, yPosition, Game.BUTTON_SIZE, Game.BUTTON_SIZE);
	}
	
	public static void setButtonIcon(final ImageIcon buttonIcon) {
		ButtonView.buttonIcon = buttonIcon;
	}
	
	public static void setButtonIconHit(final ImageIcon buttonIconHit) {
		ButtonView.buttonIconHit = buttonIconHit;
	}

	public static void setButtonIconLit(final ImageIcon buttonIconLit) {
		ButtonView.buttonIconLit = buttonIconLit;
	}
	
	public void setNormalIcon() {
		setIcon(buttonIcon);
	}
	
	public void setHitIcon() {
		setIcon(buttonIconHit);
	}
	
	private class ImageCreator {
		
	}
}