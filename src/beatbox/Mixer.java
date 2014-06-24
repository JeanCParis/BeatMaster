package beatbox;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import actionlisteners.LineStoppedListener;

public class Mixer {
	protected boolean stopThread = false;
	protected boolean playUpcommingClips = false;
	
	protected File ticksoundFile;
	protected boolean paused = false;
	protected HashMap<String, Clip> currentClips = new HashMap<String, Clip>();
	protected HashMap<String, File> upcommingClips = new HashMap<String, File>();
	
	public void setTickSound(File ticksoundFile) {
		this.ticksoundFile = ticksoundFile;
	}
	
	public synchronized void addUpcommingClip(final File file, final String signature) {
		if(upcommingClips.get(signature)==null) {
			upcommingClips.put(signature, file);
		}
	}
	
	public void playUpcommingClips() {
		for(final String key : upcommingClips.keySet()) {
			final File file = upcommingClips.get(key);
			playClip(file, key, 0);
			upcommingClips.remove(key);
		}
		playClip(ticksoundFile, "", 0);
	}
	
	public synchronized void playClip(final File file, final String signature, final long startMicrosecondPosition) {
			final Clip clip = getClip(file, signature);
	        clip.setMicrosecondPosition(startMicrosecondPosition);
	        removeClip(signature);
	        clip.start();
			currentClips.put(signature, clip);
	}
	
	private Clip getClip(File file, String signature) {
		try {
			final AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
			final Clip clip = AudioSystem.getClip();
			clip.addLineListener(new LineStoppedListener(this, audioInputStream));
	        clip.open(audioInputStream);
	        return clip;
		} catch (final UnsupportedAudioFileException e){
			System.out.print("Error addUpcommingClip");
			return null;
		} catch (final IOException e){
			System.out.print("Error addUpcommingClip");
			return null;
		} catch (final LineUnavailableException e){
			System.out.print("Error addUpcommingClip");
			return null;
		}
	}
	
	public void removeClip(String signature) {
		Clip clip = currentClips.get(signature);
		if (clip!=null) {
			clip.stop();
			clip.close();
			currentClips.remove(signature);
		}
	}
	
	public boolean isPaused() {
		return paused;
	}
	
	public void setPaused(boolean paused) {
		if(paused) {
			if(!this.paused) {
				for(final String key : currentClips.keySet()) {
					currentClips.get(key).stop();
				}
				this.paused = true;
			}
		}
		else {
			if(this.paused) {
				for(final String key : currentClips.keySet()) {
					currentClips.get(key).start();
				}
				this.paused = false;
			}
		}
	}
}
