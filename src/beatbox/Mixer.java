package beatbox;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Mixer implements Runnable, TickListener {
	protected boolean paused = false;
	protected HashMap<String, Clip> currentClips = new HashMap<String, Clip>();
	protected HashMap<String, File> upcommingClips = new HashMap<String, File>();
	
	public Mixer() {

	}
	
	public void addUpcommingClip(final File file, final String signature) {
		upcommingClips.put(signature, file);  
	}
	
	public void pause() {
		paused=true;
		
		for(final String key : currentClips.keySet()) {
			currentClips.get(key).stop();
		}
	}
	
	public void resume() {
		paused = false;
		
		for(final String key : currentClips.keySet()) {
			currentClips.get(key).start();
		}
	}
	
	protected void playUpcommigClips() {
		for(final String key : upcommingClips.keySet()) {
			final File file = upcommingClips.get(key);
			try {
				final AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
				final Clip clip = AudioSystem.getClip();
				currentClips.put(key, clip);
		        clip.open(audioInputStream);
		        clip.start();
			} catch (final UnsupportedAudioFileException e){
				System.out.print("Error addUpcommingClip");
			} catch (final IOException e){
				System.out.print("Error addUpcommingClip");
			} catch (final LineUnavailableException e){
				System.out.print("Error addUpcommingClip");
			}
		}
	}
	
	@Override
	public void metronomeTicked(final TickEvent e) {
		playUpcommigClips();
	}

	@Override
	public void run() {
		if(!paused) {
			for(final String key : currentClips.keySet()) {
				final Clip clip = currentClips.get(key);
				if(!clip.isRunning()) {
					clip.close();
					currentClips.remove(key);
				}
			}
		}
	}
}
