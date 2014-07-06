package actionlisteners;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

import beatbox.Beatbox;
import beatbox.Mixer;

public class LineUpdateListener implements LineListener {
	protected Mixer mixer;
	protected AudioInputStream audioInputStream;
	protected Beatbox beatbox;
	protected String id;
	
	public LineUpdateListener(final Mixer mixer, final AudioInputStream audioInputStream, final Beatbox beatbox, final String id) {
		this.mixer = mixer;
		this.audioInputStream = audioInputStream;
		this.beatbox = beatbox;
		this.id = id;
	}
	
	@Override
	public synchronized void update(final LineEvent event) {
		if(event.getType() == LineEvent.Type.STOP && !mixer.isPaused()) {
			Clip clip = (Clip)event.getSource();
			clip.close();
			try {
				audioInputStream.close();
			}
			catch(IOException e) {
				System.out.println("Failed to close audioInputStream");
			}
			beatbox.soundEnded(id);
		}
		if(event.getType() == LineEvent.Type.START && !mixer.isPaused()) {
			beatbox.soundStarted(id);
		}
	}
}
