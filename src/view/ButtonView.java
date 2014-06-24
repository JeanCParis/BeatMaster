package view;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ButtonView extends JButton {
	static protected ImageIcon buttonIcon;
	static protected ImageIcon buttonIconHit;
	static protected ImageIcon buttonIconLit;
	
	public ButtonView() {
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
}