package beatbox;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import actionlisteners.LineUpdateListener;

public class Mixer {
	protected volatile boolean busy = false;
	
	protected Beatbox beatbox;
	
	protected boolean stopThread = false;
	protected boolean playUpcommingClips = false;
	
	protected File ticksoundFile;
	protected boolean paused = false;
	protected Map<String, Clip> currentClips = new TreeMap<String, Clip>();
	protected Map<String, File> upcommingClips = new ConcurrentHashMap<String, File>();
	
	public Mixer(Beatbox beatbox) {
		this.beatbox = beatbox;
		ticksoundFile = new File("sounds/tick.wav");
	}
	
	public synchronized void addUpcommingClip(final File file, final String id) {
		if(upcommingClips.get(id)==null) {
			upcommingClips.put(id, file);
		}
	}
	
	public synchronized void playUpcommingClips() {
		Iterator<String> keys = upcommingClips.keySet().iterator();
		while(keys.hasNext()) {
			String key = keys.next();
			final File file = upcommingClips.get(key);
			playClip(file, key, 0);
			upcommingClips.remove(key);
		}
		playClip(ticksoundFile, "", 0);
	}
	
	public synchronized void playClip(final File file, final String id, final long startMicrosecondPosition) {
			Clip oldClip = currentClips.get(id);
			if (oldClip != null){
				oldClip.stop();
			}
			Clip clip = getClip(file, id);
			while(clip == null) {
				clip = getClip(file, id);
			}
			
	        clip.setMicrosecondPosition(startMicrosecondPosition);
	        clip.start();
			currentClips.put(id, clip);
	}
	
	private Clip getClip(File file, String id) {
		try {
			final AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
			final Clip clip = AudioSystem.getClip();
			clip.addLineListener(new LineUpdateListener(this, audioInputStream, beatbox, id));
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
	
	public void removeClip(String id) {
		currentClips.remove(id);
	}
	
	public void stopClip(String id) {
		Clip clip = currentClips.get(id);
		if (clip != null){
			clip.stop();
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
