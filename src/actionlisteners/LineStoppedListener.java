package actionlisteners;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

import main.Game;
import beatbox.Mixer;

public class LineStoppedListener implements LineListener {
	protected Mixer mixer;
	protected AudioInputStream audioInputStream;
	protected Game game;
	protected String id;
	
	public LineStoppedListener(Mixer mixer, AudioInputStream audioInputStream, Game game, String id) {
		this.mixer = mixer;
		this.audioInputStream = audioInputStream;
		this.game = game;
		this.id = id;
	}
	
	@Override
	public void update(LineEvent event) {
		if(event.getType() == LineEvent.Type.STOP && !mixer.isPaused()) {
			Clip clip = (Clip)event.getSource();
			clip.close();
			try {
				audioInputStream.close();
			}
			catch(IOException e) {
				System.out.println("Failed to close audioInputStream");
			}
			game.soundEnded(id);
		}
		if(event.getType() == LineEvent.Type.START && !mixer.isPaused()) {
			game.soundStarted(id);
		}
	}
}
