package actionlisteners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import beatbox.Beatbox;

public class CheckListener implements MouseListener{
	protected Beatbox beatbox;
	
	public CheckListener(Beatbox beatbox) {
		this.beatbox = beatbox;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		beatbox.setChecked(!beatbox.isChecked());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
