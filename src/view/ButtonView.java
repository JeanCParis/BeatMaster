package view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import events.UpdateViewEvent;
import beatbox.Button;
import beatbox.Pulse;
import view.ImageScaler.ScaleType;
import main.Game;
import actionlisteners.UpdateViewListener;

public class ButtonView extends JButton implements UpdateViewListener{
	static protected ImageIcon buttonIcon;
	static protected ImageIcon buttonIconHit;
	static protected ImageIcon buttonIconLit;
	
	protected ImageIcon currentIcon;
	
	protected Button button;
	
	public ButtonView(Button button) {
		setIcon(buttonIcon);
		currentIcon = buttonIcon;
		setBorder(BorderFactory.createEmptyBorder());
		setBounds(button.getXPosition(), button.getYPosition(), Game.BUTTON_SIZE, Game.BUTTON_SIZE);
		this.button = button;
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
		currentIcon = buttonIcon;
	}
	
	public void setHitIcon() {
		currentIcon = buttonIconHit;
	}
	
	@Override
	public void updateView(UpdateViewEvent e) {
		List<Pulse> pulses = button.getPulses();
		int size = pulses.size();
		
		if(size !=0) {
			Icon[] icons = new Icon[pulses.size()+1];
			for(int i=0 ; i<size ; ++i) {
				ImageScaler scaler = new ImageScaler(buttonIconLit);
				int scale = (int)(pulses.get(i).getCurrentFactor()*Game.BUTTON_SIZE);
				if(scale==0){
					scale=1;
				}
				scaler.createScaledImage(scale,scale,ScaleType.FIT);
				icons[i] = scaler.getScaledImage();
			}
			icons[size]=currentIcon;
			setIcon(new CompoundIcon(icons));
		}
		else {
			setIcon(currentIcon);
		}
		
		repaint();
	}
	
	private class ImageCreator {
		
		/*ImageScaler is = new ImageScaler(buttonIcon);
		is.createScaledImage(10, 10, ScaleType.FIT);
		
		setIcon(is.getScaledImage());*/
		
		//image de base, le futur resultat
		//pour chaque pulsation, calculer l'image correspondante et la coller à l'image de base
		//coller l'image actuelle
	}
}